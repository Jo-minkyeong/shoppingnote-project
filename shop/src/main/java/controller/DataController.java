package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.DataDAO;
import domain.ItemDTO;
import domain.Shopping_listDTO;
import service.DataService;

@RestController
public class DataController {
	
	@Autowired
	private DataDAO dataDao;
	@Autowired
	private Shopping_listDTO dataDto;
	@Autowired
	private ItemDTO itemDto;
	@Autowired
	DataService service;
	
	
	Logger logger = LoggerFactory.getLogger("controller.DataController");

	//요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
	@RequestMapping("shoppinglist")
	public String insertShoppinglist() throws Exception{
		List<Shopping_listDTO> insertShoppinglist = service.insertShoppinglist();
		return "shoppinglist";
	}
	
}
