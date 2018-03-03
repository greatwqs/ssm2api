package com.greatwqs.ssm2api.service.product;

import com.greatwqs.ssm2api.common.exception.HiException;
import com.greatwqs.ssm2api.domain.pojo.product.ProductPojo;

/***
 * 
 * 样例商品Service
 * @author greatwqs
 *
 */
public interface DemoProductService {

	/***
	 * @param productID
	 * @return
	 */
	public ProductPojo getProductPojo(int productID) throws HiException;
    
}
