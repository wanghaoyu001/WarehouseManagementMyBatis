package com.dao;

import java.sql.*;
import java.util.*;
import com.pojo.*;

public class ZhuceDao implements IZhuceDao{
	private ResultSet rst;
	public boolean save(Zhuce zhuce) {
		String sql1="insert into userinfo(uid,upassword,utype,uname,usex,utel,upicname,powerPurchase,powerSale,powerInventoryview,powerProfit,powerWarehouseManage,powerAllocationManage,powerCustomerManage,powerStaffManage,remarks) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int rows1=DbHelperDao.executeUpdate(sql1, new Object[]{
				zhuce.getUid(),
				zhuce.getUpassword(),
				zhuce.getUtype(),
				zhuce.getUname(),
				zhuce.getUsex(),
				zhuce.getUtel(),
				zhuce.getUpicname(),
				zhuce.getPowerPurchase(),
				zhuce.getPowerSale(),
				zhuce.getPowerInventoryview(),
				zhuce.getPowerProfit(),
				zhuce.getPowerWarehouseManage(),
				zhuce.getPowerAllocationManage(),
				zhuce.getPowerCustomerManage(),
				zhuce.getPowerStaffManage(),
				zhuce.getRemarks()
		});
		String sql2="insert into login(Uid,Upassword,Utype) values(?,?,?)";
		int rows2=DbHelperDao.executeUpdate(sql2, new Object[]{
				zhuce.getUid(),
				zhuce.getUpassword(),
				zhuce.getUtype()
				
		});
		if(rows1>0&&rows2>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Zhuce zhuce) {
		String sql="update student set sname=?,sex=?,address=?,birthday=?,fname=?,classid=? where stuid=?";
		int rows=DbHelperDao.executeUpdate(sql, new Object[]{
				zhuce.getUid()
		});
		if(rows>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delById(Integer Uid) {
	String sql="delete from student where stuid=?";
	int rows=DbHelperDao.executeUpdate(sql, new Object[]{Uid});
	if(rows>0){
		return true;
	}
		return false;
	}

	@Override
	public Zhuce findById(String Uid) {
		String sql="select * from userinfo inner join utype on userinfo.utype=utype.utypes where uid=?";
		rst=DbHelperDao.executeQuery(sql, new Object[]{Uid});
		try {
			if(rst.next()){
				Zhuce zhuce = new Zhuce(
					rst.getString(2),
					rst.getString(3),
					rst.getInt(4),
					rst.getString(5),
					rst.getString(6),
					rst.getString(7),
					rst.getString(8),
					rst.getInt(9),
					rst.getInt(10),
					rst.getInt(11),
					rst.getInt(12),
					rst.getInt(13),
					rst.getInt(14),
					rst.getInt(15),
					rst.getInt(16),
					rst.getString(17),
					rst.getString(19)
				);
				return zhuce;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Zhuce> findAll() {
		List<Zhuce> ltZhuce=new ArrayList<Zhuce>();
		String sql="select * from userinfo inner join utype on userinfo.utype=utype.utypes order by uid";
		rst=DbHelperDao.executeQuery(sql,null);
		try {
			while(rst.next()){
				Zhuce zhuce=new Zhuce(
						rst.getString(2),
						rst.getString(3),
						rst.getInt(4),
						rst.getString(5),
						rst.getString(6),
						rst.getString(7),
						rst.getString(8),
						rst.getInt(9),
						rst.getInt(10),
						rst.getInt(11),
						rst.getInt(12),
						rst.getInt(13),
						rst.getInt(14),
						rst.getInt(15),
						rst.getInt(16),
						rst.getString(17),
						rst.getString(19)
						);
				ltZhuce.add(zhuce);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ltZhuce;
	}

	public List<Utype> doinit() {
		List<Utype> listUtype=new ArrayList<Utype>();
		String sql="select * from Utype";
		rst=DbHelperDao.executeQuery(sql,null);
		try {
			while(rst.next()){
				Utype type=new Utype(
						rst.getInt(1),
						rst.getString(2)
						);
				listUtype.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUtype;
	}
	//获取员工信息总条数
	public int getTotalCount() {
		int total =0;
		String sql = "select count(1) from userinfo inner join utype on userinfo.utype=utype.utypes order by uid";
		rst=DbHelperDao.executeQuery(sql,null);
		try {
			while(rst.next()){
				total = rst.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	//分页获取员工信息数据
	//pageNo当前页码 pageSize页面容量
	public List<Zhuce> getPageZhuceList(int pageNo,int pageSize){
		List<Zhuce> ltZhuce=new ArrayList<Zhuce>();
		String sql = "select * from userinfo inner join utype on userinfo.utype=utype.utypes order by id limit ?,?";
		rst=DbHelperDao.executeQuery(sql,new Object[]{(pageNo-1)*pageSize,pageSize});
		try {
			while(rst.next()){
				Zhuce zhuce = new Zhuce(
					rst.getString(1),
					rst.getString(2),
					rst.getString(3),
					rst.getInt(4),
					rst.getString(19),
					rst.getString(5),
					rst.getString(6),
					rst.getString(7),
					rst.getString(8),
					rst.getInt(9),
					rst.getInt(10),
					rst.getInt(11),
					rst.getInt(12),
					rst.getInt(13),
					rst.getInt(14),
					rst.getInt(15),
					rst.getInt(16),
					rst.getString(17)
				);
				ltZhuce.add(zhuce);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ltZhuce;
	}
	

}
