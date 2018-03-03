package com.greatwqs.ssm2api.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 客户端验证URL中的参数字符串:
 * data参数和sign参数通过加密后的一致性;
 * 
 * @author greatwqs
 *
 */
public class AuthUtil {
	
	/**
	 * 用于客服端和服务器端的加密字符;
	 */
	public static final String DECODE_STRING_API = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	public static final String DECODE_STRING_WAP = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
	
    /***
	 * 参数验证
	 * @param data : 由接口参数data + device + r组成 
	 * @param sign : 客户端传上来的s
	 * @return
	 */
    public static boolean signIsOkNew(final String data, final String sign) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(sign)) {
            return false;
        }
        final String ourSign = getApiSign(data+DECODE_STRING_API);
        return sign.equals(ourSign);
    }
    
    /***
	 * 针对WAP添加sign验证
	 * @param data : 由接口参数data + device + r组成 
	 * @param sign : 客户端传上来的s
	 * @return
	 */
    public static boolean signIsOkWap(final Map<Object, Object> paramMap) {
    	if(paramMap == null || paramMap.size()==0){
			return false;
		}
    	
    	// 判断 sign 是否OK
		final Object signVal = paramMap.get("sign");
		if (signVal == null) {
			return false;
		}
		
		final String signValStr = signVal.toString(); // sign 的值
		if(StringUtils.isEmpty(signValStr)){
			return false;
		}
    	
    	final TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>();
		treeMap.putAll(paramMap);
		treeMap.put("salt", DECODE_STRING_WAP);
		treeMap.remove("sign");
		
		final StringBuilder sb = new StringBuilder();
		final Iterator<Map.Entry<Object, Object>> iter = treeMap.entrySet().iterator();
		while(iter.hasNext()){
			final Map.Entry<Object, Object> entry = iter.next();
			final String key = getString(entry.getKey());
			final String val = getString(entry.getValue());
			sb.append(key);
			sb.append("=");
			sb.append(val);
			sb.append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		final String paramResult = sb.toString();
		final String md5Result = PublicUtil.encodingMD5(paramResult);
		return StringUtils.equals(signValStr, md5Result);
    }
    
    private static String getString(final Object obj){
    	if(obj == null){
    		return "";
    	}
    	
    	return obj.toString();
    }
    
    /***
     * 判断时间是否OK
     * @param t app中参数diao中的t参数值
     * @param currentTimestamps 当前的时间戳
     * @return
     */
	public static boolean timeIsOk(String t, long currentTimestamps) {
		final long TOMEOUT_MS = 1000L*60*10; // 10分钟就超时
		if (Math.abs(currentTimestamps - Long.parseLong(t)) >= TOMEOUT_MS) {
			return false;
		} else {
			return true;
		}
	}
	
    /***
     * 判断时间是否OK, WAP和H5使用
     * @param timestamp
     * @param currentTimestamps
     * @return
     */
	public static boolean timeIsOkWap(long timestamp, long currentTimestamps) {
		final long TOMEOUT_MS = 1000L*60*10; // 10分钟就超时
		if (Math.abs(currentTimestamps - timestamp) >= TOMEOUT_MS) {
			return false;
		} else {
			return true;
		}
	}
    
	/***
	 * 获取sign;
	 * 
	 * @param data
	 * @return
	 */
	public static String getApiSign(String data) {
		if (data == null) {
			return null;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (final NoSuchAlgorithmException e) {
			return null;
		}
		md.reset();
		try {
			md.update(data.getBytes("UTF-8"));
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		final byte[] hash = md.digest();
		// 转换为十六进制
		final StringBuilder sb = new StringBuilder();
		final int cnt = hash.length;
		for (int i = 0; i < cnt; i++) {
			sb.append(String.format("%02x", hash[i]));
		}
		return sb.toString();
	}
	
	/***
	 * md5 加密
	 * @param s
	 * @return
	 */
	public static String MD5(String s) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] bytes = md.digest(s.getBytes("utf-8"));
	        return toHex(bytes);
	    }
	    catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	/**
	 * md5 加密 辅助方法
	 * @param bytes
	 * @return
	 */
	public static String toHex(byte[] bytes) {
	    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}
    
    /**
     * 获取服务器的签名
     * @param fds
     */
    public static void main(String[] fds){
//    data:{"cur_uid":0,"postID":"4"}
//    sign:647b4dec4ece90b96fc1b8b8cb7e6e40
//    timestamp:1497334941219
//    	final Map<Object, Object> paramMap = new HashMap<Object, Object>();
//    	paramMap.put("cur_uid", "0");
//    	paramMap.put("postID", "4");
//    	paramMap.put("timestamp", "1497336251100");
//    	paramMap.put("sign", "0992c3780fa67ecfc406a6445bc45acb");
//    	System.out.println(signIsOkWap(paramMap));
    }
}
