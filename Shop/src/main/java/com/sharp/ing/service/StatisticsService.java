package com.sharp.ing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sharp.ing.domain.CategoryAvgDTO;
import com.sharp.ing.domain.ShoppingListDTO;
import com.sharp.ing.domain.StatisticsDAO;

@Service("StatisticsService")
public class StatisticsService {

	Logger logger = LoggerFactory.getLogger("com.sharp.ing.controller.StatisticsService");

	private StatisticsDAO statisticsDAO;

	@Autowired
	public StatisticsService(StatisticsDAO statisticsDAO) {
		this.statisticsDAO = statisticsDAO;
	}

	// 전체 소비 평균 값
//	@Scheduled(fixedDelay = 1000)
	public List<ShoppingListDTO> TotalAvg() throws Exception {
		List<ShoppingListDTO> totalAvg = statisticsDAO.totalAvg();
		return totalAvg;
	}

	// 사용자 소비 평균 값
	public List<ShoppingListDTO> UserAvg(String userId) throws Exception {
		List<ShoppingListDTO> userAvg = statisticsDAO.userAvg(userId);
		return userAvg;
	}

	// 카테고리별 통계
	public List<CategoryAvgDTO> CategoryAvg3(String userId) throws Exception {
		List<CategoryAvgDTO> categoryAvg3 = statisticsDAO.categoryAvg3(userId);
		return categoryAvg3;
	}

	// 카테고리별 6개월 통계
	public List<CategoryAvgDTO> CategoryAvg6(String userId) throws Exception {
		List<CategoryAvgDTO> categoryAvg6 = statisticsDAO.categoryAvg6(userId);
		return categoryAvg6;
	}

	// 카테고리별 12개월 통계
	public List<CategoryAvgDTO> CategoryAvg12(String userId) throws Exception {
		List<CategoryAvgDTO> categoryAvg12 = statisticsDAO.categoryAvg12(userId);
		return categoryAvg12;
	}
}
