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

import com.sharp.ing.domain.CategoryAvgDTO;
import com.sharp.ing.domain.ShoppingListDTO;
import com.sharp.ing.service.StatisticsService;

@RestController
@CrossOrigin(origins = "*")
public class StatisticsController {

	Logger logger = LoggerFactory.getLogger("com.sharp.ing.controller.StatisticsController");
	
	JSONObject jsonObject = new JSONObject();
	JSONObject avgData = new JSONObject();
	JSONObject categoryAvgData = new JSONObject();
	
	//필드
	private StatisticsService service;
	private List<ShoppingListDTO> totalAverage;
	private List<ShoppingListDTO> userAverage;
	private List<CategoryAvgDTO> listAvgCategory3;
	private List<CategoryAvgDTO> listAvgCategory6;
	private List<CategoryAvgDTO> listAvgCategory12;

	//생성자
	@Autowired
	public StatisticsController(StatisticsService service) {
		this.service = service;
	}

	// 소비 평균 값
	@RequestMapping(value = "/average")
	public JSONObject UserAvg(@RequestParam(value = "userId") String userId, Model model) throws Exception {
			
		totalAverage = service.TotalAvg();
		userAverage = service.UserAvg(userId);
		
		model.addAttribute("TotalAverage", totalAverage);
		model.addAttribute("UserAverage", userAverage);
		
		avgData.put("totalAverage", totalAverage);
		avgData.put("userAverage", userAverage);
		
		logger.debug("=========================Average=========================");

		return avgData;
	}
	
	// 카테고리별 통계
	@RequestMapping(value = "/category-average")
	public JSONObject CategoryAvg(@RequestParam(value = "userId") String userId, Model model) throws Exception {
		
		listAvgCategory3 = service.CategoryAvg3(userId);
		listAvgCategory6 = service.CategoryAvg6(userId);
		listAvgCategory12 = service.CategoryAvg12(userId);
		
		model.addAttribute("CategoryAverage3", listAvgCategory3);
		model.addAttribute("CategoryAverage6", listAvgCategory6);
		model.addAttribute("CategoryAverage12", listAvgCategory12);
		
		categoryAvgData.put("CategoryAverage3", listAvgCategory3);
		categoryAvgData.put("CategoryAverage6", listAvgCategory6);
		categoryAvgData.put("CategoryAverage12", listAvgCategory12);
		
		logger.debug("=========================categoryAverage=========================");

		return categoryAvgData;
	}

}
