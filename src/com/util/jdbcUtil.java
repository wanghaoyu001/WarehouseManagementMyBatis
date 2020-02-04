package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtil {
	private static String url;
	private static String user;
	private static String password;
	private static String driverclass;
	static{
		//���ļ��ж�ȡ������
		InputStream fis=jdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		//����properties����
		Properties info=new Properties();
		//�����м�������
		try {
			info.load(fis);
			driverclass=info.getProperty("driverclass");
			url=info.getProperty("url");
			user=info.getProperty("user");
			password=info.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConn() {
		Connection conn=null;
		try {
			//���ݿ���������
			Class.forName(driverclass);
			//2.ʹ���������������ȡ���ݿ����Ӷ���
			conn=DriverManager.getConnection(
					url,
					user,
					password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
