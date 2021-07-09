package com.sharp.ing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharp.ing.domain.CompareDAO;
import com.sharp.ing.domain.CompareDTO;

@Service("CompareService")
public class CompareService {
	
	Logger logger = LoggerFactory.getLogger("com.sharp.ing.service.CompareService");

	private CompareDAO compareDAO;

	@Autowired
	public CompareService(CompareDAO compareDAO) {
		this.compareDAO = compareDAO;
	}
	
	public List<CompareDTO> Compare(int code01, int code02, int code03, int code04) throws Exception{
		List<CompareDTO> listCompare = compareDAO.compare(code01, code02, code03, code04);
		return listCompare;
	}
	
}