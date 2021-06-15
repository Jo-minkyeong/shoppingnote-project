package com.sharp.ing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharp.ing.domain.CategoryAvgDTO;
import com.sharp.ing.domain.CategoryDTO;
import com.sharp.ing.domain.DataDAO;
import com.sharp.ing.domain.ItemDTO;
import com.sharp.ing.domain.Shopping_listDTO;

@Service("service")
public class DataService {

	private DataDAO dataDAO;

	@Autowired
	public DataService(DataDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	// 카데고리 코드 조회
	public List<CategoryDTO> Category() throws Exception {
		List<CategoryDTO> listCategory = dataDAO.category();
		return listCategory;
	}

	// 리스트생성
	// insert문은 return type이 필요 없음
	@Transactional
	public void Shoppinglist(Shopping_listDTO listDTO) throws Exception {
		dataDAO.insertShoppinglist(listDTO);
	}

	// 물품 등록
	public void Item(ItemDTO itemDTO) throws Exception {
		dataDAO.insertItem(itemDTO);
	}

	// 리스트 수정
	public void EditShoppinglist(Shopping_listDTO listDTO) throws Exception {
		dataDAO.editShoppingList(listDTO);
	}

	// 물품 수정
	public void EditItem(ItemDTO itemDTO) throws Exception {
		dataDAO.editItem(itemDTO);
	}

	// 리스트 삭제
	// transactional = 하나 지우고 안지워지면 rollback 시켜줌 (DeleteList, DeleteItem 연결)
	@Transactional
	public void DeleteList(int list_id) throws Exception {
		dataDAO.deleteList(list_id);
		dataDAO.deleteItemList(list_id);
	}
	
	// 물품 삭제
	public void DeleteItem(int item_no) throws Exception {
		dataDAO.deleteItem(item_no);
	}

	// 전체 소비 평균 값
	public List<Shopping_listDTO> TotalAvg() throws Exception {
		List<Shopping_listDTO> totalAvg = dataDAO.totalAvg(); 
		return totalAvg; 
	}

	// 사용자 소비 평균 값
	public List<Shopping_listDTO> UserAvg(String user_id) throws Exception {
		List<Shopping_listDTO> userAverage = dataDAO.userAvg(user_id); 
		return userAverage; 
	}
	
	// 카테고리별 통계
	public List<CategoryAvgDTO> CategoryAvg(String user_id) throws Exception {
		List<CategoryAvgDTO> categoryAvg = dataDAO.categoryAvg(user_id);
		return categoryAvg;
	}
	
	// 카테고리별 6개월 통계
	public List<CategoryAvgDTO> CategoryAvg6(String user_id) throws Exception {
		List<CategoryAvgDTO> categoryAvg6 = dataDAO.categoryAvg6(user_id);
		return categoryAvg6;
	}
	
	// 카테고리별 12개월 통계
	public List<CategoryAvgDTO> CategoryAvg12(String user_id) throws Exception {
		List<CategoryAvgDTO> categoryAvg12 = dataDAO.categoryAvg12(user_id);
		return categoryAvg12;
	}
	
}
