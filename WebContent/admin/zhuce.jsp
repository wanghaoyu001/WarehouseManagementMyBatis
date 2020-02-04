<%@ page language="java" import="com.pojo.*,com.biz.*,java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${listUtype==null}">
 <c:redirect url="ZhuceServerlet?op=6"></c:redirect> 
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工注册</title>
<link rel="stylesheet" href="./../css/buyin.css" type="text/css"/>
<!-- jquery引入 -->
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<style type="text/css">
            #preview {
                width: 200px;
                height: 250px;
               	background-image:url(./../image/background.png);
                background-repeat: no-repeat;
                background-color:white;
                background-size:100%;
                background-position-x:center;
                background-position-y:center;
            }
        </style>
<script type="text/javascript"> 
//注销二次确认
function Loginout(){
	return window.confirm("您确定要注销吗?");
}
//复选框全选
function checkAlls(){
	 //1.获取编号前面的复选框
    var checkAllEle = document.getElementById("checkAll");
    //2.对编号前面复选框的状态进行判断
    if(checkAllEle.checked==true){
        //3.获取下面所有的复选框
        var checkOnes = document.getElementsByName("power");
        //4.对获取的所有复选框进行遍历
        for(var i=0;i<checkOnes.length;i++){
            //5.拿到每一个复选框，并将其状态置为选中
            checkOnes[i].checked=true;
        }
    }else{
        //6.获取下面所有的复选框
        var checkOnes = document.getElementsByName("power");
        //7.对获取的所有复选框进行遍历
        for(var i=0;i<checkOnes.length;i++){
            //8.拿到每一个复选框，并将其状态置为未选中
            checkOnes[i].checked=false;
        }
    }
}
//用户名验证
function uidcheck(){
	var uid = $("#uid").val();
	if(uid==null||uid==""){
		$("#showid").html("<div style='color:red'>用户名不能为空!</div>");
	}else{
		$.getJSON("ZhuceServerlet?op=5&uid="+uid,function(uidcheck){
			if(uidcheck==true){
				$("#showid").html("<div style='color:red'>用户名已存在!</div>");
			 }else{
				 $("#showid").html("<div style='color:green'>用户名可以使用!</div>");
			 }
		});
	}
}
//密码验证
function psdcheck(){
	var upassword = $("#upassword").val();
	if(upassword==null||upassword==""){
		$("#showpd").html("<div style='color:red'>密码不能为空!</div>");
	}
}
//密码二次验证
function psdcheck2(){
	var upassword2 = $("#upassword2").val();
	if(upassword2==null||upassword2==""){
		$("#showpd").html("<div style='color:red'>密码不能为空!</div>");
	}else{
		var upassword = $("#upassword").val();
		if(upassword!=upassword2){
			$("#showpd").html("<div style='color:red'>两次输入的密码不同!</div>");
		}else{
			$("#showpd").html("<div style='color:green'>密码可以使用!</div>");
		}
	}
}
//清除验证信息
function clean(){
	$("#showpd").html("");
}
</script>
<%
String s = request.getParameter("op");
%>
</head>
<body>
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
<form action="ZhuceServerlet" enctype="multipart/form-data" method="post">
<table height ="500px" width="500px" border="1" align="center">
    <tr><td style="font-size:30px;height:50px;font-weight:bold;" colspan="6" align="center" bgcolor="#FFFFCC">员工注册</td></tr>
	<tr>
		<td width="100px" align="center">账号</td>
		<td><input type="text" id="uid" name="uid" style="width:98%" onblur="uidcheck()"></td>
		<td align="center" id="showid"></td>
	</tr>
	<tr>	
		<td align="center">密码</td>
		<td><input type="text" id="upassword" name="upassword" style="width:98%" onfocus="clean()" onblur="psdcheck()"></td>
		<td align="center" id="showpd"></td>
	</tr>
	<tr>	
		<td align="center">再次输入密码</td>
		<td><input type="text" id="upassword2" name="upassword2" style="width:98%" onfocus="clean()" onblur="psdcheck2()"></td>
		<td rowspan="5">
			<div id="preview"></div>
		</td>
	</tr>
	<tr>
		<td align="center">用户类型</td>
		<td>
			<select name="utype" style="width:100%">
				<c:forEach items="${listUtype}" var="lsut">
  				<option value="${lsut.utypes}">${lsut.utypeName}</option>
  				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td align="center">姓名</td>
		<td><input type="text" id="uname" name="uname" style="width:98%"></td>
	</tr>
	<tr>
		<td align="center">性别</td>
		<td>
			<input type="radio" id="usex" name="usex" value="男" checked>男
			<input type="radio" id="usex" name="usex" value="女">女
		</td>
	</tr>
	<tr>
		<td align="center">电话号码</td>
		<td><input type="text" id="utel" name="utel" style="width:98%"></td>
	</tr>
	<tr>
  		<td align="center">照片</td>
  		<td colspan="2"><input type="file" accept="image/*" id="files" name="picture" ></td>
  	</tr>
  	
	<tr>
		<td align="center">权限</td>
		<td colspan="2">
			<input type="checkbox" id="power" name="power" value="powerPurchase">采购
			<input type="checkbox" id="power" name="power" value="powerSale">销售
			<input type="checkbox" id="power" name="power" value="powerInventoryview">库存查看
			<input type="checkbox" id="power" name="power" value="powerProfit">利润<br>
			<input type="checkbox" id="power" name="power" value="powerWarehouseManage">仓库管理
			<input type="checkbox" id="power" name="power" value="powerAllocationManage">仓库调拨
			<input type="checkbox" id="power" name="power" value="powerCustomerManage">客户管理
			<input type="checkbox" id="power" name="power" value="powerStaffManage">员工管理<br>
			<input type="checkbox" id="checkAll" onclick="checkAlls()">全选
		</td>
	</tr>
	<tr>
		<td align="center">备注</td>
		<td colspan="5"><textarea name="remarks" id="remarks" cols="110" rows="6" style="width:99%"></textarea></td>
		
	</tr>
	<tr>
		<td style="height:50px;" colspan="3" align="center">
		<input type="hidden" id="op" name="op" value="1" >
		<input style="width:80px;height:40px;" type="submit" value="注册">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input style="width:80px;height:40px;" type="reset" value="重置">
		</td>
	</tr>
</table>
</form>
<div>
<%if(s!=null&&s.equals("0")){%>
<div style="font-size:30px;color:red;" align="center">注册失败！</div>
<%}%>
<%if(s!=null&&s.equals("1")){%>
<div style="font-size:30px;color:red;" align="center">注册成功！</div>
<%}%>
</div>
<script type="text/javascript">
            var preview = document.querySelector('#preview');
            var eleFile = document.querySelector('#files');
            eleFile.addEventListener('change', function() {
                var file = this.files[0];                
                // 确认选择的文件是图片                
                if(file.type.indexOf("image") == 0) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file);                    
                    reader.onload = function(e) {
                        // 图片base64化
                        var newUrl = this.result;
                        preview.style.backgroundImage = 'url(' + newUrl + ')';
                    };
                }
            });
</script>
</body>
</html>