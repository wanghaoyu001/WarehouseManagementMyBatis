package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mapper.BuyinMapper;
import com.pojo.BuyInSearch;
import com.util.MyBatisUtil;

public class findbyname {
public static void main(String[] args) {
	//声明mybatis数据库连接对象
	  SqlSession session=null;
	 //加载mybatis的配置文件
	  try {
		//获取数据库会话
		session=MyBatisUtil.createSqlSession();
		System.out.println("session--->"+session);
		//获取学生的映射接口对象
		BuyinMapper buyinMapper=session.getMapper(BuyinMapper.class);
		List<BuyInSearch> lsst=buyinMapper.findByproductName("力");
		for(BuyInSearch oldst:lsst){
		System.out.println("查询结果:"+oldst.toString());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		MyBatisUtil.closeSqlSession(session);
	}
}
}
