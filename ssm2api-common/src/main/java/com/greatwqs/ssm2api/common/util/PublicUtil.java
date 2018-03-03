package com.greatwqs.ssm2api.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 公共的工具类;
 *
 * @author greatwqs
 */
public class PublicUtil {

    /**
     * MD5加密
     *
     * @param plainText 明文
     * @return 32位密文
     */
    public static final String encodingMD5(String plainText) {
        if (plainText == null || plainText.trim().length() == 0) {
            return null;
        }
        String re_md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            byte b[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder(32);
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (Exception exp) {
            exp.printStackTrace();
            Log4jUtil.exception(exp);
        }
        return re_md5;
    }

    public static String encodingSHA256(String data) {
        if (data == null) {
            return null;
        }
        // 初始化
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
        final StringBuffer sb = new StringBuffer();
        final int cnt = hash.length;
        for (int i = 0; i < cnt; i++) {
            // sb.append(Integer.toHexString((hash[i] >> 4) & 0x0F));
            // sb.append(Integer.toHexString(hash[i] & 0x0F));
            sb.append(String.format("%02x", hash[i]));
        }
        return sb.toString();
    }

    /***
     * 得到Boolean对象的值, 如果对象为null, 则返回false;
     *
     * @param booln
     * @return
     */
    public static final boolean getBooleanValue(Boolean booln) {
        if (booln == null) {
            return false;
        } else {
            return booln.booleanValue();
        }
    }

    /***
     * 得到Integer对象的值, 如果对象为null, 则返回0;
     *
     * @param intObj
     * @return
     */
    public static final int getIntegerValue(Integer intObj) {
        if (intObj == null) {
            return 0;
        } else {
            return intObj.intValue();
        }
    }

    /***
     * 得到Integer对象的值, 如果对象为null, 则返回0;
     *
     * @param str
     * @return
     */
    public static final int getIntegerValue(String str) {
        if (StringUtils.isNotBlank(str) && NumberUtils.isDigits(str)) {
            return Integer.parseInt(str);
        }

        return 0;
    }

    /***
     * 从参数String类型中返回float类型的数值;
     *
     * @param floatValue
     * @return
     */
    public static final float getFloatValue(String floatValue) {
        if (floatValue == null || floatValue.trim().length() == 0) {
            return 0;
        }
        if (!NumberUtils.isNumber(floatValue)) {
            return 0;
        }
        return Float.valueOf(floatValue);
    }

    /***
     * 得到trim后的字符串;如果字符串为null,则返回空串;
     *
     * @param str
     * @return
     */
    public static final String getTrimIfNullValueBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        return str.trim();
    }

    /***
     * 得到trim后的字符串;如果字符串为null或者为"",则返回NULL;
     *
     * @param str
     * @return
     */
    public static final String getTrimIfBlankValueNULL(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        return str.trim();
    }

    /***
     * 得到一个文件的文本内容;
     *
     * @param filePath
     * @return
     */
    public static final String getFileContent(String filePath) {
        String text = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File f = new File(filePath);
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                sb.append(tmp);
            }
            text = sb.toString();
        } catch (Exception exp) {
            Log4jUtil.exception(exp);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException exp) {
                    Log4jUtil.exception(exp);
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException exp) {
                    Log4jUtil.exception(exp);
                }
            }
        }
        return text;
    }

    /***
     * 获取客户端IP地址;这里通过了Nginx获取;X-Real-IP,
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        @SuppressWarnings("unused")
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            fromSource = "X-Forwarded-For";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            fromSource = "Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            fromSource = "WL-Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }
        return getIPForPay(ip);
    }

    private static final String getIPForPay(String ip) {
        if (ip != null && ip.trim().length() > 0) {
            final String[] iparray = ip.split(",");
            if (iparray != null && iparray.length > 0) {
                return iparray[0].trim();
            }
        } else {
            return "127.0.0.1";
        }
        return "127.0.0.1";
    }
}
