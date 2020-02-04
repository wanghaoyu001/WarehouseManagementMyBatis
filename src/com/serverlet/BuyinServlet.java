package com.serverlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.biz.*;
import com.pojo.*;
import com.util.*;


public class BuyinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// ��ʼ����Ʒ����ɹ���
		ProductInfo pdInfo = new ProductInfo();
		BuyIn bi = new BuyIn();

		// ��Ʒ��Ϣ
		String buyinProductId = null;
		if (request.getParameter("buyinProductId") != null) {
			buyinProductId = request.getParameter("buyinProductId");// ��ʼ��Ʒ����
		}

		if (request.getParameter("productId") != null) {
			String productId = request.getParameter("productId");// ��Ʒ����(����)
			String productName = request.getParameter("productName");// ��Ʒ����(����)
			String productType = request.getParameter("productType");// ��Ʒ����(����)
			String productSpecs = request.getParameter("productSpecs");// ���(��ɫ�ͺ�)
			
			pdInfo.setProductId(productId);
			pdInfo.setProductName(productName);
			pdInfo.setProductType(productType);
			pdInfo.setProductSpecs(productSpecs);

			// �ɹ���Ϣ
			String buyinIdS;
			int buyinId;
			if (request.getParameter("buyinId") != null) {
				buyinIdS = request.getParameter("buyinId");// �ɹ����
				buyinId = Integer.parseInt(buyinIdS);
				bi.setBuyinId(buyinId);// �ɹ����
			}
			String buyinNumS = request.getParameter("buyinNum");// ��������
			int buyinNum = Integer.parseInt(buyinNumS);
			String buyinPriceS = request.getParameter("buyinPrice");// ��������
			double buyinPrice = Double.parseDouble(buyinPriceS);
			double buyinTotal = buyinNum * buyinPrice;// �ϼ�
			String buyinPayS = request.getParameter("buyinPay");// �Ѹ����
			double buyinPay = Double.parseDouble(buyinPayS);
			double buyinDebt = buyinPay - buyinTotal;// Ƿ����
			String buyinWarehouse = request.getParameter("buyinWarehouse");// �ֿ�
			String buyinSupplier = request.getParameter("buyinSupplier");// ������
			String buyinTime = request.getParameter("buyinTime");// ����ʱ��
			String buyinUser = request.getParameter("buyinUser");// ������
			String buyinRemarks = request.getParameter("buyinRemarks");// ��ע

			bi.setBuyinProductId(productId);// ��Ʒ����(����)
			bi.setBuyinNum(buyinNum);// ��������
			bi.setBuyinPrice(buyinPrice);// ��������
			bi.setBuyinTotal(buyinTotal);// �ϼ�
			bi.setBuyinPay(buyinPay);// �Ѹ����
			bi.setBuyinDebt(buyinDebt);// Ƿ����
			bi.setBuyinWarehouse(buyinWarehouse);// �ֿ�
			bi.setBuyinSupplier(buyinSupplier);// ������
			bi.setBuyinTime(buyinTime);// ��������
			bi.setBuyinUser(buyinUser);// ������
			bi.setBuyinRemarks(buyinRemarks);// ��ע
		}

		IBuyinBiz ibin = new BuyinBiz();
		String s = request.getParameter("op");
		System.out.println("op="+s);
		int op = Integer.parseInt(s);
		switch (op) {
		case 1:// ����
			
			int flag = ibin.save(bi, pdInfo);
			if (flag>0) {
				String jsonadd=JSONObject.toJSONString(1);
				AJAXUtil.printString(response, jsonadd);
			} else {
				String jsonadd=JSONObject.toJSONString(0);
				AJAXUtil.printString(response, jsonadd);
			}
			break;
		case 2:// �޸�
			System.out.println("line:97-BuyinServlet.java-�޸�-buyinProductId:" + buyinProductId);
			int flag1 = ibin.update(bi, pdInfo, buyinProductId);
			if (flag1>0) {
				String jsonupdate=JSONObject.toJSONString(1);
				AJAXUtil.printString(response, jsonupdate);
			} else {
				String jsonupdate=JSONObject.toJSONString(0);
				AJAXUtil.printString(response, jsonupdate);
			}
			break;
		case 3:// ɾ��
			buyinProductId = request.getParameter("buyinProductId");
			System.out.println("line:109-BuyinServlet.java-ɾ��-buyinProductId:" + buyinProductId);
			int flag2 = ibin.delById(buyinProductId);
			if (flag2>0) {
				String jsondelete=JSONObject.toJSONString(1);
				AJAXUtil.printString(response, jsondelete);
			} else {
				String jsondelete=JSONObject.toJSONString(0);
				AJAXUtil.printString(response, jsondelete);
			}
			break;

		case 4:// ��ѯ����
			String BuyinId = request.getParameter("BuyinId");
			BuyInSearch updateBuyIn = ibin.findById(BuyinId);
			String jsonupdate=JSONObject.toJSONString(updateBuyIn);
			AJAXUtil.printString(response, jsonupdate);	
			break;
		case 7:// ��ҳ��ѯ����
			// ������ҳ�����
			Pageutil pgbuyin = (Pageutil) session.getAttribute("pgbuyin");
			pgbuyin = pgbuyin == null ? new Pageutil() : pgbuyin;
			// ���ü�¼��ҳ��
			int totalCount = ibin.getTotalCount();
			System.out.println("totalCount:"+totalCount);
			pgbuyin.setTotalCount(totalCount);
			
			// ��ȡ��ǰҳ��
			String pageIndex = request.getParameter("currentPageNo");
			// ��ȡÿҳ��ʾ����
			String pageSize = request.getParameter("pageSize");
			
			// ����ת��
			int nowpageIndex = Integer.parseInt(pageIndex);
			int nowpageSize = Integer.parseInt(pageSize);
			
			if (nowpageSize < 1) {
				nowpageSize = 1;
			} else if (nowpageSize > totalCount) {
				nowpageSize = totalCount;
			}
			pgbuyin.setPageSize(nowpageSize);
			pgbuyin.setTotalPageCount();
			System.out.println("TotalPageCount:"+pgbuyin.getTotalPageCount());
			if (nowpageIndex < 1) {
				nowpageIndex = 1;
			} else if (nowpageIndex > pgbuyin.getTotalPageCount()) {
				nowpageIndex = pgbuyin.getTotalPageCount();
			}
			pgbuyin.setCurrentPageNo(nowpageIndex);
			System.out.println("nowpageIndex:"+nowpageIndex);
			System.out.println("nowpageSize:"+nowpageSize);
			// ��ȡ��¼���ϲ���װ
			List<BuyInSearch> listPage = ibin.getPageBuyinList(nowpageIndex, nowpageSize);
			pgbuyin.setPagelist(listPage);
			// �������ҳ��
			pgbuyin.setTotalPageCount();
			String jsonsPgbuyin=JSONObject.toJSONString(pgbuyin);
		    AJAXUtil.printString(response, jsonsPgbuyin);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}
}
