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
	
	public List<PurchaseDTO> recentList(String userId) throws Exception;
	
	public int avgPrice() throws Exception;
	
}
