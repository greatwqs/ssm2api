package com.greatwqs.ssm2api.service.product;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.greatwqs.ssm2api.common.exception.HiException;
import com.greatwqs.ssm2api.common.exception.HiStatus;
import com.greatwqs.ssm2api.common.util.Log4jUtil;
import com.greatwqs.ssm2api.common.util.Threads;
import com.greatwqs.ssm2api.dao.product.DemoProductListDAO;
import com.greatwqs.ssm2api.domain.pojo.product.ProductPojo;

/***
 * 
 * 样例商品Service
 * @author greatwqs
 *
 */
@Service
public class DemoProductServiceImpl implements DemoProductService {
	
	private Logger svcLog = Log4jUtil.svcLog;

    @Resource
    private DemoProductListDAO demoProductListDAO;
    
    /***
	 * @param productID
	 * @return
	 */
	public ProductPojo getProductPojo(int productID) throws HiException{
    	if(productID == 0){
    		svcLog.warn(Threads.getCallLocation()+", productID:"+productID);
    		throw new HiException(HiStatus.RESOURCE_NOT_FOUND);
    	} else if(productID == 99){
    		svcLog.warn(Threads.getCallLocation()+", productID:"+productID);
    		throw new NullPointerException();
    	} 
    	
    	ProductPojo pojo = demoProductListDAO.getProductPojo(productID);
    	svcLog.warn(Threads.getCallLocation()+", pojo:"+pojo.toString());
    	return pojo;
    }

}
