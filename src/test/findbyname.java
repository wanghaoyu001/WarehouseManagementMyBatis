package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mapper.BuyinMapper;
import com.pojo.BuyInSearch;
import com.util.MyBatisUtil;

public class findbyname {
public static void main(String[] args) {
	//����mybatis���ݿ����Ӷ���
	  SqlSession session=null;
	 //����mybatis�������ļ�
	  try {
		//��ȡ���ݿ�Ự
		session=MyBatisUtil.createSqlSession();
		System.out.println("session--->"+session);
		//��ȡѧ����ӳ��ӿڶ���
		BuyinMapper buyinMapper=session.getMapper(BuyinMapper.class);
		List<BuyInSearch> lsst=buyinMapper.findByproductName("��");
		for(BuyInSearch oldst:lsst){
		System.out.println("��ѯ���:"+oldst.toString());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		MyBatisUtil.closeSqlSession(session);
	}
}
}
