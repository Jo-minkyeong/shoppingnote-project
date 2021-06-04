package com.sharp.ing.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("dataDAO")
public interface DataDAO {
	Logger logger = LoggerFactory.getLogger("controller.BoardController");

	//code01
//	public List<CategoryDTO> autoLevel1() throws Exception;
	//code02
//	public List<Level2DTO> autoLevel2() throws Exception;
	//code03
//	public List<AutoDTO> autoLevel3() throws Exception;
	//code04
//	public List<AutoDTO> autoLevel4() throws Exception;
	
	public List<CategoryDTO> category() throws Exception;
	
	
	//리스트 생성
	public void insertShoppinglist(Shopping_listDTO listDTO) throws Exception;

	//물품 등록
	public void insertItem(ItemDTO itemDTO) throws Exception;
	
	//리스트 수정
	public void editShoppingList(Shopping_listDTO listDTO) throws Exception;
	
	//물품 수정
	public void editItem(ItemDTO itemDTO) throws Exception;
	
	//리스트(영수증) 삭제
	public void deleteList(int list_id) throws Exception;
	public void deleteItemList(int list_id) throws Exception;
	
	//물품 삭제 
	public void deleteItem(int item_no) throws Exception;
	
}
