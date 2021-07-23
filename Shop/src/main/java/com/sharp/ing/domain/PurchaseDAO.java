package com.sharp.ing.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("purchaseDAO")
public interface PurchaseDAO {

	// 개인별 총 용량(total_qt) 구할 때
	public List<PurchaseDTO> purchase(String userId) throws Exception;

	// 개인별 일평균 사용량 구할 때
	public List<PurchaseDTO> dailyAverage(String userId) throws Exception;

	// 최근구매목록 조회
	public List<PurchaseDTO> recentPurchase(String userId) throws Exception;

	// push알림을 위한 모든 userId, firebase_token 조회
	public List<FirebaseDTO> getUserIdAndFirebaseToken() throws Exception;
	
	// 알림차단을 위한 토큰 삭제
	public void deleteFbToken(String userId) throws Exception;
	
	// 알림허용을 위한 토큰 추가
	public void insertFbToken(String userId,String fb_token) throws Exception;



}

