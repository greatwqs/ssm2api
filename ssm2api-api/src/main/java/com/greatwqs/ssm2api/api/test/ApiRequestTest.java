package com.greatwqs.ssm2api.api.test;

import com.greatwqs.ssm2api.common.util.ApiRequestUtil;

/**
 * 
 * api本地请求的工具类, 请求含 @ApiAuth 注解的接口;
 * @author greatwqs
 * 
 */
public class ApiRequestTest {

    /**
     * 默认的data请求参数
     */
    private static final String JSON_DATA = "{\"cur_uid\":\"1\"}";

    /**
     * 默认的device请求参数: 为iOS的系统
     */
    private static final String JSON_DEVICE = "{" +
            "\"channel\" : \"appstore\"," +
            "\"did\" : \"DEFUALT-JSON-DEVICE-INFO-TEST\"," +
            "\"app_ver\" : \"1.0.0\"," +
            "\"sys_ver\" : \"9.3.1\"," +
            "\"model\" : \"iPhone8,1\"," +
            "\"platform\" : \"1\"" +
            "}";

    public static void main(String[] args) {
        String response = ApiRequestUtil.getResultByPost(
                "http://127.0.0.1:8080/ssm2api/user/infotest", JSON_DATA, JSON_DEVICE);
        System.out.println("response Text: " + response);
    }
}
