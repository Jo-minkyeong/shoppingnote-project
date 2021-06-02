package com.sharp.ing.domain;

import org.springframework.stereotype.Component;

@Component("itemDTO")
public class ItemDTO {
	// 물품 번호
	public int item_no;
	// 영수증 번호
	public int list_id;
	// 마트코드
	private String mart_code;
	// 가격
	public int price;
	// 수량
	public int amount;
	// 대분류 코드
	private int code01;
	// 중분류 코드
	private int code02;
	// 소분류 코드
	private int code03;
	// 세분류 코드
	private int code04;
	// 용량 단위 코드
	public String qt_code;
	// 용량 적어주는 칸
	public int qt;

	public int getItem_no() {
		return item_no;
	}

	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	
	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}
	
	public String getMart_code() {
		return mart_code;
	}

	public void setMart_code(String mart_code) {
		this.mart_code = mart_code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCode01() {
		return code01;
	}

	public void setCode01(int code01) {
		this.code01 = code01;
	}

	public int getCode02() {
		return code02;
	}

	public void setCode02(int code02) {
		this.code02 = code02;
	}

	public int getCode03() {
		return code03;
	}

	public void setCode03(int code03) {
		this.code03 = code03;
	}

	public int getCode04() {
		return code04;
	}

	public void setCode04(int code04) {
		this.code04 = code04;
	}

	public String getQt_code() {
		return qt_code;
	}

	public void setQt_code(String qt_code) {
		this.qt_code = qt_code;
	}

	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
		this.qt = qt;
	}

}
