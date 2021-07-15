package com.sharp.ing.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharp.ing.domain.CompareDAO;
import com.sharp.ing.domain.CompareDTO;
import com.sharp.ing.domain.ItemDTO;
import com.sharp.ing.domain.PurchaseDTO;

@Service("CompareService")
public class CompareService {
	
	Logger logger = LoggerFactory.getLogger("com.sharp.ing.service.CompareService");

	JSONObject data = new JSONObject();
	
	//필드 
	private CompareDAO compareDAO;

	@Autowired
	public CompareService(CompareDAO compareDAO) {
		this.compareDAO = compareDAO;
	}
	
	public List<CompareDTO> Compare(int code01, int code02, int code03, int code04) throws Exception{
		List<CompareDTO> listCompare = compareDAO.compare(code01, code02, code03, code04);
		return listCompare;
	}
	
	
	public List<PurchaseDTO> analysis(String userId) throws Exception {
		
		List<PurchaseDTO> recentlist = compareDAO.recentList(userId);
		int difference = compareDAO.avgPrice();
		
		
		
		
		for (PurchaseDTO item : recentlist) {
			item.setItem_no(difference);
		}
		
		return recentlist ;
		
	}
	
//	public JSONObject Difference(int item_no) throws Exception{
//			
//			data.put("d_price", difference);
//			
//		return data;
//	}
	
	
}
