package com.sharp.ing.domain;

import org.springframework.stereotype.Component;

@Component("categoryDTO")
public class CategoryDTO {

	private String code01_vl;
	private int code01;

	private String code02_vl;
	private int code02;

	private String code03_vl;
	private int code03;

	private String code04_vl;
	private int code04;


	public String getCode01_vl() {
		return code01_vl;
	}

	public void setCode01_vl(String code01_vl) {
		this.code01_vl = code01_vl;
	}

	public int getCode01() {
		return code01;
	}

	public void setCode01(int code01) {
		this.code01 = code01;
	}

	public String getCode02_vl() {
		return code02_vl;
	}

	public void setCode02_vl(String code02_vl) {
		this.code02_vl = code02_vl;
	}

	public int getCode02() {
		return code02;
	}

	public void setCode02(int code02) {
		this.code02 = code02;
	}

	public String getCode03_vl() {
		return code03_vl;
	}

	public void setCode03_vl(String code03_vl) {
		this.code03_vl = code03_vl;
	}

	public int getCode03() {
		return code03;
	}

	public void setCode03(int code03) {
		this.code03 = code03;
	}

	public String getCode04_vl() {
		return code04_vl;
	}

	public void setCode04_vl(String code04_vl) {
		this.code04_vl = code04_vl;
	}

	public int getCode04() {
		return code04;
	}

	public void setCode04(int code04) {
		this.code04 = code04;
	}

}