package com.hkgiau.custom;

public class User {

	private int id;
	private String name;
	private String pass;
	private String fullname;
	private String email;
	private int status;
	private int permit;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String name,String pass, String fullname,String email
			,int status, int permit) {
		// TODO Auto-generated constructor stub
		this.name=name; this.pass=pass; this.fullname=fullname;
		this.email=email; this.status=status; this.permit=permit;
	}
	
	public void setID(int id)
	{
		this.id=id;
	}
	public int getID()
	{
		return id;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;
	}
	public void setPass(String pass)
	{
		this.pass=pass;
	}
	public String getPass()
	{
		return pass;
	}
	public void setFullname(String fullname)
	{
		this.fullname=fullname;
	}
	public String getFullname()
	{
		return fullname;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public String getEmail()
	{
		return email;
	}
	public void setStatus(int status)
	{
		this.status=status;
	}
	public int getStatus()
	{
		return status;
	}
	public void setPermit(int permit)
	{
		this.permit=permit;
	}
	public int getPermit()
	{
		return permit;
	}
}
