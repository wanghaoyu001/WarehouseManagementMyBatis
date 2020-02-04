package com.pojo;

import java.io.Serializable;

public class Utype implements Serializable {
	private static final long serialVersionUID = 3058776787871408945L;
	private int utypes;//用户类型
	private String utypeName;//用户类型名
	
	public Utype() {
		super();
		// TODO 自动生成的构造函数存根
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
