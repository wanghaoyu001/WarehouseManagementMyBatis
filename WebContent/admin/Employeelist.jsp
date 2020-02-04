<%@ page language="java" import="com.pojo.*,com.biz.*,java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${pg==null}">
 <c:redirect url="ZhuceServerlet?op=7"></c:redirect> 
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工列表</title>
<link rel="stylesheet" href="./../css/buyin.css" type="text/css"/>
<script type="text/javascript">  
//删除二次确认
function confirmDel(){
	return window.confirm("您确定要删除该条记录吗?");
}
//注销二次确认
function Loginout(){
	return window.confirm("您确定要注销吗?");
}
//更改每页记录数
function dochanpageSize(){
	var pageSize=document.formpage.pageSize.value;
	if(isNaN(pageSize)){
		alert("请输入正确的数字");
		document.formpage.pageSize.value=${pg.pageSize}
       return
	}
	window.location="ZhuceServerlet?op=7&pageSize="+pageSize;
}
//跳转页数
function dochancurrentPageNo(){
	var currentPageNo=document.formpage.currentPageNo.value;
	if(isNaN(currentPageNo)){
		alert("请输入正确的数字");
		document.formpage.currentPageNo.value=${pg.currentPageNo}
       return
	}
	window.location="ZhuceServerlet?op=7&currentPageNo="+currentPageNo;
}
</script>

</head>
<body style="margin-bottom:40px">
<br>
<div style="font-size:30px;font-weight:bold;position:absolute;left:30px;">仓库管理系统MyBatis版</div>
<div align="center">
<c:if test="${nowuser.powerPurchase==1}">
<div class="dropdown">
 <button class="dropbtn">采购</button>
 <div class="dropdown-content">
 <a href="buyin.jsp">采购进货</a>
 <a href="#">采购退货</a>
 </div>
</div>
</c:if>
<c:if test="${nowuser.powerSale==1}">
<div class="dropdown">
 <button class="dropbtn">销售</button>
 <div class="dropdown-content">
 <a href="#">销售出货</a>
 <a href="#">销售退货</a>
 </div>
</div>
</c:if>
<c:if test="${nowuser.powerInventoryview==1}">
<div class="dropdown">
 <button class="dropbtn">库存查看</button>
 <div class="dropdown-content">
 <a href="#">商品库存</a>
 <a href="#">各仓库库存</a>
 </div>
</div>
</c:if>
<c:if test="${nowuser.powerProfit==1}">
<div class="dropdown">
 <button class="dropbtn">利润</button>
 <div class="dropdown-content">
 <a href="#">商品利润</a>
 <a href="#">客户利润</a>
 </div>
</div>
</c:if>
<c:if test="${nowuser.powerWarehouseManage==1}">
<div class="dropdown">
 <button class="dropbtn">仓库管理</button>
 <div class="dropdown-content">
 <a href="#">仓库管理</a>
 </div>
</div>
</c:if>
<c:if test="${nowuser.powerAllocationManage==1}">
<div class="dropdown">
 <button class="dropbtn">仓库调拨</button>
 <div class="dropdown-content">
 <a href="#">仓库调拨</a>
 </div>
</div>
</c:if>
<c:if test="${nowuser.powerCustomerManage==1}">
<div class="dropdown">
 <button class="dropbtn">客户管理</button>
 <div class="dropdown-content">
 <a href="#">客户管理</a>
 <a href="#">供货商管理</a>
 </div>
</div>
</c:if>
<c:if test="${nowuser.powerStaffManage==1}">
<div class="dropdown">
 <button class="dropbtn">员工管理</button>
 <div class="dropdown-content">
 <a href="Employeelist.jsp">员工管理</a>
 <a href="zhuce.jsp">员工注册</a>
 </div>
</div>
</c:if>
</div>
<div style="height:30px;color:red;font-size:18px;position:absolute;display:inline-block;right:30px;">
	<div style="float:left;display:inline-block;">当前用户:${nowuser.utypeName}-${nowuser.uname}&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<div style="width:30px;float:left;">
		<form action="LoServlet" method="post" onsubmit="return Loginout()">
 		<input type="submit" value="注销">
		</form>
	</div>
</div>
<br>
<hr>
<br>
<table width="1400"  align="center" border="1">
  <tr><td style="font-size:30px;height:50px;font-weight:bold;" bgcolor="#ffffcc" align="center" colspan="18">员工展示</td></tr>
  <tr>
  	<td align="center" rowspan="2">序号</td>
  	<td align="center" rowspan="2">账号</td>
 	<td align="center" rowspan="2">密码</td>
  	<td align="center" rowspan="2">用户类型</td>
  	<td align="center" rowspan="2">姓名</td>
  	<td align="center" rowspan="2">性别</td>
  	<td align="center" rowspan="2">电话</td>
  	<td align="center" rowspan="2">照片</td>
  	<td align="center" rowspan="2">备注</td>
  	<td align="center" colspan="8">用户权限</td>
  	<td align="center" rowspan="2">操作</td>
  </tr>
  <tr>
  	<td align="center">采购</td>
  	<td align="center">销售</td>
  	<td align="center">库存查看</td>
  	<td align="center">利润查看</td>
  	<td align="center">仓库管理</td>
  	<td align="center">仓库调拨</td>
  	<td align="center">客户管理</td>
  	<td align="center">员工管理</td>
  </tr>
  <!--分页查询所有-->
  <c:set var="row" value="${0}"></c:set>
  <c:forEach items="${pg.pagelist}" var="ltZhuce">
  <c:if test="${row%2==0 }">
  <tr bgcolor="#F1F3F4">
  	<td align="center">${ltZhuce.id}</td>
  	<td>${ltZhuce.uid}</td>
  	<td>${ltZhuce.upassword}</td>
  	<td>${ltZhuce.utypeName}</td>
  	<td>${ltZhuce.uname}</td>
 	<td>${ltZhuce.usex}</td>
  	<td>${ltZhuce.utel}</td>
    <td align="center">
   	<a href="./../image/${ltZhuce.upicname}">
  	<img alt="图片不存在" src="./../image/${ltZhuce.upicname}" width="100" height="100">
  	</a>
   	</td>
  	<td>${ltZhuce.remarks}</td>
  	<td align="center">${ltZhuce.powerPurchase==1?"是":"否"}</td>
  	<td align="center">${ltZhuce.powerSale==1?"是":"否"}</td>
  	<td align="center">${ltZhuce.powerInventoryview==1?"是":"否"}</td>
  	<td align="center">${ltZhuce.powerProfit==1?"是":"否"}</td>
  	<td align="center">${ltZhuce.powerWarehouseManage==1?"是":"否"}</td>
  	<td align="center">${ltZhuce.powerAllocationManage==1?"是":"否"}</td>
  	<td align="center">${ltZhuce.powerCustomerManage==1?"是":"否"}</td>
  	<td align="center">${ltZhuce.powerStaffManage==1?"是":"否"}</td>
  	<td></td>
  </tr>
  </c:if>
  
  <c:if test="${row%2!=0 }">
  <tr>
  	<td align="center">${ltZhuce.id}</td>
 	<td>${ltZhuce.uid}</td>
  	<td>${ltZhuce.upassword}</td>
  	<td>${ltZhuce.utypeName}</td>
  	<td>${ltZhuce.uname}</td>
 	<td>${ltZhuce.usex}</td>
  	<td>${ltZhuce.utel}</td>
    <td align="center">
   	<a href="./../image/${ltZhuce.upicname}">
  	<img alt="图片不存在" src="./../image/${ltZhuce.upicname}" width="100" height="100">
  	</a>
   	</td>
  	<td>${ltZhuce.remarks}</td>
  	<td width="80px" align="center">${ltZhuce.powerPurchase==1?"是":"否"}</td>
  	<td width="80px" align="center">${ltZhuce.powerSale==1?"是":"否"}</td>
  	<td width="80px" align="center">${ltZhuce.powerInventoryview==1?"是":"否"}</td>
  	<td width="80px" align="center">${ltZhuce.powerProfit==1?"是":"否"}</td>
  	<td width="80px" align="center">${ltZhuce.powerWarehouseManage==1?"是":"否"}</td>
  	<td width="80px" align="center">${ltZhuce.powerAllocationManage==1?"是":"否"}</td>
  	<td width="80px" align="center">${ltZhuce.powerCustomerManage==1?"是":"否"}</td>
  	<td width="80px" align="center">${ltZhuce.powerStaffManage==1?"是":"否"}</td>
  	<td></td>
  </tr>
  </c:if>
  <c:set var="row" value="${row+1}"></c:set>
  </c:forEach>
</table>
<!-- 分页 -->
<div style="position:fixed; bottom:0px;width:100%" align="center">
<form action="" id="formpage" name="formpage">
<table width="1000px" height="30px" align="center" border="1" bgcolor="#FFFFCC">
<tr align="center">
  
  <c:if test="${pg.currentPageNo>1}">
  	<td>
  		<a href="ZhuceServerlet?op=7&currentPageNo=1">首页</a>
  	</td>
  </c:if> 
  <c:if test="${pg.currentPageNo==1}">
  	<td>首页</td>
  </c:if>
  
  <c:if test="${pg.currentPageNo>1}">
  	<td>
  		<a href="ZhuceServerlet?op=7&currentPageNo=${pg.currentPageNo-1}">上一页</a>
  	</td>
  </c:if> 
  <c:if test="${pg.currentPageNo==1}">
  	<td>上一页</td>
  </c:if>
  
  <c:if test="${pg.currentPageNo<pg.totalPageCount}">
  	<td>
  		<a href="ZhuceServerlet?op=7&currentPageNo=${pg.currentPageNo+1}">下一页</a>
  	</td>
  </c:if>
  <c:if test="${pg.currentPageNo==pg.totalPageCount}">
  	<td>下一页</td>
  </c:if>
  
  
  <c:if test="${pg.currentPageNo<pg.totalPageCount}">
  	<td>
  		<a href="ZhuceServerlet?op=7&currentPageNo=${pg.totalPageCount}">尾页</a>
  	</td>
  </c:if>
  <c:if test="${pg.currentPageNo==pg.totalPageCount}">
  	<td>尾页</td>
  </c:if>
  
  
  <td>跳转到第
 	<input type="text" name="currentPageNo" size="2" id="currentPageNo" value="${pg.currentPageNo}"> 
  	页
  	<input type="button" name="button" id="button" onclick="dochancurrentPageNo()" value="确定">
  </td>
  
  <td>每页 
 	<input type="text" name="pageSize" size="2"  id="pageSize" value="${pg.pageSize}"> 
 	条记录
  	<input type="button" name="button1" id="button1" onclick="dochanpageSize()" value="确定"> 
  </td>
  
  <td>${pg.currentPageNo}/${pg.totalPageCount}</td>
</tr>
</table>
</form>
</div>
</body>
</html>