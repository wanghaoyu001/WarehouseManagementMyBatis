package com.biz;

import java.util.List;

import com.dao.IZhuceDao;
import com.dao.ZhuceDao;
import com.pojo.Utype;
import com.pojo.Zhuce;

public class ZhuceBiz implements IZhuceBiz{
	private IZhuceDao zhucedao=new ZhuceDao();
	public boolean save(Zhuce zhuce) {
		boolean flag=false;
		if(zhuce!=null){
			System.out.println("biz:"+zhuce.toString());
		  flag=zhucedao.save(zhuce);
		}
		return flag;
	}

	public boolean update(Zhuce zhuce) {
		boolean flag=false;
		if(zhuce!=null){
		  flag=zhucedao.update(zhuce);
		}
		return flag;
	}

	public boolean delById(Integer Uid) {
		return zhucedao.delById(Uid);
	}


	public Zhuce findById(String Uid) {
		return zhucedao.findById(Uid);
	}

	public List<Zhuce> findAll() {
		return zhucedao.findAll();
	}

	public List<Utype> doinit() {
		return zhucedao.doinit();
	}
	//获取员工信息总条数
	public int getTotalCount() {
		return zhucedao.getTotalCount();
	}
	//分页获取员工信息数据
	public List<Zhuce> getPageZhuceList(int pageNo,int pageSize){
		return zhucedao.getPageZhuceList(pageNo, pageSize);
	}

}
