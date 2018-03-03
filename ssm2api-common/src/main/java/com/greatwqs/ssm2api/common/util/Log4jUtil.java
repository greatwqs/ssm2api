package com.greatwqs.ssm2api.common.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/***
 * 
 * Log4j应用配置信息,在web 的 Spring环境中,
 * spring会自动去把log4j的配置文件找到并做相关的初始化;
 * 
 * @author greatwqs
 *
 */
public class Log4jUtil {

	// 系统日志, 记录异常信息;
	public static final Logger sysLog = Logger.getLogger("SYS");
	// 应用日志, 记录接口的访问信息;
	public static final Logger apiLog = Logger.getLogger("API");
	// 服务日志, 记录服务日志的情况;
	public static final Logger svcLog = Logger.getLogger("SVC");
	// sign拦截日志
	public static final Logger itcLog = Logger.getLogger("ITC");

	/***
	 * 异常日志;
	 * 
	 * @param exp
	 */
	public static void exception(Exception exp) {
		String exceptionLog = null;
		exception(exp, exceptionLog);
	}

	/***
	 * 异常日志;
	 * 
	 * @param exp
	 * @param exceptionLog
	 *            用于在出异常时, 由业务传入异常的Log, 方便跟踪;
	 */
	public static void exception(Exception exp, String exceptionLog) {
		if (exp == null) {
			sysLog.error("ExceptionLog:" + exceptionLog + ", Exception NULL.");
			return;
		}

		try {
			StringBuilder sb = new StringBuilder();
			if (exceptionLog != null && exceptionLog.length() > 0) {
				sb.append("ExceptionLog:").append(exceptionLog).append(". ");
			}

			StackTraceElement[] s = exp.getStackTrace();
			// 针对java.lang.NollPointerException的异常;
			// exp.getMessage()返回为null;
			String expMsg = PublicUtil.getTrimIfNullValueBlank(exp.getMessage());
			sb.append(expMsg).append("\r\n");

			if (s != null) {
				for (int i = 0; i < s.length; i++) {
					StackTraceElement st = s[i];
					sb.append("\t").append(st.toString()).append("\r\n");
				}
			}
			sysLog.error(sb.toString());
		} catch (Exception excp) {
			excp.printStackTrace();
		}
	}

	/***
	 * 异常日志;
	 * 
	 * @param exp
	 */
	@SuppressWarnings("rawtypes")
	public static void exception(Exception exp, HttpServletRequest request) {
		if (exp == null) {
			sysLog.error("unknown error");
			return;
		}
		try {
			StringBuilder em = new StringBuilder();
			em.append("---------------------error-info---------------------\r\n");
			// 优化,打印request所有信息
			em.append("Request URI:" + request.getRequestURI()).append("\r\n");
			em.append("Request Content-Type:" + request.getContentType()).append("\r\n");
			em.append("Request method:" + request.getMethod()).append("\r\n");
			em.append("Request characterEncoding:" + request.getCharacterEncoding()).append("\r\n");
			em.append("Request-Header:\r\n");
			Enumeration requestHeaders = request.getHeaderNames();
			for (; requestHeaders.hasMoreElements();) {
				String headerName = requestHeaders.nextElement().toString();
				String headerValue = request.getHeader(headerName);
				em.append("Header-" + headerName + ":" + headerValue).append("\r\n");
			}
			em.append("Request-Request:\r\n");
			Enumeration requestParams = request.getParameterNames();
			for (; requestParams.hasMoreElements();) {
				String paramName = requestParams.nextElement().toString();
				String paramValue = request.getParameter(paramName);
				em.append("Param-" + paramName + ":" + paramValue).append("\r\n");
			}
			// 针对java.lang.NollPointerException的异常;
			// exp.getMessage()返回为null;
			em.append("Class : ").append(exp.getClass()).append("\r\n");
			em.append("Meassage : ").append(PublicUtil.getTrimIfNullValueBlank(exp.getMessage())).append("\r\n");
			StackTraceElement[] s = exp.getStackTrace();
			if (s != null) {
				for (int i = 0, len = s.length; i < len; i++) {
					StackTraceElement st = s[i];
					em.append("\t\t").append(st.toString()).append("\r\n");
				}
			}
			em.append("---------------------error-end---------------------\r\n");
			sysLog.error(em.toString());
		} catch (Exception excp) {
			excp.printStackTrace();
		}
	}

	/**
	 * getLogger
	 * 
	 * @param logName
	 * @return
	 */
	public static Logger getLogger(String logName) {
		if (logName == null || logName.trim().length() == 0) {
			return null;
		}
		try {
			return Logger.getLogger(logName);
		} catch (Exception excp) {
			excp.printStackTrace();
			Log4jUtil.exception(excp);
		}
		return null;
	}

	public static void main(String[] arg) {
		exception(new NullPointerException());
	}
}
