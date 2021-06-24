package com.sharp.ing.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.ing.domain.CategoryDTO;
import com.sharp.ing.domain.ItemDTO;
import com.sharp.ing.domain.ShoppingItemDTO;
import com.sharp.ing.domain.Shopping_listDTO;
import com.sharp.ing.service.DataService;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
public class DataController {

	Logger logger = LoggerFactory.getLogger("com.sharp.ing.controller.DataController");
	
	// 필드
	// 생성자에 @Autowired를 붙여줬기때문에 필드에는 붙일 필요 없음
	private Shopping_listDTO listDTO;
	private ItemDTO itemDTO;
	private DataService service;
	
	List<CategoryDTO> listCategory;
	List<ShoppingItemDTO> listShoppingItem;
	List<ItemDTO> listItem;

	JSONObject jsonObject = new JSONObject();
	JSONObject data = new JSONObject();

	// 생성자
	@Autowired
	public DataController(Shopping_listDTO listDTO, ItemDTO itemDTO, DataService service) {
		this.listDTO = listDTO;
		this.itemDTO = itemDTO;
		this.service = service;
	}
	

	// level1
//	@ResponseBody
//	@RequestMapping("/level1")
//	public List<CategoryDTO> getLevel1(Model model) throws Exception {
//
//		level1List=service.AutoLevel1();
//		model.addAttribute("level1List", level1List);
//		logger.debug("=========================getLevel1=========================");
//		return level1List;
//	}

	// 카데고리 코드 조회
//	@RequestMapping("/category")
//	public String Category(Model model) throws Exception {
//
//		listCategory = service.Category();
//		model.addAttribute("category", listCategory);
//		logger.debug("=========================getLevel1=========================");
//		return "index.html";
//	}

	// 실제 listCategory로 받아와서 실행 시켜볼때 하는 방법
	@RequestMapping("/category")
	public List<CategoryDTO> Category(Model model) throws Exception {

		listCategory=service.Category();
		model.addAttribute("category", listCategory);
		logger.debug("=========================getCategory=========================");
		return listCategory;
	}

	// 리스트 생성
	// RequestMapping = 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션(브라우저에 접속할때 붙여줌)
	@RequestMapping("/shoppinglist")
	public String Shoppinglist(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "list_id", required=false) int list_id, @RequestParam(value = "Purchase_date", required=false) String purchase_date) throws Exception {
		
		listDTO.setuserId(userId);
		listDTO.setPurchase_date(purchase_date);
		listDTO.setList_id(list_id);
		logger.debug("=========================Shoppinglist=========================");

		// service로 보내줌
		service.Shoppinglist(listDTO);

		return "index.html";
	}

	// 물품 등록
	@RequestMapping("/item")
	public String Item(@RequestParam(value = "item_no") int item_no, @RequestParam(value = "list_id") int list_id,
			@RequestParam(value = "mart_code") String mart_code, @RequestParam(value = "price") int price,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "code01") int code01,
			@RequestParam(value = "code02") int code02, @RequestParam(value = "code03") int code03,
			@RequestParam(value = "code04") int code04, @RequestParam(value = "qt") int qt,
			@RequestParam(value = "qt_code") String qt_code) throws Exception {
		logger.debug("=========================Item=========================");
		itemDTO.setItem_no(item_no);
		itemDTO.setList_id(list_id);
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
		return "index.html";
	}

	// 리스트, 아이템 조회
	@RequestMapping("/viewshoppingitem")
	public List<ShoppingItemDTO> ViewShoppingItem(@RequestParam(value="userId") String userId, @RequestParam(required=false, value="list_id") Integer list_id )throws Exception{
		
		if(list_id != null) {
			service.ViewShoppingItem(userId, list_id);
			listShoppingItem = service.ViewShoppingItem(userId, list_id);
			return listShoppingItem;
		}else 
			listShoppingItem = null;
			return listShoppingItem;
	}
	
	// 리스트 수정
	@RequestMapping("/editshoppinglist")
	public String EditShoppinglist(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "list_id") int list_id, @RequestParam(value = "Purchase_date") String purchase_date)
			throws Exception {
		listDTO.setuserId(userId);
		listDTO.setPurchase_date(purchase_date);
		listDTO.setList_id(list_id);
		logger.debug("=========================EditShoppinglist=========================");

		service.EditShoppinglist(listDTO);	
		return "index.html";
	}

	// 물품 수정
	@RequestMapping(value = "/edititem")
	public String EditItem(@RequestParam(value = "item_no") int item_no, @RequestParam(value = "list_id") int list_id,
			@RequestParam(value = "mart_code") String mart_code, @RequestParam(value = "price") int price,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "code01") int code01,
			@RequestParam(value = "code02") int code02, @RequestParam(value = "code03") int code03,
			@RequestParam(value = "code04") int code04, @RequestParam(value = "qt") int qt,
			@RequestParam(value = "qt_code") String qt_code) throws Exception {
		logger.debug("=========================EditItem=========================");
		itemDTO.setItem_no(item_no);
		itemDTO.setList_id(list_id);
		itemDTO.setMart_code(mart_code);
		itemDTO.setAmount(amount);
		itemDTO.setCode01(code01);
		itemDTO.setCode02(code02);
		itemDTO.setCode03(code03);
		itemDTO.setCode04(code04);
		itemDTO.setPrice(price);
		itemDTO.setQt(qt);
		itemDTO.setQt_code(qt_code);
		service.EditItem(itemDTO);
		return "index.html";
	}

	// 리스트 삭제
//	@RequestMapping(value = "/deletelist")
//	public String DeleteList(@RequestParam(value = "list_id") int list_id) throws Exception {
//		logger.debug("=========================DeleteList=========================");
//		service.DeleteList(list_id);
//		return "index.html";
//	}

	// 물품 삭제
	@RequestMapping(value = "/deleteitem")
	public String DeleteItem(@RequestParam(value = "list_id") int list_id, @RequestParam(value = "item_no") int item_no) throws Exception {
		logger.debug("=========================DeleteItem=========================");
		service.DeleteItem(list_id, item_no);
		return "index.html";
	}
	
}
