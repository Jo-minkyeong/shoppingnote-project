package com.sharp.ing.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.ing.domain.FirebaseDTO;
import com.sharp.ing.service.PurchaseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseController {

	private PurchaseService service;

	List<JSONObject> purchase;
	List<JSONObject> recentPurchase;

	@Autowired
	public PurchaseController(PurchaseService service) {
		this.service = service;
	}

	//구매 임박 목록
	@RequestMapping(value = "/purchase")
	public List<JSONObject> Purchase(Authentication authentication, Model model) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername(); 
		purchase = service.Purchase(userId);	
		model.addAttribute("UserPurchase", purchase);	
		return purchase;
	}

	//최근 쇼핑 목록
	@RequestMapping(value = "/recentpurchase")
	public List<JSONObject> RecentPurchase(Authentication authentication, Model model) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername(); 
		recentPurchase = service.RecentPurchase(userId);	
		model.addAttribute("UserPurchase", recentPurchase);	
		return recentPurchase;	
	}

	//구매임박품목 푸시알림
	@RequestMapping(value="/push")
	public String testFb(Authentication authentication) throws Exception{
		// 모든 유저 아이디, 토큰 받아오기
		List<FirebaseDTO> idAndTokenList = service.getUserIdAndFirebaseToken();
		for(FirebaseDTO item : idAndTokenList) {
			// 푸시알림 메시지내용을 위해 유저아이디로 구매임박품목 뽑기
			purchase = service.Purchase("TestYeram");
			System.out.println(item.getUserId());
			System.out.println(purchase);
			// 구매임박품목의 갯수와 파이어베이스 토큰 전달
			if(purchase.size()>0) {				
				service.sendToToken(item.getFb_token(), purchase.size());
			}
		}
		return "A";
	}

	//알림 차단
	@RequestMapping(value="/denied")
	public String testFb1(Authentication authentication) throws Exception{
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername(); 
		service.deleteFbToken(userId);
		return "success";
	}
	//알림 허용
	@RequestMapping(value="/allow")
	public String testFb2(Authentication authentication, String fbToken) throws Exception{
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername(); 
		service.insertFbToken(userId,fbToken);
		return "success";
	}


}
