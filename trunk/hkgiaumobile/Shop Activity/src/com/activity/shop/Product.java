package com.activity.shop;

import java.io.Serializable;

public class Product implements Serializable{

	/**
	 * Serializable transfer a object between activity
	 */
	private static final long serialVersionUID = 3782502608428335803L;
	private String product;
	private int price;
	private int quantity;
	private String picture;
	
	public Product(String product, int price, String picture)
	{
		super();
		this.product=product;
		this.price=price;
		this.picture=picture;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public void setProduct(String product)
	{
		this.product=product;
	}
	public String getProduct()
	{
		return product;
	}
	public void setPrice(int price)
	{
		this.price=price;
	}
	public int getPrice()
	{
		return price;
	}
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setPicture(String picture)
	{
		this.picture=picture;
	}
	public String getPicture()
	{
		return picture;
	}

}
