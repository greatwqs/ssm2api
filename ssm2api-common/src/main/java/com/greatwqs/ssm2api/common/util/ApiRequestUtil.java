package com.greatwqs.ssm2api.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/***
 * 
 * api请求的工具类
 * 向外暴露三个公共方法: 
 * public static String getResultByPost(String apiURL)
 * public static String getResultByPost(String apiURL, String dataJson)
 * public static String getResultByPost(String apiURL, String dataJson, String deviceJson)
 * 
 * API请求参数:
 * data : {"cui_uid":"69975"}
 * device : {"did":"hgfd","app_ver":"1.0.0"}
 * r : {"t":"1325435432121","h":"A5881855e948B7F3e19Cb6A5e4B05558Da8eab57"}
 * s : "dsds3213123123131"
 * 
 * @author greatwqs
 *
 */
public class ApiRequestUtil {
	
	/**
	 * 默认的data请求参数
	 */
	private static final String DEFUALT_JSON_DATA = "{\"cur_uid\":\"0\"}";
	
	/**
	 * 默认的device请求参数: 为iOS的系统,V6.0.0版本
	 */
	private static final String DEFUALT_JSON_DEVICE = "{"+
				  "\"channel\" : \"appstore\","+
				  "\"did\" : \"DEFUALT-JSON-DEVICE-ID-TEST\","+
				  "\"app_ver\" : \"1.0.0\","+
				  "\"sys_ver\" : \"9.3.1\","+
				  "\"model\" : \"iPhone8,1\","+
				  "\"platform\" : \"1\","+
				  "\"imsi\" : \"46002\","+
				  "\"ua\" : \"Mozilla\","+
				  "\"oid\" : \"99498efc3c2da3d9d42f57ace85b3f8c4e48a843\","+
				  "\"conn\" : \"1\","+
				  "\"idfa\" : \"25D44944-F010-437F-A393-EBE3AC50C42B\""+
				  "}";
	
	public static void main(String[] fds){
		String returnData = getResultByPost("http://127.0.0.1:8080/", DEFUALT_JSON_DATA);
		System.out.println(returnData);
	}
	
	/***
	 * 把data数据进行封装后, 把数据POST到apiURL接口地址;
	 * @param apiURL 接口URL
	 * @return
	 */
    public static String getResultByPost(String apiURL) {
    	List<NameValuePair> requestParamList = getRequestParamList(null, null);
        final String requestParam = URLEncodedUtils.format(requestParamList, "utf-8");
        return postToServer(apiURL, requestParam);
    }
	
	/***
	 * 把data数据进行封装后, 把数据POST到apiURL接口地址;
	 * @param apiURL 接口URL
	 * @param data 接口参数名为data的数据
	 * @return
	 */
    public static String getResultByPost(String apiURL, String dataJson) {
    	List<NameValuePair> requestParamList = getRequestParamList(dataJson, null);
        final String requestParam = URLEncodedUtils.format(requestParamList, "utf-8");
        return postToServer(apiURL, requestParam);
    }
    
	/***
	 * 把data数据进行封装后, 把数据POST到apiURL接口地址;
	 * @param apiURL 接口URL
	 * @param dataJson 接口参数名为data的数据
	 * @param deviceJson 接口参数名为device的数据
	 * @return
	 */
    public static String getResultByPost(String apiURL, String dataJson, String deviceJson) {
    	List<NameValuePair> requestParamList = getRequestParamList(dataJson, deviceJson);
        final String requestParam = URLEncodedUtils.format(requestParamList, "utf-8");
        return postToServer(apiURL, requestParam);
    }
    
    /***
     * 生成random请求参数
     * @return
     */
    private static String generateRandomJSON(){
    	// h hash, t time
    	final String param = "{\"h\":\"#RANDOM-STRING#\",\"t\":#TIMESTAMP#}";
    	// RANDOM-STRING不用随机, 这里直接写死;
    	return param.replace("#RANDOM-STRING#", "frk3ztxar6mtjr1h25pp38t7n2toognh")
    	            .replace("#TIMESTAMP#", String.valueOf(System.currentTimeMillis()));
    }
    
    /***
     * 根据dataJson & deviceJson, 生成请求的参数的List;
     * @param dataJson  api请求的data的Json参数
     * @param deviceJson  api请求的device的Json参数
     * @return
     */
    private static List<NameValuePair> getRequestParamList(final String dataJson, final String deviceJson) {
        List<NameValuePair> info = new ArrayList<NameValuePair>();
        
        // data
        String dataJsonResult = DEFUALT_JSON_DATA;
        if(dataJson != null && dataJson.trim().length()>0){
        	dataJsonResult = dataJson;
        }
		info.add(new BasicNameValuePair("data", dataJsonResult));
        
        // device
        String deviceJsonResult = DEFUALT_JSON_DEVICE;
        if(deviceJson != null && deviceJson.trim().length()>0){
        	deviceJsonResult = deviceJson;
        }
        info.add(new BasicNameValuePair("device", deviceJsonResult));
        
        // r random
        final String diaoResult = generateRandomJSON();
        info.add(new BasicNameValuePair("r", diaoResult));
        
        // s sign
		final String sign = AuthUtil.getApiSign(dataJsonResult + deviceJsonResult 
				+ diaoResult + AuthUtil.DECODE_STRING_API);
        info.add(new BasicNameValuePair("s", sign));
        return info;
    }

	/***
	 * 将生成的请求requestParam通过POST发送到apiURL接口
	 * @param apiURL
	 * @param requestParam
	 * @return
	 */
    private static String postToServer(final String apiURL, String requestParam) {
        if (apiURL == null) {
            return "Error apiURL is null.";
        }

        HttpURLConnection urlConn = null;
        try {
            URL connection = new URL(apiURL);
            // 使用HttpURLConnection打开连接
            urlConn = (HttpURLConnection) connection.openConnection();
            // 因为这个是post请求,设立需要设置为true
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            // 设置以POST方式
            urlConn.setRequestMethod("POST");
            urlConn.setConnectTimeout(20000);
            urlConn.setReadTimeout(20000);
            // Post 请求不能使用缓存
            urlConn.setUseCaches(false);
            urlConn.setInstanceFollowRedirects(true);
            // 5)设置使用POST的方式发送:
            urlConn.setRequestMethod("POST");
            // 6)设置维持长连接:
            urlConn.setRequestProperty("Connection", "close");
            urlConn.setRequestProperty("User-Agent", "Token360(Android)");

            // 7)设置文件字符集:
            urlConn.setRequestProperty("Charset", "UTF-8");
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            urlConn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            urlConn.connect();
            // DataOutputStream流
            if (requestParam != null) {
                DataOutputStream out = new DataOutputStream(
                        urlConn.getOutputStream());
                // 将要上传的内容写入流中
                out.writeBytes(requestParam);
                // 刷新、关闭
				out.flush();
				out.close();
			}

			if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return "Error http statusCode:" + urlConn.getResponseCode();
			}

            // 获取数据
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));
            String inputLine = null;
            // 使用循环来读取获得的数据
            String result = "";
            while (((inputLine = reader.readLine()) != null)) {
                // 我们在每一行后面加上一个"\n"来换行
                result += inputLine + "\n";
            }

            reader.close();

            if (!result.equals("")) {
                return result;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭http连接
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }

        return "Error exception.";
    }
}
