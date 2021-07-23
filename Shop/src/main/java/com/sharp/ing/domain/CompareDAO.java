package com.sharp.ing.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("CompareDAO")
public interface CompareDAO {

	Logger logger = LoggerFactory.getLogger("com.sharp.ing.domain.CompareDAO");


	public List<CompareDTO> compare(int code01, int code02, int code03, int code04) throws Exception;

	public List<CompareDTO> recentList(String userId) throws Exception;

	public int avgPrice(int item_no) throws Exception;

	// 전체 아이템별 일평균사용량
	public float TotalAverage(int item_no) throws Exception;

	// 전체 아이템별 총 용량(total_qt)
	public float TotalQt(int item_no) throws Exception;

	// 유저별 아이템별 일평균사용량
	public CompareDTO userAverage(String userId, int item_no) throws Exception;

	// 유저별 아이템별 총 용량(total_qt)
	public CompareDTO userQt(String userId, int item_no) throws Exception;
}
