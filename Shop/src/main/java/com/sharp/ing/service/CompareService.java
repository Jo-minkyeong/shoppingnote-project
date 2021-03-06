package com.sharp.ing.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharp.ing.domain.CompareDAO;
import com.sharp.ing.domain.CompareDTO;
import com.sharp.ing.domain.NoteDTO;
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
	
	//분석
	public List<CompareDTO> analysis(String userId) throws Exception {
		
		int item_no;
		
		List<CompareDTO> recentlist = compareDAO.recentList(userId);
		List analysis = new ArrayList<>();
		
		
		int difference;
		float QT;
		float average;
		
		
		//구조 : [{},{}]
		for (CompareDTO item : recentlist) {
			
			item_no = item.getItem_no();
			difference = compareDAO.avgPrice(item_no);
			QT = compareDAO.TotalQt(item_no);
			average = compareDAO.TotalAverage(item_no);
			
			int qt_average = (int) Math.round(Double.valueOf(QT/average));
			CompareDTO userQt = compareDAO.userQt(userId, item_no);
			CompareDTO userAverage = compareDAO.userAverage(userId, item_no);
		
			int userQT_average = (int) Math.round(Double.valueOf(userQt.getUser_qt()/userAverage.getUser_average()));
			
			int tmp = (int) Math.round((qt_average-userQT_average));

			String result = "";
			if(tmp>0) {
				result="+"+tmp;
			} else if(tmp==0) {
				result="-";
			}else {
				result=""+tmp;
			}
			
			String test = "";
			if(difference>0) {
				test="+"+difference;
			}else if(difference==0) {
				test="-";
			}else {
				test=""+difference;
			}
			
			Map<String, Object> map = new Hashtable<>();
			
			map.put("code04_vl",item.getCode04_vl());
			map.put("qt",item.getQt());
			map.put("qt_code",item.getQt_code());
			map.put("amount",item.getAmount());
			map.put("purchase_date",item.getPurchase_date());
			map.put("price",item.getPrice());
			map.put("price_difference",test);
			map.put("purchase_period",result);
	
			logger.debug("analysis: "+analysis);
			
			analysis.add(map);
		}
		return analysis;
	}
	
}