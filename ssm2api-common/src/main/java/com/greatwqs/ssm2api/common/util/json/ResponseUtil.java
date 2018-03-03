package com.greatwqs.ssm2api.common.util.json;

import org.apache.log4j.Logger;

import com.greatwqs.ssm2api.common.util.Log4jUtil;

/***
 * 
 * 请求工具类; 返回json数据;
 * @author greatwqs
 *
 */
public class ResponseUtil {
    
    public static final Logger sysLog = Log4jUtil.sysLog;

    public static final String JSON_API_INTERNET_BUSY = "{\"code\":100,\"data\":\"服务器忙，请稍后再试！\"}";
    
    public static final String RESULT_CODE = "code";
    public static final String RESULT_DATA = "data";

    public static String succ() {
        return succ("");
    }
    
    /***
     * 默认使用的;
     * @param data
     * @return
     */
    public static String succ(Object data) {
        try {
            GsonModel model = new GsonModel();
            model.setCode(0);
            model.setData(data);
            return GSonUtil.getJson(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            Log4jUtil.exception(exp);
        }
        return JSON_API_INTERNET_BUSY;
    }
    
    /***
     * 含有版本信息的返回;
     * @param appVersion
     * @param data
     * @return
     */
    public static String succ(double appVersion, Object data) {
        try {
            GsonModel model = new GsonModel();
            model.setCode(0);
            model.setData(data);
            return GSonUtil.getJson(appVersion, model);
        } catch (Exception exp) {
            exp.printStackTrace();
            Log4jUtil.exception(exp);
        }
        return JSON_API_INTERNET_BUSY;
    }
    
    /***
     * 状态码和自定义的错误信息;
     * @param code
     * @param data
     * @return
     */
    public static String error(int code, String data) {
        try {
            GsonModel model = new GsonModel();
            model.setCode(code);
            model.setData(data);
            return GSonUtil.getJson(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            Log4jUtil.exception(exp);
        }
        return JSON_API_INTERNET_BUSY;
    }
}
