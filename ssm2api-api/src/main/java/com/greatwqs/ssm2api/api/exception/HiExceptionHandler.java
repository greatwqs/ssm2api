package com.greatwqs.ssm2api.api.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.greatwqs.ssm2api.common.exception.HiException;
import com.greatwqs.ssm2api.common.exception.HiStatus;
import com.greatwqs.ssm2api.common.util.Log4jUtil;

/**
 * 
 * 异常集中在这里进行处理;
 * 
 * Spring MVC异常统一处理的三种方式:
 * https://www.cnblogs.com/junzi2099/p/7840294.html
 * 
 * Spring MVC全局异常后返回JSON异常数据:
 * http://blog.csdn.net/chwshuang/article/details/48089203
 * 
 * @author greatwqs
 *
 */
@Component
public class HiExceptionHandler implements HandlerExceptionResolver {
	
	private Logger sysLog = Log4jUtil.sysLog;
	
	/**
	 * 全局异常捕获
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception exp) {
		
		sysLog.warn("HiExceptionHandler, resolveException execute.");
		
		// 全局使用 HiException 返回错误信息;
		HiException exception = new HiException(HiStatus.SERVER_ERROR); 
		if (exp instanceof HiException) {
			exception = (HiException) exp;
		} else {
			// 非HiException, 记录到系统日志文件, sys.log
			Log4jUtil.exception((Exception) exp, request);
		}

		// 清除异常信息, 避免返回给前端页面
		WebUtils.clearErrorRequestAttributes(request);

		// 返回 ModelAndView
		final ModelAndView modelAndView = new ModelAndView(); 
		final FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();  
        final Map<String, Object> exceptionParamMap = new HashMap<String, Object>();  
        exceptionParamMap.put("code", exception.getCode());  
        exceptionParamMap.put("data", exception.getMessage());  
        fastJsonJsonView.setAttributesMap(exceptionParamMap);  
        modelAndView.setView(fastJsonJsonView);   
        return modelAndView;
	}
}