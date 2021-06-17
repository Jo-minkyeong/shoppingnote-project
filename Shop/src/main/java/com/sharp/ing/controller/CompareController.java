package com.sharp.ing.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.ing.domain.CompareDTO;
import com.sharp.ing.service.CompareService;

@RestController
public class CompareController {
	
	Logger logger = LoggerFactory.getLogger("com.sharp.ing.domain.CompareController");

	JSONObject jsonObject = new JSONObject();
	JSONObject data = new JSONObject();
	
	//필드
	private CompareService service;
	
	List<CompareDTO> listCompare;
	
	//생성자
	@Autowired
	public CompareController(CompareService service) {
		this.service = service;
	}
	
	@RequestMapping(value="/compare")
	public JSONObject Compare(@RequestParam(value = "code01") int code01, @RequestParam(value = "code02") int code02, 
			@RequestParam(value = "code03") int code03, @RequestParam(value = "code04") int code04, Model model) throws Exception {
		
		service.Compare(code01, code02, code03, code04);
		
		listCompare = service.Compare(code01, code02, code03, code04);
		model.addAttribute(model);
		data.put("Compare", listCompare);
		
		return data;
	}
	
}
