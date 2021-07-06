package com.sharp.ing.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
	List<ShoppingItemDTO> listTotalShoppingHeader;
	List<ShoppingItemDTO> listTotalShoppingBody;
	List<ShoppingItemDTO> listShoppingItem;
	List<ItemDTO> listItem;

	JSONObject TotalShopping = new JSONObject();
	JSONObject data = new JSONObject();
	// 구글의 json paser 라이브러리
	Gson gson = new Gson();
	Gson gson2 = new Gson();

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

		listCategory = service.Category();
		model.addAttribute("category", listCategory);
		logger.debug("=========================getCategory=========================");
		return listCategory;
	}

	// 리스트 생성
	// RequestMapping = 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션(브라우저에 접속할때
	// 붙여줌)
//	@RequestMapping("/shoppinglist")
//	public String Shoppinglist(@RequestParam(value = "userId") String userId, @RequestParam(value = "id") int id,
//			@RequestParam(value = "list_id", required = false) int list_id,
//			@RequestParam(value = "Purchase_date", required = false) String purchase_date) throws Exception {
//
//		listDTO.setuserId(userId);
//		listDTO.setId(id);
//		listDTO.setPurchase_date(purchase_date);
//		listDTO.setList_id(list_id);
//		logger.debug("=========================Shoppinglist=========================");
//
//		// service로 보내줌
//		service.Shoppinglist(listDTO);
//
//		return "index.html";
//	}

	// 물품 등록
//	@RequestMapping("/item")
//	public String Item(@RequestParam(value = "item_no") int item_no, @RequestParam(value = "list_id") int list_id,
//			@RequestParam(value = "mart_code") String mart_code, @RequestParam(value = "price") int price,
//			@RequestParam(value = "amount") int amount, @RequestParam(value = "code01") int code01,
//			@RequestParam(value = "code02") int code02, @RequestParam(value = "code03") int code03,
//			@RequestParam(value = "code04") int code04, @RequestParam(value = "qt") int qt,
//			@RequestParam(value = "qt_code") String qt_code) throws Exception {
//		logger.debug("=========================Item=========================");
//		itemDTO.setItem_no(item_no);
//		itemDTO.setList_id(list_id);
//		itemDTO.setMart_code(mart_code);
//		itemDTO.setAmount(amount);
//		itemDTO.setCode01(code01);
//		itemDTO.setCode02(code02);
//		itemDTO.setCode03(code03);
//		itemDTO.setCode04(code04);
//		itemDTO.setPrice(price);
//		itemDTO.setQt(qt);
//		itemDTO.setQt_code(qt_code);
//		service.Item(itemDTO);
//		return "index.html";
//	}

	// http통신할때 post는 body에다 data를 집어넣어서 받아오는 용도
	// @RequestBody에 param에 있는 값을 매칭해서 보내줘라.
//	@RequestMapping(value = "/item", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
//	public @ResponseBody String Item(@RequestBody List<Map<String, Object>> param) throws Exception {
//		logger.debug("param ======== "+param.toString());
//		// 구글의 json paser 라이브러리
//		Gson gson = new Gson();
//		ItemDTO[] array = gson.fromJson(param.toString(), ItemDTO[].class);
//		
//		// asList => 배열을 리스트로 바꾸어줌/ java.util.Arrays.ArrayList (Arrays클래스의 메소드로 ArrayList로 바꾸어줌, 사이즈 고정)/ java.util.ArrayList 클래스와는 다른 클래스
//		List<ItemDTO> item = Arrays.asList(array);
//		service.Item(item);
//
//		return "success";
//	}

	//리스트 생성, 물품등록
	@RequestMapping(value = "/item", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public @ResponseBody String Item(@RequestBody Map<String, Object> param) throws Exception {
		logger.debug("param ======== " + param.toString());
		// 구글의 json paser 라이브러리
		Gson gson = new Gson();

		// jsonPaserPser 클래스 객체를 만들고 해당 객체에
		JsonParser jparser = new JsonParser();

		// param의 id 오브젝트 -> 문자열 파싱 -> jsonElement 파싱
		JsonElement head = jparser.parse(param.get("head").toString());
		
		Shopping_listDTO itemHead = gson.fromJson(head, Shopping_listDTO.class);
//		ItemDTO gsonHead = gson.fromJson(head, ItemDTO.class);
		service.ItemHead(itemHead);
		
		JsonElement body = jparser.parse(param.get("body").toString()); 
		ItemDTO[] Item = gson.fromJson(body.toString(), ItemDTO[].class);
		
		// asList => 배열을 리스트로 바꾸어줌/ java.util.Arrays.ArrayList (Arrays클래스의 메소드로
		// ArrayList로 바꾸어줌, 사이즈 고정)/ java.util.ArrayList 클래스와는 다른 클래스
//		List<ItemDTO> items = new ArrayList(Arrays.asList(gsonItem));
		List<ItemDTO> items = Arrays.asList(Item);

		service.Item(items);

		return "success";
	}

	// 리스트, 아이템 전체조회
	@RequestMapping("/viewtotalshopping")
	public JSONObject ViewTotalShopping(@RequestParam(value = "userId") String userId, Model model) throws Exception {

		service.ViewTotalShoppingHeader(userId);
		service.ViewTotalShoppingBody(userId);

		listTotalShoppingHeader = service.ViewTotalShoppingHeader(userId);
		listTotalShoppingBody = service.ViewTotalShoppingBody(userId);

		model.addAttribute("Header", listTotalShoppingHeader);
		model.addAttribute("Body", listTotalShoppingBody);

		TotalShopping.put("Header", listTotalShoppingHeader);
		TotalShopping.put("Body", listTotalShoppingBody);

		return TotalShopping;

	}

	// 리스트, 아이템 상세조회
	@RequestMapping("/viewshoppingitem")
	public List<ShoppingItemDTO> ViewShoppingItem(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "list_id") Integer list_id) throws Exception {

//		if(list_id != null) {
//			service.ViewShoppingItem(userId, list_id);
//			listShoppingItem = service.ViewShoppingItem(userId, list_id);
//			return listShoppingItem;
//		}else 
//			service.ViewTotalShopping(userId);
//			listTotalShopping = service.ViewTotalShopping(userId);
//			return listTotalShopping;

		service.ViewShoppingItem(userId, list_id);
		listShoppingItem = service.ViewShoppingItem(userId, list_id);
		return listShoppingItem;

	}
	
	
	@RequestMapping(value = "/edititem", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public @ResponseBody String EditItem(@RequestBody Map<String, Object> param) throws Exception {
		logger.debug("param ======== " + param.toString());
		
		// jsonPaserPser 클래스 객체를 만들고 해당 객체에
		JsonParser jparser = new JsonParser();

		// param의 id 오브젝트 -> 문자열 파싱 -> jsonElement 파싱
		JsonElement head = jparser.parse(param.get("head").toString());
		
		Shopping_listDTO itemHead = gson2.fromJson(head, Shopping_listDTO.class);
//		ItemDTO gsonHead = gson.fromJson(head, ItemDTO.class);
		service.EditItemHead(itemHead);
		
		JsonElement body = jparser.parse(param.get("body").toString());
		ItemDTO[] Item = gson2.fromJson(body.toString(), ItemDTO[].class);
		
		// asList => 배열을 리스트로 바꾸어줌/ java.util.Arrays.ArrayList (Arrays클래스의 메소드로 ArrayList로 바꾸어줌, 사이즈 고정)/ java.util.ArrayList 클래스와는 다른 클래스
//		List<ItemDTO> items = new ArrayList(Arrays.asList(gsonItem));
		List<ItemDTO> items = Arrays.asList(Item);

		service.EditItem(items);

		return "success";
	}


	// 리스트 수정
//	@RequestMapping("/editshoppinglist")
//	public String EditShoppinglist(@RequestParam(value = "userId") String userId,
//			@RequestParam(value = "list_id") int list_id, @RequestParam(value = "Purchase_date") String purchase_date)
//			throws Exception {
//		listDTO.setuserId(userId);
//		listDTO.setPurchase_date(purchase_date);
//		listDTO.setList_id(list_id);
//		logger.debug("=========================EditShoppinglist=========================");
//
//		service.EditShoppinglist(listDTO);
//		return "index.html";
//	}

	// 물품 수정
//	@RequestMapping(value = "/edititem")
//	public String EditItem(@RequestParam(value = "item_no") int item_no, @RequestParam(value = "list_id") int list_id,
//			@RequestParam(value = "mart_code") String mart_code, @RequestParam(value = "price") int price,
//			@RequestParam(value = "amount") int amount, @RequestParam(value = "code01") int code01,
//			@RequestParam(value = "code02") int code02, @RequestParam(value = "code03") int code03,
//			@RequestParam(value = "code04") int code04, @RequestParam(value = "qt") int qt,
//			@RequestParam(value = "qt_code") String qt_code) throws Exception {
//		logger.debug("=========================EditItem=========================");
//		itemDTO.setItem_no(item_no);
//		itemDTO.setList_id(list_id);
//		itemDTO.setMart_code(mart_code);
//		itemDTO.setAmount(amount);
//		itemDTO.setCode01(code01);
//		itemDTO.setCode02(code02);
//		itemDTO.setCode03(code03);
//		itemDTO.setCode04(code04);
//		itemDTO.setPrice(price);
//		itemDTO.setQt(qt);
//		itemDTO.setQt_code(qt_code);
//		service.EditItem(itemDTO);
//		return "index.html";
//	}

	// 리스트 삭제
//	@RequestMapping(value = "/deletelist")
//	public String DeleteList(@RequestParam(value = "list_id") int list_id) throws Exception {
//		logger.debug("=========================DeleteList=========================");
//		service.DeleteList(list_id);
//		return "index.html";
//	}

	// 물품 삭제
	@RequestMapping(value = "/deleteitem")
	public String DeleteItem(@RequestParam(value = "list_id") int list_id, @RequestParam(value = "item_no") int item_no)
			throws Exception {
		logger.debug("=========================DeleteItem=========================");
		service.DeleteItem(list_id, item_no);
		return "success";
	}

}
