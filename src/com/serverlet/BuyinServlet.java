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
		// 初始化商品类与采购类
		ProductInfo pdInfo = new ProductInfo();
		BuyIn bi = new BuyIn();

		// 商品信息
		String buyinProductId = null;
		if (request.getParameter("buyinProductId") != null) {
			buyinProductId = request.getParameter("buyinProductId");// 初始商品编码
		}

		if (request.getParameter("productId") != null) {
			String productId = request.getParameter("productId");// 商品编码(必填)
			String productName = request.getParameter("productName");// 商品名称(必填)
			String productType = request.getParameter("productType");// 商品大类(必填)
			String productSpecs = request.getParameter("productSpecs");// 规格(颜色型号)
			
			pdInfo.setProductId(productId);
			pdInfo.setProductName(productName);
			pdInfo.setProductType(productType);
			pdInfo.setProductSpecs(productSpecs);

			// 采购信息
			String buyinIdS;
			int buyinId;
			if (request.getParameter("buyinId") != null) {
				buyinIdS = request.getParameter("buyinId");// 采购编号
				buyinId = Integer.parseInt(buyinIdS);
				bi.setBuyinId(buyinId);// 采购编号
			}
			String buyinNumS = request.getParameter("buyinNum");// 进货数量
			int buyinNum = Integer.parseInt(buyinNumS);
			String buyinPriceS = request.getParameter("buyinPrice");// 进货单价
			double buyinPrice = Double.parseDouble(buyinPriceS);
			double buyinTotal = buyinNum * buyinPrice;// 合计
			String buyinPayS = request.getParameter("buyinPay");// 已付金额
			double buyinPay = Double.parseDouble(buyinPayS);
			double buyinDebt = buyinPay - buyinTotal;// 欠款金额
			String buyinWarehouse = request.getParameter("buyinWarehouse");// 仓库
			String buyinSupplier = request.getParameter("buyinSupplier");// 供货商
			String buyinTime = request.getParameter("buyinTime");// 进货时间
			String buyinUser = request.getParameter("buyinUser");// 进货人
			String buyinRemarks = request.getParameter("buyinRemarks");// 备注

			bi.setBuyinProductId(productId);// 商品编码(必填)
			bi.setBuyinNum(buyinNum);// 进货数量
			bi.setBuyinPrice(buyinPrice);// 进货单价
			bi.setBuyinTotal(buyinTotal);// 合计
			bi.setBuyinPay(buyinPay);// 已付金额
			bi.setBuyinDebt(buyinDebt);// 欠款金额
			bi.setBuyinWarehouse(buyinWarehouse);// 仓库
			bi.setBuyinSupplier(buyinSupplier);// 供货商
			bi.setBuyinTime(buyinTime);// 进货日期
			bi.setBuyinUser(buyinUser);// 进货人
			bi.setBuyinRemarks(buyinRemarks);// 备注
		}

		IBuyinBiz ibin = new BuyinBiz();
		String s = request.getParameter("op");
		System.out.println("op="+s);
		int op = Integer.parseInt(s);
		switch (op) {
		case 1:// 增加
			
			int flag = ibin.save(bi, pdInfo);
			if (flag>0) {
				String jsonadd=JSONObject.toJSONString(1);
				AJAXUtil.printString(response, jsonadd);
			} else {
				String jsonadd=JSONObject.toJSONString(0);
				AJAXUtil.printString(response, jsonadd);
			}
			break;
		case 2:// 修改
			System.out.println("line:97-BuyinServlet.java-修改-buyinProductId:" + buyinProductId);
			int flag1 = ibin.update(bi, pdInfo, buyinProductId);
			if (flag1>0) {
				String jsonupdate=JSONObject.toJSONString(1);
				AJAXUtil.printString(response, jsonupdate);
			} else {
				String jsonupdate=JSONObject.toJSONString(0);
				AJAXUtil.printString(response, jsonupdate);
			}
			break;
		case 3:// 删除
			buyinProductId = request.getParameter("buyinProductId");
			System.out.println("line:109-BuyinServlet.java-删除-buyinProductId:" + buyinProductId);
			int flag2 = ibin.delById(buyinProductId);
			if (flag2>0) {
				String jsondelete=JSONObject.toJSONString(1);
				AJAXUtil.printString(response, jsondelete);
			} else {
				String jsondelete=JSONObject.toJSONString(0);
				AJAXUtil.printString(response, jsondelete);
			}
			break;

		case 4:// 查询单个
			String BuyinId = request.getParameter("BuyinId");
			BuyInSearch updateBuyIn = ibin.findById(BuyinId);
			String jsonupdate=JSONObject.toJSONString(updateBuyIn);
			AJAXUtil.printString(response, jsonupdate);	
			break;
		case 7:// 分页查询所有
			// 创建分页类对象
			Pageutil pgbuyin = (Pageutil) session.getAttribute("pgbuyin");
			pgbuyin = pgbuyin == null ? new Pageutil() : pgbuyin;
			// 设置记录总页数
			int totalCount = ibin.getTotalCount();
			System.out.println("totalCount:"+totalCount);
			pgbuyin.setTotalCount(totalCount);
			
			// 获取当前页码
			String pageIndex = request.getParameter("currentPageNo");
			// 获取每页显示数量
			String pageSize = request.getParameter("pageSize");
			
			// 类型转换
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
			// 获取记录集合并封装
			List<BuyInSearch> listPage = ibin.getPageBuyinList(nowpageIndex, nowpageSize);
			pgbuyin.setPagelist(listPage);
			// 计算出总页数
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
