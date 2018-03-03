package com.greatwqs.ssm2api.dao.product;

import org.apache.ibatis.annotations.Select;

import com.greatwqs.ssm2api.domain.pojo.product.ProductPojo;

/**
 * 
 * ssm2product.demo_productlist
 * @author greatwqs
 * 
 */
public interface DemoProductListDAO {
	
//	CREATE TABLE `demo_productlist` (
//	  `ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
//	  `Name` varchar(200) NOT NULL DEFAULT '',
//	  `CreateTime` datetime NOT NULL DEFAULT '2013-04-11 00:00:00',
//	  PRIMARY KEY (`ID`)
//	) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
	
	@Select("SELECT ID,Name,CreateTime FROM demo_productlist WHERE ID=#{id}")
	public ProductPojo getProductPojo(int id);
}
