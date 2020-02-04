package com.serverlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;
import com.biz.IZhuceBiz;
import com.biz.ZhuceBiz;
import com.pojo.Login;
import com.pojo.Utype;
import com.pojo.Zhuce;
import com.util.AJAXUtil;
import com.util.Pageutil;

public class ZhuceServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		IZhuceBiz zhucebiz = new ZhuceBiz();
		/**************** 文件上传 ********************/
		Zhuce zhuce = new Zhuce();
		int op = 0;
		String oop = request.getParameter("op");
		System.out.println("oop:" + oop);
		if (oop == null) {
			try {
				// 1.获取磁盘工厂对象
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 2.获取上传的servlet对象
				ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
				// 3.解析request请求对象
				List<FileItem> lsitem = servletFileUpload.parseRequest(request);

				// 4.处理上传的数据
				if (lsitem != null) {
					for (FileItem item : lsitem) {
						// 判断上传的文件和表单控件
						if (item.isFormField()) {// 是表单元素
							// 获取表单元素的名称
							String filedName = item.getFieldName();
							// 根据元素名称，获取表单元素具体的值
							if (filedName.equals("uid")) {
								// 获取具体数据
								String uid = item.getString("utf-8");
								zhuce.setUid(uid);
							}
							if (filedName.equals("upassword")) {
								String upassword = item.getString("utf-8");
								zhuce.setUpassword(upassword);
							}
							if (filedName.equals("utype")) {
								String utype = item.getString("utf-8");
								zhuce.setUtype(Integer.parseInt(utype));
							}
							if (filedName.equals("uname")) {
								String uname = item.getString("utf-8");
								zhuce.setUname(uname);
							}
							if (filedName.equals("usex")) {
								String usex = item.getString("utf-8");
								zhuce.setUsex(usex);
							}
							if (filedName.equals("utel")) {
								String utel = item.getString("utf-8");
								zhuce.setUtel(utel);
							}
							if (filedName.equals("remarks")) {
								String remarks = item.getString("utf-8");
								zhuce.setRemarks(remarks);
							}
							if (filedName.equals("power")) {
								String power = item.getString("utf-8");
								if (power.equals("powerPurchase")){
									zhuce.setPowerPurchase(1);
								}
								if (power.equals("powerSale")){
									zhuce.setPowerSale(1);
								}
								if (power.equals("powerInventoryview")){
									zhuce.setPowerInventoryview(1);
								}
								if (power.equals("powerProfit")){
									zhuce.setPowerProfit(1);
								}
								if (power.equals("powerWarehouseManage")){
									zhuce.setPowerWarehouseManage(1);
								}
								if (power.equals("powerAllocationManage")){
									zhuce.setPowerAllocationManage(1);
								}
								if (power.equals("powerCustomerManage")){
									zhuce.setPowerCustomerManage(1);
								}
								if (power.equals("powerStaffManage")){
									zhuce.setPowerStaffManage(1);
								}
							}

							// 获取客户端传递的请求码
							if (filedName.equals("op")) {
								// 获取具体数据
								String opp = item.getString("utf-8");
								op = Integer.parseInt(opp);
							}
							
						} else {// 文件域
								// 获取文件上传名称
							String upicname = item.getName();
							// 获取上传图片后缀
							if (upicname.lastIndexOf(".") != -1) {
								// 截取
								String ext = upicname.substring(upicname.lastIndexOf("."));
								if (ext != null && (ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".png"))) {
									// 改文件名字
									upicname = new Date().getTime() + ext;
									System.out.println("修改后文件名称:" + upicname);
									zhuce.setUpicname(upicname);
								}
							}
							// 获取网站根路径
							String realpath = getServletContext().getRealPath("/");
							System.out.println("realpath:" + realpath);
							// 获取上传文件的字节数组
							byte[] b = item.get();
							// 创建输出流对象
							FileOutputStream fout = new FileOutputStream(new File(realpath + "image\\" + upicname));
							System.out.println("realpath:" +realpath + "image\\" + upicname);
							fout.write(b);
							fout.flush();
							fout.close();

						}
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			op = Integer.parseInt(oop);
		}
		/**************** 文件上传end ********************/
		switch (op) {
		case 1:// 增加
			System.out.println("添加对象：" + zhuce.toString());
			boolean flag = zhucebiz.save(zhuce);
			if (flag) {
				System.out.println("注册成功！");
				response.sendRedirect(request.getContextPath() + "/admin/zhuce.jsp?op=1");
			} else {
				System.out.println("注册失败！");
				response.sendRedirect(request.getContextPath() + "/admin/zhuce.jsp?op=0");
			}

			break;
		case 2:// 修改
			System.out.println("oldst:" + zhuce.toString());
			flag = zhucebiz.update(zhuce);

			if (flag) {
				response.sendRedirect("stuServlet?op=5");
			} else {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			}
			break;
		case 3:// 删除
			String sid = request.getParameter("sid");
			flag = zhucebiz.delById(Integer.parseInt(sid));
			if (flag) {
				response.sendRedirect("stuServlet?op=5");
			} else {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			}
			break;
		case 4://查询单个
			Login user=(Login)session.getAttribute("user");
			String uid = user.getUid();
			Zhuce nowuser = zhucebiz.findById(uid);
			session.setAttribute("nowuser", nowuser);
			response.sendRedirect(request.getContextPath() + "/stuupdate.jsp");
			break;
		case 5:// 验证用户名
			boolean  flag5 = false;
			String uidcheck = request.getParameter("uid");
			List<Zhuce> listZhuce = zhucebiz.findAll();
				for (Zhuce zhuceid : listZhuce) {
					if (zhuceid.getUid().equals(uidcheck)){
						flag5=true;
						break;
					} 
				}
				String jsonuidcheck=JSONObject.toJSONString(flag5);
				AJAXUtil.printString(response, jsonuidcheck);
				
			break;
		case 6:// 班级
			List<Utype> listUtype = zhucebiz.doinit();
			session.setAttribute("listUtype", listUtype);
			response.sendRedirect(request.getContextPath() + "/admin/zhuce.jsp");
			break;
		case 7:// 分页查询所有
			//从session中获取分页实体类
			Pageutil pg = (Pageutil)session.getAttribute("pg");
			pg=pg==null?new Pageutil():pg;
			//记录总数
			int totalCount = zhucebiz.getTotalCount();
			pg.setTotalCount(totalCount);
			//当前页码
			String pageIndex =request.getParameter("currentPageNo");
		    //每页显示数量
			String pageSize=request.getParameter("pageSize");
			pageIndex=pageIndex==null?""+pg.getCurrentPageNo():pageIndex;
			pageSize=pageSize==null?""+pg.getPageSize():pageSize;
			//类型转换
			int nowpageIndex=Integer.parseInt(pageIndex);
			int nowpageSize=Integer.parseInt(pageSize);
			if(nowpageIndex<1){
				nowpageIndex=1;
				}else if (nowpageIndex>pg.getTotalPageCount()) {
					nowpageIndex=pg.getTotalPageCount();
				}
			if(nowpageSize<1){
				nowpageSize=1;
				}else if (nowpageSize>totalCount) {
					nowpageSize=totalCount;
				}
			
			
			pg.setCurrentPageNo(nowpageIndex);
			pg.setPageSize(nowpageSize);
		    //获取记录集合并封装
		    List<Zhuce> listPage = zhucebiz.getPageZhuceList(nowpageIndex, nowpageSize);
		    pg.setPagelist(listPage);
		    //计算出总页数
		    pg.setTotalPageCount();
			session.setAttribute("pg", pg);
			response.sendRedirect(request.getContextPath() + "/admin/Employeelist.jsp");
			break;
		default:
			break;
		}
		out.flush();
		out.close();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
