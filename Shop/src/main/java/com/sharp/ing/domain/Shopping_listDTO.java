package com.sharp.ing.domain;

import org.springframework.stereotype.Component;

@Component("listDTO")
public class Shopping_listDTO {
	// 사용자_id
	private String user_id;
	// 영수증 번호
	public int list_id;
	// 구매날짜
	public String purchase_date;

	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

}