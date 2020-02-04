package com.serverlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.*;
import com.pojo.*;

public class LoginServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ILoginBiz logb = new LoginBiz();
		HttpSession session = request.getSession();
		
		int Utype = Integer.parseInt(request.getParameter("Utype"));
		String Uid = request.getParameter("Uid");
		String Upassword = request.getParameter("Upassword");
		List<Login> lslog = logb.findAll();
		boolean flag=false;
		switch (Utype) {
		case 0:// 管理员登录
			for (Login login : lslog) {
				if (login.getUtype() == 0 && login.getUid().equals(Uid) && login.getUpassword().equals(Upassword)) {
					Login user = new Login(Uid, Upassword, Utype);
					session.setAttribute("user", user);
					flag=true;
					IZhuceBiz zhucebiz = new ZhuceBiz();
					Zhuce nowuser = zhucebiz.findById(Uid);
					session.setAttribute("nowuser", nowuser);
					break;
				} 
			}
			if (flag) {
				// 登录成功并跳转
				session.setAttribute("loginop", true);
				response.sendRedirect(request.getContextPath() + "/admin/buyin.jsp");
			}else {
				// 登录失败重新登录
				session.setAttribute("loginop", false);
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			
			}

			break;
		case 1:// 普通用户登录
			for (Login login : lslog) {
				if (login.getUtype() == 1 && login.getUid().equals(Uid) && login.getUpassword().equals(Upassword)) {
					Login user = new Login(Uid, Upassword, Utype);
					session.setAttribute("user", user);
					flag=true;
					IZhuceBiz zhucebiz = new ZhuceBiz();
					Zhuce nowuser = zhucebiz.findById(Uid);
					session.setAttribute("nowuser", nowuser);
					break;
				} 
			}
			if (flag) {
				// 登录成功并跳转
				session.setAttribute("loginop", true);
				response.sendRedirect(request.getContextPath() + "/admin/buyin.jsp");
				
			}else {
				// 登录失败重新登录
				session.setAttribute("loginop", false);
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				
			
			}
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}
}