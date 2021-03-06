package com.sharp.ing.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("dataDAO")
public interface DataDAO {
	
	Logger logger = LoggerFactory.getLogger("com.sharp.ing.domain.DataDAO");


	// 카데고리 코드 조회
	public List<CategoryDTO> category() throws Exception;
	
	// 리스트 생성
	public void insertItemHead(ShoppingListDTO itemHead) throws Exception;
	// 아이템 생성
	public Integer selectListID() throws Exception;
	public void insertItem(List<ItemDTO> item) throws Exception;
	
	// 리스트, 물품 전체조회 
	public List<ShoppingItemDTO> viewTotalShoppingHead(String userId) throws Exception;
	public List<ShoppingItemDTO> viewTotalShoppingBody(String userId) throws Exception;	

	// 리스트, 물품 상세조회 
	public ShoppingItemDTO viewShoppingHead(String userId, int list_id) throws Exception;
	public List<ItemDTO> viewShoppingBody(String userId, int list_id) throws Exception;
	
	// 리스트, 물품 수정
	public void editShoppinglist(ShoppingListDTO editItemHead) throws Exception;
	public void editItem(List<ItemDTO> editItems) throws Exception;

//	public void upsertItem(ItemDTO editItems) throws Exception;
	
	// 리스트(영수증) 삭제
//	public void deleteList(int list_id) throws Exception;
//	public void deleteItemList(int list_id) throws Exception;

	// 물품 삭제
	public void deleteItem(List<ShoppingItemDTO> listdeleteItem) throws Exception;
	
}
