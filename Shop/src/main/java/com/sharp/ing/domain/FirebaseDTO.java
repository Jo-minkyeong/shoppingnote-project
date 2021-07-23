package com.sharp.ing.domain;

import org.springframework.stereotype.Component;

@Component("FirebaseDTO")
public class FirebaseDTO {
	private String userId;
	private String fb_token;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFb_token() {
		return fb_token;
	}
	public void setFb_token(String fb_token) {
		this.fb_token = fb_token;
	}
}
