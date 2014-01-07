package com.hkgiau.custom;

public class Electric {

	String name;
	int price;
	int quantity;
	public Electric() {
		// TODO Auto-generated constructor stub
	}
	public Electric(String name, int price) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.price=price;
	}
	public void setData(String name, int price) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.price=price;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;
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
}
