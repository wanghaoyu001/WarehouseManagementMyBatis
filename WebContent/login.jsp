<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<br>
<div style="font-size:35px;font-weight:bold;" align="center">仓库管理系统MyBatis版</div>
<br>
<hr>
<br>
<form action="LiServlet" method="post">
<table border="1" width="300px" height="300px" align="center">
	<tr>
	<td colspan="2" align="center" bgcolor="#FFFFCC">用户登录</td>
	</tr>
	<tr>
	<td align="center">用户名:</td>
	<td><input type="text" style="width:98%;height:80%" id="Uid" name="Uid"/></td>
	</tr>
	<tr>
	<td align="center">密码:</td>
	<td><input type="password" style="width:98%;height:80%" id="Upassword" name="Upassword"/></td>
	</tr>
	<tr>
	<td align="center">用户类型:</td>
	<td align="center">
		<input type="radio" id="Utype" name="Utype" value="0"/>管理员
		<input type="radio" id="Utype" name="Utype" value="1" checked/>普通用户
	</td>
	</tr>
	<tr>
	<td colspan="2" align="center">
		<input type="submit" style="width:60px;height:30px;" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" style="width:60px;height:30px;" value="重置"/>
	</td>
	</tr>
</table>
</form>
<br>
<c:if test="${loginop==false}">
	<div style="font-size:30px;color:red;" align="center">登录失败！请重新登录！</div>
</c:if>
<br>
<hr>
<br>
</body>
</html>