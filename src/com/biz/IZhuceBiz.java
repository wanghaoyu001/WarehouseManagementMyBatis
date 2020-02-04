package com.biz;

import java.util.List;

import com.pojo.Utype;
import com.pojo.Zhuce;

public interface IZhuceBiz {
	public boolean save(Zhuce zhuce);

	public boolean update(Zhuce zhuce);

	public boolean delById(Integer Uid);

	public Zhuce findById(String Uid);

	public List<Zhuce> findAll();

	public List<Utype> doinit();

	// ��ȡԱ����Ϣ������
	public int getTotalCount();

	// ��ҳ��ȡԱ����Ϣ����
	public List<Zhuce> getPageZhuceList(int pageNo, int pageSize);
}
