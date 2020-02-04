package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.jdbcUtil;

public class DbHelperDao {
	/**
	 * ͨ��dao��
	 * */
   private static Connection conn;
   private static PreparedStatement ps;
   private static ResultSet rst;
   /**
    ͨ�õ���ɾ�ķ���
    */
  public static int executeUpdate(String sql,Object[] obj){
	  try {
		  //conn=DbConnection.getConn();
		  conn=jdbcUtil.getConn();
		  ps=conn.prepareStatement(sql);
		  //�ж��������Ƿ��в���
		  if(obj!=null&&obj.length>0){
			  for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
			}
		  }
		  System.out.println("ps:"+ps.toString());
		  int rows=ps.executeUpdate();
		  if(rows>0){
			  return rows;
		  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		//DbConnection.closeAll(null, ps, null,conn);
	}
	  
	return 0;
	  
  }
  /**
  ͨ�õĲ�ѯ����
  */
public static ResultSet executeQuery(String sql,Object[] obj){
	  try {
		  conn=jdbcUtil.getConn();
		  ps=conn.prepareStatement(sql);
		  //�ж��������Ƿ��в���
		  if(obj!=null&&obj.length>0){
			  for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
				System.out.println(obj[i].toString());
			}
		  }
		  System.out.println(ps.toString());
		   rst=ps.executeQuery();
		  if(rst!=null){
			  return rst;
		  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		//DbConnection.closeAll(null, ps, null,conn);
	}
	  
	return null;
	  
}
   
}
