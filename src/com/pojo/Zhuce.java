package com.pojo;

import java.io.Serializable;
//ע��ҳ��
public class Zhuce implements Serializable{
	private static final long serialVersionUID = 7268007289325855840L;
	
    private String id;//���
	private String uid;//�˺�
	private String upassword;//����
	private int utype;//�û�����
	private String utypeName;//�û�������
	private String uname;//����
	private String usex;//�Ա�
	private String utel;//�绰
	private String upicname;//ͼƬ��
	private int powerPurchase;//�ɹ�Ȩ��
	private int powerSale;//����Ȩ��
	private int powerInventoryview;//���鿴Ȩ��
	private int powerProfit;//����鿴Ȩ��
	private int powerWarehouseManage;//�ֿ����Ȩ��
	private int powerAllocationManage;//�ֿ����Ȩ��
	private int powerCustomerManage;//�ͻ�����Ȩ��
	private int powerStaffManage;//Ա������Ȩ��
	private String remarks;//��ע
	
	
	public Zhuce() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public Zhuce(String id, String uid, String upassword, int utype, String utypeName, String uname, String usex,
			String utel, String upicname, int powerPurchase, int powerSale, int powerInventoryview, int powerProfit,
			int powerWarehouseManage, int powerAllocationManage, int powerCustomerManage, int powerStaffManage,
			String remarks) {
		super();
		this.id = id;
		this.uid = uid;
		this.upassword = upassword;
		this.utype = utype;
		this.utypeName = utypeName;
		this.uname = uname;
		this.usex = usex;
		this.utel = utel;
		this.upicname = upicname;
		this.powerPurchase = powerPurchase;
		this.powerSale = powerSale;
		this.powerInventoryview = powerInventoryview;
		this.powerProfit = powerProfit;
		this.powerWarehouseManage = powerWarehouseManage;
		this.powerAllocationManage = powerAllocationManage;
		this.powerCustomerManage = powerCustomerManage;
		this.powerStaffManage = powerStaffManage;
		this.remarks = remarks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Zhuce(String uid, String upassword, int utype, String uname, String usex, String utel, String upicname,
			int powerPurchase, int powerSale, int powerInventoryview, int powerProfit, int powerWarehouseManage,
			int powerAllocationManage, int powerCustomerManage, int powerStaffManage, String remarks) {
		super();
		this.uid = uid;
		this.upassword = upassword;
		this.utype = utype;
		this.uname = uname;
		this.usex = usex;
		this.utel = utel;
		this.upicname = upicname;
		this.powerPurchase = powerPurchase;
		this.powerSale = powerSale;
		this.powerInventoryview = powerInventoryview;
		this.powerProfit = powerProfit;
		this.powerWarehouseManage = powerWarehouseManage;
		this.powerAllocationManage = powerAllocationManage;
		this.powerCustomerManage = powerCustomerManage;
		this.powerStaffManage = powerStaffManage;
		this.remarks = remarks;
	}

	public Zhuce(String uid, String upassword, int utype, String uname, String usex, String utel,
			String upicname, int powerPurchase, int powerSale, int powerInventoryview, int powerProfit,
			int powerWarehouseManage, int powerAllocationManage, int powerCustomerManage, int powerStaffManage,
			String remarks, String utypeName) {
		super();
		this.uid = uid;
		this.upassword = upassword;
		this.utype = utype;
		this.utypeName = utypeName;
		this.uname = uname;
		this.usex = usex;
		this.utel = utel;
		this.upicname = upicname;
		this.powerPurchase = powerPurchase;
		this.powerSale = powerSale;
		this.powerInventoryview = powerInventoryview;
		this.powerProfit = powerProfit;
		this.powerWarehouseManage = powerWarehouseManage;
		this.powerAllocationManage = powerAllocationManage;
		this.powerCustomerManage = powerCustomerManage;
		this.powerStaffManage = powerStaffManage;
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "Zhuce [uid=" + uid + ", upassword=" + upassword + ", utype=" + utype + ", utypeName=" + utypeName
				+ ", uname=" + uname + ", usex=" + usex + ", utel=" + utel + ", upicname=" + upicname
				+ ", powerPurchase=" + powerPurchase + ", powerSale=" + powerSale + ", powerInventoryview="
				+ powerInventoryview + ", powerProfit=" + powerProfit + ", powerWarehouseManage=" + powerWarehouseManage
				+ ", powerAllocationManage=" + powerAllocationManage + ", powerCustomerManage=" + powerCustomerManage
				+ ", powerStaffManage=" + powerStaffManage + ", remarks=" + remarks + "]";
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public int getUtype() {
		return utype;
	}
	public void setUtype(int utype) {
		this.utype = utype;
	}
	public String getUtypeName() {
		return utypeName;
	}
	public void setUtypeName(String utypeName) {
		this.utypeName = utypeName;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUpicname() {
		return upicname;
	}
	public void setUpicname(String upicname) {
		this.upicname = upicname;
	}
	public int getPowerPurchase() {
		return powerPurchase;
	}
	public void setPowerPurchase(int powerPurchase) {
		this.powerPurchase = powerPurchase;
	}
	public int getPowerSale() {
		return powerSale;
	}
	public void setPowerSale(int powerSale) {
		this.powerSale = powerSale;
	}
	public int getPowerInventoryview() {
		return powerInventoryview;
	}
	public void setPowerInventoryview(int powerInventoryview) {
		this.powerInventoryview = powerInventoryview;
	}
	public int getPowerProfit() {
		return powerProfit;
	}
	public void setPowerProfit(int powerProfit) {
		this.powerProfit = powerProfit;
	}
	public int getPowerWarehouseManage() {
		return powerWarehouseManage;
	}
	public void setPowerWarehouseManage(int powerWarehouseManage) {
		this.powerWarehouseManage = powerWarehouseManage;
	}
	public int getPowerAllocationManage() {
		return powerAllocationManage;
	}
	public void setPowerAllocationManage(int powerAllocationManage) {
		this.powerAllocationManage = powerAllocationManage;
	}
	public int getPowerCustomerManage() {
		return powerCustomerManage;
	}
	public void setPowerCustomerManage(int powerCustomerManage) {
		this.powerCustomerManage = powerCustomerManage;
	}
	public int getPowerStaffManage() {
		return powerStaffManage;
	}
	public void setPowerStaffManage(int powerStaffManage) {
		this.powerStaffManage = powerStaffManage;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
