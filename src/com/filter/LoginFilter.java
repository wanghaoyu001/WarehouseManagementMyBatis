package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.Login;

public class LoginFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("用户登录检查过滤器");
		HttpServletRequest requests=(HttpServletRequest)request;
		HttpServletResponse responses=(HttpServletResponse)response;
		Login user =(Login)requests.getSession().getAttribute("user");
		if (user==null) {
			responses.sendRedirect(requests.getContextPath() + "/login.jsp");
		}
		else {
			chain.doFilter(requests, responses);
		}
	}

}
