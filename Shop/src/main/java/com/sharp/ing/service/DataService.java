package com.sharp.ing.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharp.ing.domain.CategoryDTO;
import com.sharp.ing.domain.DataDAO;
import com.sharp.ing.domain.ItemDTO;
import com.sharp.ing.domain.ShoppingItemDTO;
import com.sharp.ing.domain.ShoppingListDTO;

@Service("DataService")
public class DataService {

	Logger logger = LoggerFactory.getLogger("com.sharp.ing.controller.DataService");

	private ItemDTO itemDTO;
	private DataDAO dataDAO;
	private List<CategoryDTO> listCategory;
	
	JSONObject data = new JSONObject();

	@Autowired
	public DataService(ItemDTO itemDTO, DataDAO dataDAO) {
		this.itemDTO = itemDTO;
		this.dataDAO = dataDAO;
	}

	// 카데고리 코드 조회
	public List<CategoryDTO> Category() throws Exception {
		listCategory = dataDAO.category();
		return listCategory;
	}

	// 리스트 생성
	// insert문은 return type이 필요 없음

	public void ItemHead(ShoppingListDTO itemHead) throws Exception {
		
		logger.debug("=========================ItemHead=========================");
		logger.debug("itemHead = " + itemHead.toString());
		
		dataDAO.insertItemHead(itemHead);
	}

	// 물품등록
	public void Item(List<ItemDTO> items) throws Exception {
		
		logger.debug("=========================Item=========================");
		logger.debug("Item = " + items.toString());

		int listID = dataDAO.selectListID();

		// items loop => item.setListId(listID)
		for (ItemDTO item : items) {
			item.setList_id(listID);
		}
		dataDAO.insertItem(items);
	}

	// 리스트, 물품 전체조회
	public Object ViewTotalShopping(String userId) throws Exception {
		
		logger.debug("=========================ViewTotalShopping=========================");
		
		List<ShoppingItemDTO> listShoppingHead = dataDAO.viewTotalShoppingHead(userId);
		List<ShoppingItemDTO> listShoppingBody = dataDAO.viewTotalShoppingBody(userId);

		// 구조 : [{ head, body[]}]
		List TotalView = new ArrayList<>();

		logger.debug("======" + listShoppingHead);

		for (ShoppingItemDTO itemHead : listShoppingHead) {
			
			Map<String, Object> item = new Hashtable<>();
			item.put("head", itemHead);

			List<ShoppingItemDTO> listItems = new ArrayList<>();

			for (ShoppingItemDTO itemBody : listShoppingBody) {
				if (itemHead.getList_id() == itemBody.getList_id()) {
					listItems.add(itemBody);
				}
			}
			item.put("body", listItems);

			TotalView.add(item);
		}

		return TotalView;
	}

	// 리스트, 물품 상세조회
	public JSONObject ViewShoppingItem(String userId, int list_id) throws Exception {
		
		// 구조 : { head, body[{},{}..] }
		ShoppingItemDTO ShoppingHead = dataDAO.viewShoppingHead(userId, list_id);
		List<ItemDTO> listShoppingBody= dataDAO.viewShoppingBody(userId, list_id);
		
		data.put("head", ShoppingHead);
		data.put("body", listShoppingBody);
	
		return data;
	}

	// 리스트 수정

	public void EditItemHead(ShoppingListDTO editItemHead) throws Exception {
		dataDAO.editShoppinglist(editItemHead);
	}

	// 물품 수정
	public void EditItem(List<ItemDTO> editItems) throws Exception {

//		for (ItemDTO item:editItems) {
//			dataDAO.upsertItem(item);
//		}
		dataDAO.editItem(editItems);
	}

	// 리스트 삭제
	// transactional = 하나 지우고 안지워지면 rollback 시켜줌 (DeleteList, DeleteItem 연결)
//	@Transactional
//	public void DeleteList(int list_id) throws Exception {
//		dataDAO.deleteList(list_id);
//		dataDAO.deleteItemList(list_id);
//	}

	// 물품 삭제
	public void DeleteItem(List<ShoppingItemDTO> listdeleteItem) throws Exception {
		dataDAO.deleteItem(listdeleteItem);
	}

	/*
	 * public static boolean isEmpty(Object obj) { if( obj instanceof List ) return
	 * obj==null || ((List<?>)obj).isEmpty(); else{ return obj == null; } }
	 * 
	 * 
	 * public static boolean isNotEmpty(String str) { return !isEmpty(str); }
	 */
}
