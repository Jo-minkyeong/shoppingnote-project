package com.sharp.ing.domain;

import org.springframework.stereotype.Component;

@Component("listDTO")
public class Shopping_listDTO {
	
	// 사용자_id
	private String userId;
	// 영수증 번호
	public int list_id;
	// 구매날짜
	public String purchase_date;
	// 가격 (Average에서 사용)
	public int price;
	

	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

	public String getuserId() {
		return userId;
	}

	public void setuserId(String userId) {
		this.userId = userId;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

}
