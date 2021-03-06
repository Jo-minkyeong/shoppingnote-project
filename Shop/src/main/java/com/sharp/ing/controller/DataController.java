package com.sharp.ing.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.sharp.ing.domain.ShoppingListDTO;
import com.sharp.ing.service.DataService;

@RestController
@CrossOrigin(origins = "*")
public class DataController {

	Logger logger = LoggerFactory.getLogger("com.sharp.ing.controller.DataController");

	
// 	JSONObject TotalShopping = new JSONObject();
// 	JSONObject data = new JSONObject();

	// 구글의 json paser 라이브러리
	Gson gson = new Gson();

	// 필드
	// 생성자에 @Autowired를 붙여줬기때문에 필드에는 붙일 필요 없음
	private ShoppingListDTO listDTO;
	private ItemDTO itemDTO;
	private DataService service;
	private List<CategoryDTO> listCategory;
	private List<ShoppingItemDTO> listShoppingItem;

	// 생성자
	@Autowired
	public DataController(ShoppingListDTO listDTO, ItemDTO itemDTO, DataService service) {
		this.listDTO = listDTO;
		this.itemDTO = itemDTO;
		this.service = service;
	}

	
	@RequestMapping("/test")
	public String Test() throws Exception {

		logger.debug("=========================Test========================");

		System.out.println("hi~~~");

		return "success";
	}
	

	// 카데고리 코드 조회
	// 실제 listCategory로 받아와서 실행 시켜볼때 하는 방법
	@RequestMapping("/category")
	public List<CategoryDTO> Category(Model model) throws Exception {

		logger.debug("=========================Category=========================");

		listCategory = service.Category();
		model.addAttribute("category", listCategory);

		return listCategory;
	}


	// 리스트 생성, 물품등록
	// http통신할때 post는 body에다 data를 집어넣어서 받아오는 용도 (프론트에서 data를 받아옴)
	// @RequestBody에 param에 있는 값을 매칭해서 보내줘라.
	@RequestMapping(value = "/item", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public @ResponseBody String Item(@RequestBody Map<String, Object> param, Authentication authentication) throws Exception {

		logger.debug("=========================Item=========================");
		logger.debug("param = " + param.toString());

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		// jsonPaserPser 클래스 객체를 만들고 해당 객체에
		JsonParser jparser = new JsonParser();

		// param의 id 오브젝트 -> 문자열 파싱 -> jsonElement 파싱
		JsonElement findItem = jparser.parse(param.get("head").toString());

		listDTO = gson.fromJson(findItem, ShoppingListDTO.class);
		listDTO.setuserId(userId);
		
		service.ItemHead(listDTO);

		JsonElement findItems = jparser.parse(param.get("body").toString());
		ItemDTO[] item = gson.fromJson(findItems.toString(), ItemDTO[].class);
		// asList => 배열을 리스트로 바꾸어줌/ java.util.Arrays.ArrayList (Arrays클래스의 메소드로 ArrayList로 바꾸어줌, 사이즈 고정)/ java.util.ArrayList 클래스와는 다른 클래스
		List<ItemDTO> items = Arrays.asList(item);

		service.Item(items);

		return "success";
	}

	// 리스트, 아이템 전체조회
	@RequestMapping("/viewitems")
	public Object ViewTotalShopping(Authentication authentication, Model model) throws Exception {

		logger.debug("=========================ViewTotalShopping=========================");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		Object total = service.ViewTotalShopping(userId);
		model.addAttribute(total);

		return total;
	}

	// 리스트, 아이템 상세조회
	@RequestMapping("/viewitem")
	public JSONObject ViewShoppingItem(Authentication authentication, @RequestParam(value = "list_id") Integer list_id) throws Exception {

		logger.debug("=========================ViewShoppingItem=========================");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();

		JSONObject detail = service.ViewShoppingItem(userId, list_id);

		return detail;

	}

	// 리스트 수정
	@RequestMapping(value = "/edititem", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public @ResponseBody String EditItem(@RequestBody Map<String, Object> param, Authentication authentication) throws Exception {

		logger.debug("=========================EditItem=========================");
		logger.debug("param = " + param.toString());

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		JsonParser jparser = new JsonParser();

		JsonElement editItem = jparser.parse(param.get("head").toString());
		listDTO = gson.fromJson(editItem, ShoppingListDTO.class);
		listDTO.setuserId(userId);
		service.EditItemHead(listDTO);

		JsonElement editItems = jparser.parse(param.get("body").toString());
		ItemDTO[] item = gson.fromJson(editItems.toString(), ItemDTO[].class);
		List<ItemDTO> items = Arrays.asList(item);
		service.EditItem(items);

		return "success";
	}


	// 물품 삭제
	@RequestMapping(value = "/deleteitem", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public @ResponseBody String DeleteItem(@RequestBody List<Map<String, Object>> param) throws Exception {

		logger.debug("=========================DeleteItem=========================");

		//구조: [{},{}]	
		ShoppingItemDTO[] array = gson.fromJson(param.toString(), ShoppingItemDTO[].class);
		List<ShoppingItemDTO> item = Arrays.asList(array);
		service.DeleteItem(item);

		return "success";

	}
}
