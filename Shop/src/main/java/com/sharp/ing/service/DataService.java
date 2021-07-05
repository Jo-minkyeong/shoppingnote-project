package com.sharp.ing.service;

import java.io.Serializable;
import java.util.List;

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

	// 리스트생성
	// insert문은 return type이 필요 없음
//	@Transactional
//	public void Shoppinglist(Shopping_listDTO listDTO) throws Exception {
//		dataDAO.insertShoppinglist(listDTO);
//	}

	// 물품 등록
//	public void Item(ItemDTO itemDTO) throws Exception {
//		dataDAO.insertItem(itemDTO);
//	}
	
	//리스트 생성
	public void ItemHead(Shopping_listDTO itemHead) throws Exception {
		logger.debug(itemHead+"===================list===================");
		dataDAO.insertItemHead(itemHead);
		
	}
	//물품등록
	public void Item(List<ItemDTO> items) throws Exception {
		
		logger.debug(items+"===================item===================");
		
		int listID = dataDAO.selectListID();
		
		//items loop item.setListId(listID)
		for (ItemDTO item: items) {
			item.setList_id(listID);
		}
		
		dataDAO.insertItem(items);
	}
	
	
	// 리스트, 물품 전체조회
	public List<ShoppingItemDTO> ViewTotalShoppingHeader(String userId) throws Exception{
		List<ShoppingItemDTO> listTotalShoppingHeader = dataDAO.viewTotalShoppingHeader(userId);
		return listTotalShoppingHeader;
	}
	public List<ShoppingItemDTO> ViewTotalShoppingBody(String userId) throws Exception{
		List<ShoppingItemDTO> listTotalShoppingBody = dataDAO.viewTotalShoppingBody(userId);
		return listTotalShoppingBody;
	}
	
	// 리스트, 물품 상세조회
	public List<ShoppingItemDTO> ViewShoppingItem(String userId, int list_id) throws Exception{
		List<ShoppingItemDTO> listShoppingItem = dataDAO.viewShoppingItem(userId, list_id);
		return listShoppingItem;
	}
	
	// 리스트 수정
	public void EditShoppinglist(Shopping_listDTO listDTO) throws Exception {
		dataDAO.editShoppinglist(listDTO);
	}

	// 물품 수정
	public void EditItem(ItemDTO itemDTO) throws Exception {
		dataDAO.editItem(itemDTO);
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
