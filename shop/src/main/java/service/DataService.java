package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domain.DataDAO;
import domain.ItemDTO;
import domain.Shopping_listDTO;

public class DataService {

	@Autowired
	DataDAO dataDao;
	
//	public List<Shopping_listDTO> insertShoppinglist() throws Exception{
//		return dataDao.insertShoppinglist();
	
	//insert문은 return type이 필요 없음.
	public void insertShoppinglist() throws Exception{}
	public void insertItem() throws Exception{}
	
}
