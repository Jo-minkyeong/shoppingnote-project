package com.sharp.ing.service;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.sharp.ing.domain.FirebaseDTO;
import com.sharp.ing.domain.PurchaseDAO;
import com.sharp.ing.domain.PurchaseDTO;
@Service("PurchaseService")
public class PurchaseService {
	private PurchaseDAO purchaseDAO;
	@Autowired
	public PurchaseService(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}
	// 구매 임박 목록 조회
	public List<JSONObject> Purchase(String userId) throws Exception {
		List<PurchaseDTO> dailyAverage = purchaseDAO.dailyAverage(userId);
		List<PurchaseDTO> purchase = purchaseDAO.purchase(userId);
		List<JSONObject> resultList = new ArrayList<JSONObject>();
		for(int i=0;i<dailyAverage.size();i++) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(purchase.get(i).getPurchase_date());
			cal.add(Calendar.DATE, (int) Math.round(Double.valueOf(purchase.get(i).getTotal_qt())/dailyAverage.get(i).getDaily_average()));
			JSONObject updata = new JSONObject();
			Date time = new Date();				
			String today = df.format(time);			
			String repurchase = df.format(cal.getTime());
			Date Today = df.parse(today);
			Date Repurchase = df.parse(repurchase);
			long calDate = Today.getTime() - Repurchase.getTime(); 
			long calDateDays = calDate / ( 24*60*60*1000); 
			calDateDays = Math.abs(calDateDays);
			if(calDateDays<=7) {
				updata.put("code01", purchase.get(i).getCode01());
				updata.put("code02", purchase.get(i).getCode02());
				updata.put("code03", purchase.get(i).getCode03());
				updata.put("code04", purchase.get(i).getCode04());
				updata.put("code04_vl", purchase.get(i).getCode04_vl());
				updata.put("purchase_date", df.format(purchase.get(i).getPurchase_date()));  // 최근 구매일
				updata.put("Remaining Date", calDateDays); // 남은 일수 (현재 날짜 - 예상 구매일)
				updata.put("repurchase_date",repurchase);  // 예상 구매일
				resultList.add(updata);
			}
		}
		return resultList;
	}
	// 최근 구매 목록 조회
	public List<JSONObject> RecentPurchase(String userId) throws Exception {
		List<PurchaseDTO> recentPurchase = purchaseDAO.recentPurchase(userId);
		List<JSONObject> recentList = new ArrayList<JSONObject>();
		for(int i=0;i<recentPurchase.size();i++) {
			JSONObject recentdata = new JSONObject();
			recentdata.put("code01", recentPurchase.get(i).getCode01());
			recentdata.put("code02", recentPurchase.get(i).getCode02());
			recentdata.put("code03", recentPurchase.get(i).getCode03());
			recentdata.put("code04", recentPurchase.get(i).getCode04());
			recentdata.put("code04_vl", recentPurchase.get(i).getCode04_vl());
			recentList.add(recentdata);
		}
		return recentList;
	}
	//사용자 아이디,파이어베이스 토큰 가져오기
	public List<FirebaseDTO> getUserIdAndFirebaseToken() throws Exception{
		List<FirebaseDTO> idAndTokenList = purchaseDAO.getUserIdAndFirebaseToken();
		return idAndTokenList;
	}
	//푸시알림 보내기
	public void sendToToken(String fbToken, int itemCount) throws Exception{
		FileInputStream serviceAccount = null;
		FirebaseOptions options = null;
		serviceAccount = new FileInputStream("src/main/resources/static/bit-academy-project-a9c77-firebase-adminsdk-3stp7-ad4640314f.json");
		options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
		if(FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
		// [START send_to_token]
		// This registration token comes from the client FCM SDKs.
		String registrationToken = fbToken;
		// See documentation on defining a message payload.
		Message message =Message.builder()
				.setNotification(Notification.builder()
						.setTitle("알림")
						.setBody("구매 임박 물품이 "+itemCount+"개 있습니다.")
						.build())
				.setToken(registrationToken)
				.build();
		// Send a message to the device corresponding to the provided
		// registration token.
		String response = FirebaseMessaging.getInstance().send(message);
		// Response is a message ID string.
		System.out.println("Successfully sent message: " + response);
		// [END send_to_token]
	}

	//알림 차단
	public void deleteFbToken(String userId) throws Exception{
		purchaseDAO.deleteFbToken(userId);
	}
	//알림 허용
	public void insertFbToken(String userId, String fb_token) throws Exception{
		purchaseDAO.insertFbToken(userId,fb_token);
	}
}