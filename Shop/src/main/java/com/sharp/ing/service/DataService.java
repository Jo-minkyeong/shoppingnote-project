package com.sharp.ing.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
import com.sharp.ing.domain.Shopping_listDTO;

@Service("DataService")
public class DataService {

	Logger logger = LoggerFactory.getLogger("com.sharp.ing.controller.DataService");

	private ItemDTO itemDTO;
	private DataDAO dataDAO;

	JSONObject data = new JSONObject();

	@Autowired
	public DataService(ItemDTO itemDTO, DataDAO dataDAO) {
		this.itemDTO = itemDTO;
		this.dataDAO = dataDAO;
	}

	// 카데고리 코드 조회
	public List<CategoryDTO> Category() throws Exception {
		List<CategoryDTO> listCategory = dataDAO.category();
		return listCategory;
	}

	// 리스트 생성
	// insert문은 return type이 필요 없음
	@Transactional
	public void ItemHead(Shopping_listDTO itemHead) throws Exception {
		logger.debug(itemHead + "===================list===================");

		dataDAO.insertItemHead(itemHead);

	}

	// 물품등록
	public void Item(List<ItemDTO> items) throws Exception {

		logger.debug(items + "===================item===================");

		int listID = dataDAO.selectListID();

		// items loop item.setListId(listID)
		for (ItemDTO item : items) {
			item.setList_id(listID);
		}

		dataDAO.insertItem(items);
	}

	// 리스트, 물품 전체조회
	public Object ViewTotalShopping(String userId) throws Exception {
		List<ShoppingItemDTO> Header = dataDAO.viewTotalShoppingHeader(userId);
		List<ShoppingItemDTO> Body = dataDAO.viewTotalShoppingBody(userId);

		// 구조 : [{ head, body[]}]
		List result = new ArrayList<>();

		logger.debug("======" + Header);

		for (ShoppingItemDTO head : Header) {
			Map<String, Object> item = new Hashtable<>();
			item.put("head", head);

			List<ShoppingItemDTO> bodyItems = new ArrayList<>();

			for (ShoppingItemDTO body : Body) {
				if (head.getList_id() == body.getList_id()) {
					bodyItems.add(body);
				}
			}
			item.put("body", bodyItems);

			result.add(item);
		}

		return result;
	}

	// 리스트, 물품 상세조회
	public List<ShoppingItemDTO> ViewShoppingItem(String userId, int list_id) throws Exception {
		List<ShoppingItemDTO> listShoppingItem = dataDAO.viewShoppingItem(userId, list_id);
		return listShoppingItem;
	}

	// 리스트 수정
	@Transactional
	public void EditItemHead(Shopping_listDTO editItemHead) throws Exception {
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
	public void DeleteItem(int list_id, int item_no) throws Exception {
		dataDAO.deleteItem(list_id, item_no);
	}

}
