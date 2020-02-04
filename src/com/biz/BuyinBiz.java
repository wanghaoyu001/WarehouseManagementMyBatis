package com.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mapper.BuyinMapper;
import com.pojo.BuyIn;
import com.pojo.BuyInSearch;
import com.pojo.ProductInfo;
import com.util.MyBatisUtil;

public class BuyinBiz implements IBuyinBiz{
    @Override
    //�ɹ�������Ϣ����
	public int save(BuyIn buyin, ProductInfo productInfo) {
		SqlSession sqlession=null;
		int count=0;
		try {
			sqlession=MyBatisUtil.createSqlSession();
			count = sqlession.getMapper(BuyinMapper.class).save(buyin, productInfo);
			sqlession.commit();
		} catch (Exception e) {
			sqlession.rollback();
			count=0;
		} finally {
			MyBatisUtil.closeSqlSession(sqlession);
		}
		return count;
	}
	
	@Override
	//�ɹ�������Ϣ����
	public int update(BuyIn buyin,ProductInfo productInfo,String BuyinProductId) {
		SqlSession sqlession=null;
		int count=0;
		try {
			sqlession=MyBatisUtil.createSqlSession();
			count = sqlession.getMapper(BuyinMapper.class).update(buyin, productInfo ,BuyinProductId);
			sqlession.commit();
		} catch (Exception e) {
			sqlession.rollback();
			count=0;
		} finally {
			MyBatisUtil.closeSqlSession(sqlession);
		}
		return count;
	}
	@Override
	//�ɹ�����ɾ��
	public int delById(String ProductId) {
		SqlSession sqlession=null;
		int count=0;
		try {
			sqlession=MyBatisUtil.createSqlSession();
			count = sqlession.getMapper(BuyinMapper.class).delById(ProductId);
			sqlession.commit();
		} catch (Exception e) {
			sqlession.rollback();
			count=0;
		} finally {
			MyBatisUtil.closeSqlSession(sqlession);
		}
		return count;
	}
	@Override
	//���ݲɹ�ID����
	public BuyInSearch findById(String BuyinId) {
		SqlSession sqlession=MyBatisUtil.createSqlSession();
		Map<String,Object> param=new HashMap<>();
		param.put("pageNo", null);
		param.put("pageSize", null);
		param.put("BuyinId", BuyinId);
		List<BuyInSearch> IDlist=sqlession.getMapper(BuyinMapper.class).find(param);
		MyBatisUtil.closeSqlSession(sqlession);
		return IDlist.get(0);
	}
	@Override
	//��ȡ�ɹ�������Ϣ������
	public int getTotalCount() {
		int count=0;
		SqlSession sqlession=MyBatisUtil.createSqlSession();
		count=sqlession.getMapper(BuyinMapper.class).getTotalCount();
		MyBatisUtil.closeSqlSession(sqlession);
		return count;
	}
	@Override
	//��ȡ��ҳ�б�
	public List<BuyInSearch> getPageBuyinList(Integer pageNo, Integer pageSize) {
		SqlSession sqlession=MyBatisUtil.createSqlSession();
		Map<String,Object> param=new HashMap<>();
		param.put("pageNo", (pageNo-1)*pageSize);
		param.put("pageSize", pageSize);
		param.put("BuyinId", null);
		System.out.println("pageNo:"+param.get("pageNo"));
		System.out.println("pageSize:"+param.get("pageSize"));
		List<BuyInSearch> list=sqlession.getMapper(BuyinMapper.class).find(param);
		MyBatisUtil.closeSqlSession(sqlession);
		return list;
	}

}
