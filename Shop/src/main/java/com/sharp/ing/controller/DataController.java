package com.sharp.ing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharp.ing.domain.DataDAO;
import com.sharp.ing.domain.ItemDTO;
import com.sharp.ing.domain.Shopping_listDTO;
import com.sharp.ing.service.DataService;

@Controller
public class DataController {

	private Shopping_listDTO listDTO;
	private ItemDTO itemDTO;
	private DataService service;

	Logger logger = LoggerFactory.getLogger("controller.DataController");

	@Autowired
	public DataController(Shopping_listDTO listDTO, ItemDTO itemDTO, DataService service) {
		this.listDTO = listDTO;
		this.itemDTO = itemDTO;
		this.service = service;
	}
	
	// RequestMapping = 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션(브라우저에 접속할때 붙여줌)
	
	@RequestMapping("/shoppinglist")
	public String Shoppinglist(@RequestParam(value = "user_id") String user_id,
			@RequestParam(value = "list_id") int list_id, @RequestParam(value = "Purchase_date") String purchase_date)
			throws Exception {
		listDTO.setUser_id(user_id);
		listDTO.setPurchase_date(purchase_date);
		listDTO.setList_id(list_id);
		logger.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		service.Shoppinglist(listDTO);
		return "index";
	}
	
	@RequestMapping("/item")
	public String Item
			(@RequestParam(value="mart_code")String mart_code, @RequestParam(value="price")int price, 
			@RequestParam(value="amount")int amount, @RequestParam(value="code01")int code01, 
			@RequestParam(value="code02")int code02,@RequestParam(value="code03")int code03, 
			@RequestParam(value="code04")int code04, @RequestParam(value="qt")int qt,
			@RequestParam(value="qt_code")String qt_code, @RequestParam(value="list_id")int list_id, 
			@RequestParam(value="item_no")int item_no) throws Exception{
		
		itemDTO.setMart_code(mart_code);
		itemDTO.setAmount(amount);
		itemDTO.setCode01(code01);
		itemDTO.setCode02(code02);
		itemDTO.setCode03(code03);
		itemDTO.setCode04(code04);
		itemDTO.setPrice(price);
		itemDTO.setQt(qt);
		itemDTO.setQt_code(qt_code);
		service.Item(itemDTO);
		return "index";
	}
}
