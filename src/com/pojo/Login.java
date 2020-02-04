package com.pojo;
//登录验证类
public class Login {
	private String Uid;
	private String Upassword;
	private int Utype;
	
	public Login() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Login(String uid, String upassword, int utype) {
		super();
		Uid = uid;
		Upassword = upassword;
		Utype = utype;
	}
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getUpassword() {
		return Upassword;
	}
	public void setUpassword(String upassword) {
		Upassword = upassword;
	}
	public int getUtype() {
		return Utype;
	}
	public void setUtype(int utype) {
		Utype = utype;
	}
	
}
