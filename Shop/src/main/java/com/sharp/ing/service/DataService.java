package com.sharp.ing.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharp.ing.domain.DataDAO;
import com.sharp.ing.domain.ItemDTO;
import com.sharp.ing.domain.CategoryDTO;
import com.sharp.ing.domain.Shopping_listDTO;

@Service("service")
public class DataService {

	private DataDAO dataDAO;
	
	@Autowired
	public DataService(DataDAO dataDAO) {
		this.dataDAO = dataDAO;
	}
	
	//코드테이블
	@Autowired
	public List<CategoryDTO> Category() throws Exception{
		List<CategoryDTO> listCategory = dataDAO.category();
		return listCategory;
	}
	
	//리스트생성
	//insert문은 return type이 필요 없음
	@Transactional
	public void Shoppinglist(Shopping_listDTO listDTO) throws Exception{
		dataDAO.insertShoppinglist(listDTO);
	}
	//물품 등록
	public void Item(ItemDTO itemDTO) throws Exception{
		dataDAO.insertItem(itemDTO);
	}
	//리스트 수정
	public void EditShoppinglist(Shopping_listDTO listDTO) throws Exception{
		dataDAO.editShoppingList(listDTO);
	}
	//물품 수정
	public void EditItem(ItemDTO itemDTO) throws Exception{
		dataDAO.editItem(itemDTO);
	}
	//물품 삭제
	//transactional = 하나 지우고 안지워지면 rollback 시켜줌 (DeleteList, DeleteItem 연결)
	@Transactional
	public void DeleteList(int list_id) throws Exception {
		dataDAO.deleteList(list_id);
		dataDAO.deleteItemList(list_id);
	}
	public void DeleteItem(int item_no) throws Exception {
		dataDAO.deleteItem(item_no);
	}
	
}