package com.greatwqs.ssm2api.common.exception;

/**
 * 
 * 自定义异常状态码
 * @author greatwqs
 *
 */
public enum HiStatus {

	// 所有成功返回
	SUCCESS(0, ""), 
	
	/**
	 * 1-19, 为Filter, Interceptor错误使用;
	 */
	ITC_REQUEST_PARAM_ERROR(1, "服务器验证输入参数错误!"),
	ITC_TIME_ERROR(2, "系统时间与服务器时间不一致,请修改!"),
	ITC_SING_FIELD(3, "服务器验证SIGN错误!"),
	ITC_ACCOUNT_REQUIRED_FIELD(4, "用户登录验证失败!"),
	ITC_WRITE_TOO_FAST(5, "写入操作太频繁!"), 
	ITC_CRAWLER_BLOCKED(6, "!!!!!BUSY!!!!!"), // 反爬虫
	
	/***
	 * 20-99, 为基础业务异常
	 */
	USER_NOT_FOUND(20, "用户没有找到！"),
	USER_DEVICE_IS_LOCKED(21, "用户设备已经被锁，请重新登陆再试！"),
	
	INPUT_PARAM_ERROR(30, "服务器验证输入参数错误！"), 
	RESOURCE_NOT_FOUND(31, "资源没有找到！"),
	CONTENT_SENSITIVE(32, "请不要发布广告或非法内容"),
	
	/**
	 * 100以上, 其他状态码
	 */
	SERVER_ERROR(100, "服务器忙，请稍后再试！"),
	SERVER_APP_VERSION_UPGRADE(101, "请升级最新版本");
	
	private int code;
	
	private String message;

	private HiStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
