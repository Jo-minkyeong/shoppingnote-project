package com.sharp.ing.domain;

public class ShoppingItemDTO {
	
	private int list_id;
	private int item_no;
	private String purchase_date;
	private String code04_vl;
	private String mart_name;
	private String mart_code;
	private int price;
	private int amount;
	private float qt;
	private String qt_code;
	

	public int getList_id() {
		return list_id;
	}
	public void setList_id(int list_id) {
		this.list_id = list_id;
	}
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}
	public String getCode04_vl() {
		return code04_vl;
	}
	public void setCode04_vl(String code04_vl) {
		this.code04_vl = code04_vl;
	}
	public String getMart_name() {
		return mart_name;
	}
	public void setMart_name(String mart_name) {
		this.mart_name = mart_name;
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
	public float getQt() {
		return qt;
	}
	public void setQt(float qt) {
		this.qt = qt;
	}
	public String getQt_code() {
		return qt_code;
	}
	public void setQt_code(String qt_code) {
		this.qt_code = qt_code;
	}

}
