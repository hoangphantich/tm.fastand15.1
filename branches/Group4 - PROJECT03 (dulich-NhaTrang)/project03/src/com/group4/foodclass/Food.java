package com.group4.foodclass;

import java.io.Serializable;

public class Food implements Serializable{
	private static final long serialVersionUID = 1L;
	public int cat;
	public String subject;
	public String titles;
	public int code;
	public String tp;
	public String ccb;
	public String vh;
	
	public Food(int cat, String subject, String title, int code,
			String tp, String cbb, String vh) {
		super();
		this.cat = cat;
		this.subject = subject;
		this.titles = title;
		this.code = code;
		this.tp = tp;
		this.ccb = cbb;
		this.vh = vh;
	}

	public Food() {
		super();
	}
	
	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getCbb() {
		return ccb;
	}

	public void setCbb(String cbb) {
		this.ccb = cbb;
	}

	public String getVh() {
		return vh;
	}

	public void setVh(String vh) {
		this.vh = vh;
	}	
}
