package com.sharp.ing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	//insert문은 return type이 필요 없음.
	public void Shoppinglist(Shopping_listDTO listDTO) throws Exception{
		dataDAO.insertShoppinglist(listDTO);
		System.out.println("=================================================");
	}
	public void Item(ItemDTO itemDTO) throws Exception{
		dataDAO.insertItem(itemDTO);
	}
	
	
	
}
