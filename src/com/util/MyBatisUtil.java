package com.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sessionFactory;
	static {
		try {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//��ȡSqlSessionFactory(ʹ��Դ�ļ�����ȡ���ݿ�Ự��������)
		sessionFactory=new SqlSessionFactoryBuilder().build(in);
		//��ȡ���ݿ�Ự
		} catch (IOException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
		}
	}
	public static SqlSession createSqlSession() {
		return sessionFactory.openSession(false);
	}
	public static void closeSqlSession(SqlSession sqlession) {
		if (sqlession!=null) {
			sqlession.close();
		}
	}
}
