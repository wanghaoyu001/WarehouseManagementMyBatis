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
		//获取SqlSessionFactory(使用源文件流获取数据库会话工厂对象)
		sessionFactory=new SqlSessionFactoryBuilder().build(in);
		//获取数据库会话
		} catch (IOException e) {
		// TODO 自动生成的 catch 块
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
