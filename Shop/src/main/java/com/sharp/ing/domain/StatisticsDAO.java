package com.sharp.ing.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("StatisticsDAO")
public interface StatisticsDAO {
	
	Logger logger = LoggerFactory.getLogger("com.sharp.ing.domain.StatisticsDAO");
	
	// 전체 소비 평균 값
	public List<ShoppingListDTO> totalAvg() throws Exception;
	
	// 사용자 소비 평균 값
	public List<ShoppingListDTO> userAvg(String userId) throws Exception;

	//카테고리별 통계
	public List<CategoryAvgDTO> categoryAvg3(String userId) throws Exception;
	
	//카테고리별 6개월 통계
	public List<CategoryAvgDTO> categoryAvg6(String userId) throws Exception;
	
	//카테고리별 12개월 통계
	public List<CategoryAvgDTO> categoryAvg12(String userId) throws Exception;
}
