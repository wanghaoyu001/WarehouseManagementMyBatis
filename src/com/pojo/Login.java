package com.pojo;
//��¼��֤��
public class Login {
	private String Uid;
	private String Upassword;
	private int Utype;
	
	public Login() {
		super();
		// TODO �Զ����ɵĹ��캯�����
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
