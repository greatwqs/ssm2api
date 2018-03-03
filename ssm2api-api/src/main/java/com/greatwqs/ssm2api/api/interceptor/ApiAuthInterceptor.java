package com.greatwqs.ssm2api.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.JsonSyntaxException;
import com.greatwqs.ssm2api.common.exception.HiException;
import com.greatwqs.ssm2api.common.exception.HiStatus;
import com.greatwqs.ssm2api.common.util.AuthUtil;
import com.greatwqs.ssm2api.common.util.Log4jUtil;

/**
 * 
 * API 参数验证拦截器
 * 
 * API请求参数:
 * data : {"cui_uid":"69975"}
 * device : {"did":"hgfd","app_ver":"1.0.0"}
 * r : {"t":"1325435432121","h":"A5881855e948B7F3e19Cb6A5e4B05558Da8eab57"}
 * s : "dsds3213123123131"
 * 
 * springMVC中使用自定义注解来进行登录拦截控制
 * https://www.cnblogs.com/xuyou551/p/8111023.html
 * 
 * @author greatwqs
 *
 */
public class ApiAuthInterceptor extends HandlerInterceptorAdapter {
	
	private Logger itcLog = Log4jUtil.itcLog;
	
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		itcLog.warn("ApiAuthInterceptor, preHandle execute.");
		// 处理handler;
        if (handler instanceof HandlerMethod) {
            // 判断当前method上是否有标签;
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getMethodAnnotation(ApiAuth.class) != null) {
            	checkRequestParam(request);
            	return true;
            }
        }
        
        // 处理handler;
        return super.preHandle(request, response, handler);
	}
	
	/**
	 * 验证输入参数是否OK;
	 * @param request
	 * @throws HiException 
	 */
	private void checkRequestParam(final HttpServletRequest request) throws HiException{
		final String data = request.getParameter("data"); // 业务参数
		final String device = request.getParameter("device"); // 设备参数
		final String random = request.getParameter("r"); // random 随机混淆参数
		final String sign = request.getParameter("s"); // sign 加密参数
		try {
			// 参数非空判断
			if (StringUtils.isEmpty(data) || StringUtils.isEmpty(device) 
					|| StringUtils.isEmpty(random) || StringUtils.isEmpty(sign)) {
				itcLog.warn("ApiAuthInterceptor, param null! data:" + data 
						+ ",decice:" + device + ",random:" + random+ ", sign:" + sign);
				throw new HiException(HiStatus.ITC_REQUEST_PARAM_ERROR);
			}
			
			// 获取diao 中的时间戳time 和随机数r
			final JSONObject randomObject = new JSONObject(random);
			final String time = randomObject.optString("t");
			final long timestamps = System.currentTimeMillis();
			if (! AuthUtil.timeIsOk(time, timestamps)) {
				itcLog.warn("ApiAuthInterceptor, time out! randomObject:" + randomObject);
				throw new HiException(HiStatus.ITC_REQUEST_PARAM_ERROR, "系统时间与服务器时间不一致,请修改!");
			}
			
			final StringBuilder builder = new StringBuilder();
			builder.append(data);
			builder.append(device);
			builder.append(random);
			if (! AuthUtil.signIsOkNew(builder.toString(), sign)) {
				itcLog.warn("ApiAuthInterceptor, sign error! data:" + data 
						+ ",decice:" + device + ",random:" + random + ",sign:" + sign);
				throw new HiException(HiStatus.ITC_REQUEST_PARAM_ERROR, "SIGN参数错误!");
			}
		} catch (JSONException jsonExp) { // org.json.JSONException
			itcLog.error("ApiAuthInterceptor, JSONException" 
					+ ", data:" + data + ", device:" + device
					+ ", random:" + random + ", sign:" + sign);
		} catch (JsonSyntaxException jsonSynExp) { // com.google.gson.JsonSyntaxException
			itcLog.error("ApiAuthInterceptor, JsonSyntaxException" 
					+ ", data:" + data + ", device:" + device
					+ ", random:" + random + ", sign:" + sign);
		}
	}
}