package com.greatwqs.ssm2api.api.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alibaba.fastjson.parser.ParserConfig;
import com.greatwqs.ssm2api.common.ApiConstants;

/***
 * 
 * 用于自身的参数初始化;
 * @author greatwqs
 *
 */
public class HiListener implements ServletContextListener {
	
	/***
	 * 初始化系统参数;
	 */
    @Override
	public void contextInitialized(ServletContextEvent event) {
    	try{
    		final ServletContext servletContext = event.getServletContext();
	    	// 初始化部署工程的完整路径;
    		ApiConstants.rootDir = servletContext.getRealPath("/"); 
	        System.out.println("HiListener, ApiConstants.rootDir: " +ApiConstants.rootDir);
	        // 从web.xml中取得集群的节点cluster_index_value;
	        ApiConstants.CLUSTER_INDEX_VALUE = servletContext.getInitParameter("cluster_index_value"); 
	        System.out.println("HiListener, ApiConstants.CLUSTER_INDEX_VALUE: " +ApiConstants.CLUSTER_INDEX_VALUE);
	        // 从web.xml中取得is_production, 是否是线上环境!
	        String isProductionString = servletContext.getInitParameter("is_production"); 
	        ApiConstants.IS_PRODUCTION = (isProductionString!=null && "true".equalsIgnoreCase(isProductionString));
	        System.out.println("HiListener, ApiConstants.IS_PRODUCTION: " +ApiConstants.IS_PRODUCTION);	        
	        // 调整FastJson的策略（应对autoType设置导致的序列化问题）： 参见https://github.com/alibaba/fastjson/wiki/enable_autotype
	        ParserConfig.getGlobalInstance().addAccept("com.greatwqs.ssm2api."); 
    	} catch (Exception exp) { 
    		exp.printStackTrace();
    	}
    }
    
    @Override
	public void contextDestroyed(ServletContextEvent arg0) {}
}
