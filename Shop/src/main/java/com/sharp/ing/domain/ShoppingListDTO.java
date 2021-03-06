package com.sharp.ing.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sharp.ing.service.DataService;

@Component("listDTO")
public class ShoppingListDTO {
	
	// 사용자 번호 
	private Integer id;
	// 사용자 ID
	private String userId;
	// 영수증 번호
	public Integer list_id;
	// 구매날짜
	public String purchase_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getuserId() {
		return userId;
	}

	public void setuserId(String userId) {
		this.userId = userId;
	}

	public Integer getList_id() {
		return list_id;
	}

	public void setList_id(Integer list_id) {
		this.list_id = list_id;
	}
	
	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

}
