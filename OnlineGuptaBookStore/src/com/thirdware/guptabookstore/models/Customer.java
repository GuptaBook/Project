package com.thirdware.guptabookstore.models;


public class Customer {
	private int cid;
	private String cname;
	private String email;
	private String phoneno;
	private int roleid;
	private String password;
	
	public int getCid() {
		return cid;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
