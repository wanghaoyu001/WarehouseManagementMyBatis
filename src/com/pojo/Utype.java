package com.pojo;

import java.io.Serializable;

public class Utype implements Serializable {
	private static final long serialVersionUID = 3058776787871408945L;
	private int utypes;//�û�����
	private String utypeName;//�û�������
	
	public Utype() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public Utype(int utypes, String utypeName) {
		super();
		this.utypes = utypes;
		this.utypeName = utypeName;
	}

	public int getUtypes() {
		return utypes;
	}

	public void setUtypes(int utypes) {
		this.utypes = utypes;
	}

	public String getUtypeName() {
		return utypeName;
	}

	public void setUtypeName(String utypeName) {
		this.utypeName = utypeName;
	}
	
}
