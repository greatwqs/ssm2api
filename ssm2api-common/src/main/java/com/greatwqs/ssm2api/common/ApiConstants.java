package com.greatwqs.ssm2api.common;

/**
 * 
 * 后端常量字符串
 * @author greatwqs
 *
 */
public class ApiConstants {

	/**
	 * 是否是线上环境? 不是开发或者测试环境, 在web.xml中配置;
	 */
	public static boolean IS_PRODUCTION = true;
	
	/**
	 * WEB工程在部署时绝对地址,如 "/home/web/deployment/ssm2api/" 
	 */
	public static String rootDir; 
	
	/**
	 * WEB集群的INDEX_VALUE; 用于订单号唯一生成算法使用,在Listener中初始化;
	 */
	public static String CLUSTER_INDEX_VALUE = "1";
}