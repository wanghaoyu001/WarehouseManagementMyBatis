package com.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class AJAXUtil {
 public static void printString(HttpServletResponse response,String s){
	 try {
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		System.out.println("AJAXUtil.java:json����:"+s);
		out.print(s);//���
		out.flush();
		out.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}
