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
		/**************** �ļ��ϴ� ********************/
		Zhuce zhuce = new Zhuce();
		int op = 0;
		String oop = request.getParameter("op");
		System.out.println("oop:" + oop);
		if (oop == null) {
			try {
				// 1.��ȡ���̹�������
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 2.��ȡ�ϴ���servlet����
				ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
				// 3.����request�������
				List<FileItem> lsitem = servletFileUpload.parseRequest(request);

				// 4.�����ϴ�������
				if (lsitem != null) {
					for (FileItem item : lsitem) {
						// �ж��ϴ����ļ��ͱ��ؼ�
						if (item.isFormField()) {// �Ǳ�Ԫ��
							// ��ȡ��Ԫ�ص�����
							String filedName = item.getFieldName();
							// ����Ԫ�����ƣ���ȡ��Ԫ�ؾ����ֵ
							if (filedName.equals("uid")) {
								// ��ȡ��������
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

							// ��ȡ�ͻ��˴��ݵ�������
							if (filedName.equals("op")) {
								// ��ȡ��������
								String opp = item.getString("utf-8");
								op = Integer.parseInt(opp);
							}
							
						} else {// �ļ���
								// ��ȡ�ļ��ϴ�����
							String upicname = item.getName();
							// ��ȡ�ϴ�ͼƬ��׺
							if (upicname.lastIndexOf(".") != -1) {
								// ��ȡ
								String ext = upicname.substring(upicname.lastIndexOf("."));
								if (ext != null && (ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".png"))) {
									// ���ļ�����
									upicname = new Date().getTime() + ext;
									System.out.println("�޸ĺ��ļ�����:" + upicname);
									zhuce.setUpicname(upicname);
								}
							}
							// ��ȡ��վ��·��
							String realpath = getServletContext().getRealPath("/");
							System.out.println("realpath:" + realpath);
							// ��ȡ�ϴ��ļ����ֽ�����
							byte[] b = item.get();
							// �������������
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
		/**************** �ļ��ϴ�end ********************/
		switch (op) {
		case 1:// ����
			System.out.println("��Ӷ���" + zhuce.toString());
			boolean flag = zhucebiz.save(zhuce);
			if (flag) {
				System.out.println("ע��ɹ���");
				response.sendRedirect(request.getContextPath() + "/admin/zhuce.jsp?op=1");
			} else {
				System.out.println("ע��ʧ�ܣ�");
				response.sendRedirect(request.getContextPath() + "/admin/zhuce.jsp?op=0");
			}

			break;
		case 2:// �޸�
			System.out.println("oldst:" + zhuce.toString());
			flag = zhucebiz.update(zhuce);

			if (flag) {
				response.sendRedirect("stuServlet?op=5");
			} else {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			}
			break;
		case 3:// ɾ��
			String sid = request.getParameter("sid");
			flag = zhucebiz.delById(Integer.parseInt(sid));
			if (flag) {
				response.sendRedirect("stuServlet?op=5");
			} else {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			}
			break;
		case 4://��ѯ����
			Login user=(Login)session.getAttribute("user");
			String uid = user.getUid();
			Zhuce nowuser = zhucebiz.findById(uid);
			session.setAttribute("nowuser", nowuser);
			response.sendRedirect(request.getContextPath() + "/stuupdate.jsp");
			break;
		case 5:// ��֤�û���
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
		case 6:// �༶
			List<Utype> listUtype = zhucebiz.doinit();
			session.setAttribute("listUtype", listUtype);
			response.sendRedirect(request.getContextPath() + "/admin/zhuce.jsp");
			break;
		case 7:// ��ҳ��ѯ����
			//��session�л�ȡ��ҳʵ����
			Pageutil pg = (Pageutil)session.getAttribute("pg");
			pg=pg==null?new Pageutil():pg;
			//��¼����
			int totalCount = zhucebiz.getTotalCount();
			pg.setTotalCount(totalCount);
			//��ǰҳ��
			String pageIndex =request.getParameter("currentPageNo");
		    //ÿҳ��ʾ����
			String pageSize=request.getParameter("pageSize");
			pageIndex=pageIndex==null?""+pg.getCurrentPageNo():pageIndex;
			pageSize=pageSize==null?""+pg.getPageSize():pageSize;
			//����ת��
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
		    //��ȡ��¼���ϲ���װ
		    List<Zhuce> listPage = zhucebiz.getPageZhuceList(nowpageIndex, nowpageSize);
		    pg.setPagelist(listPage);
		    //�������ҳ��
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
