package com.mapper;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import com.pojo.*;


public interface BuyinMapper {
	public int save(@Param("buyin")BuyIn buyin, @Param("productInfo")ProductInfo productInfo);

	public int update(@Param("buyin")BuyIn buyin, @Param("productInfo")ProductInfo productInfo, @Param("BuyinProductId")String BuyinProductId);

	public int delById(@Param("ProductId")String ProductId);

	//查找综合
	public List<BuyInSearch> find(Map<String,Object> param);

	// 获取采购进货信息总条数
	public int getTotalCount();
	
	 //解决模糊查询传空问题
	public List<BuyInSearch> findByproductName(String productName);
}
