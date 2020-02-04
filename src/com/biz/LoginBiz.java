package com.biz;

import java.util.*;

import org.apache.ibatis.session.SqlSession;

import com.mapper.*;
import com.pojo.*;
import com.util.MyBatisUtil;

public class LoginBiz implements ILoginBiz {
	@Override
	public List<Login> findAll() {
		List<Login> login = new ArrayList<Login>();
		SqlSession sqlession = MyBatisUtil.createSqlSession();
		login = sqlession.getMapper(LoginMapper.class).findAll();
		MyBatisUtil.closeSqlSession(sqlession);
		return login;
	}

}
