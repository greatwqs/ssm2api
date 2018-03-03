package com.greatwqs.ssm2api.api.controller.product;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greatwqs.ssm2api.api.interceptor.ApiAuth;
import com.greatwqs.ssm2api.common.exception.HiException;
import com.greatwqs.ssm2api.common.util.Log4jUtil;
import com.greatwqs.ssm2api.common.util.Threads;
import com.greatwqs.ssm2api.common.util.json.ResponseUtil;
import com.greatwqs.ssm2api.service.product.DemoProductService;

/**
 * 
 * 商品接口 API
 * 
 * springmvc请求参数获取的几种方法
 * https://www.cnblogs.com/xiaoxi/p/5695783.html
 * 
 * @author greatwqs
 *
 */
@Controller
@RequestMapping("product")
public class ProductController {
	
	private Logger apiLog = Log4jUtil.apiLog;
	
    @Resource
    private DemoProductService demoProductService;
    
    /**
     * http://127.0.0.1:8080/ssm2api/product/info?productID=1
     * @param request
     * @param response
     * @return
     * @throws HiException
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "info")
    public Object info(@RequestParam(value="productID") int productID) throws HiException {
    	apiLog.warn(Threads.getCallLocation()+", productID:"+productID); 
        return ResponseUtil.succ(demoProductService.getProductPojo(productID));
    }
    
    /**
     * http://127.0.0.1:8080/ssm2api/product/infotest
     * @param request
     * @param response
     * @return
     * @throws HiException
     */
    @ApiAuth
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "infotest")
    public Object infotest(@RequestParam(value="device") String device,
    		@RequestParam(value="data") String data) throws HiException {
    	apiLog.warn(Threads.getCallLocation()+", device:"+device+", data:"+data); 
    	final JSONObject json = new JSONObject(data);
        final int productID = json.optInt("productID");
        return ResponseUtil.succ(demoProductService.getProductPojo(productID));
    }
}
