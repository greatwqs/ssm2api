package com.greatwqs.ssm2api.domain.pojo.product;

import java.util.Date;

/***
 * 
 * 商品数据库模型, 对应表: ssm2product.demo_productlist
 * @author greatwqs
 *
 */
public class ProductPojo {

	private int id;
	private String name;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
