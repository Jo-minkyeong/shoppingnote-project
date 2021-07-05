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
import com.sharp.ing.domain.Shopping_listDTO;
import com.sharp.ing.service.StatisticsService;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
public class StatisticsController {

	Logger logger = LoggerFactory.getLogger("com.sharp.ing.controller.StatisticsController");
	
	JSONObject jsonObject = new JSONObject();
	JSONObject Avgdata = new JSONObject();
	JSONObject categoryAvgData = new JSONObject();
	
	//필드
	private StatisticsService service;
	
	List<Shopping_listDTO> totalAverage;
	List<Shopping_listDTO> userAverage;
	List<CategoryAvgDTO> listAvgCategory3;
	List<CategoryAvgDTO> listAvgCategory6;
	List<CategoryAvgDTO> listAvgCategory12;


	//생성자
	@Autowired
	public StatisticsController(StatisticsService service) {
		this.service = service;
	}

	
	// 전체 소비 평균 값
//	@RequestMapping(value = "/totalAverage")
//	public JSONObject TotalAvg(Model model) throws Exception {
//		totalAverage = service.TotalAvg();
//		model.addAttribute("TotalAverage", totalAverage);
//		data.put("key", totalAverage);
//		logger.debug("=========================totalAverage=========================");
//		return data;
//	}

	// 소비 평균 값
	@RequestMapping(value = "/average")
	public JSONObject UserAvg(@RequestParam(value = "userId") String userId, Model model) throws Exception {
		service.UserAvg(userId);
		
		totalAverage = service.TotalAvg();
		userAverage = service.UserAvg(userId);
		
		model.addAttribute("TotalAverage", totalAverage);
		model.addAttribute("UserAverage", userAverage);
		
		Avgdata.put("totalAverage", totalAverage);
		Avgdata.put("userAverage", userAverage);
		logger.debug("=========================Average=========================");

		return Avgdata;
	}

	// 사용자 소비 평균 값
//	@RequestMapping(value = "/userAverage")
//	public List<AverageDTO> userAverage(@RequestParam(value = "userId") String userId, Model model)throws Exception{
//		
//		service.UserAverage(userId);
//		listUserAverage = service.UserAverage(userId);
//		model.addAttribute("UserAverage", listUserAverage);
//		return listUserAverage;
//	}
	
	
	// 카테고리별 통계
	@RequestMapping(value = "/category-average")
	public JSONObject CategoryAvg(@RequestParam(value = "userId") String userId, Model model) throws Exception {
		service.CategoryAvg3(userId);
		service.CategoryAvg6(userId);
		service.CategoryAvg12(userId);
		
		listAvgCategory3 = service.CategoryAvg3(userId);
		listAvgCategory6 = service.CategoryAvg6(userId);
		listAvgCategory12 = service.CategoryAvg12(userId);
		
		model.addAttribute("CategoryAverage", listAvgCategory3);
		model.addAttribute("CategoryAverage", listAvgCategory6);
		model.addAttribute("CategoryAverage", listAvgCategory12);
		
		categoryAvgData.put("CategoryAverage3", listAvgCategory3);
		categoryAvgData.put("CategoryAverage6", listAvgCategory6);
		categoryAvgData.put("CategoryAverage12", listAvgCategory12);
		logger.debug("=========================categoryAverage=========================");

		return categoryAvgData;
	}

}
