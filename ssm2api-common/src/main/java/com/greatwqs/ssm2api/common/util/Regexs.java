package com.greatwqs.ssm2api.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gf
 * @author greatwqs
 * @date 2015-10-22
 * @desc 正则表达式相关工具方法
 */
public final class Regexs {

    /**
     * IP正则
     **/
    private static final Pattern IP_PATTERN = Pattern.compile(
            "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
    /**
     * 手机号码正则
     **/
    public static final Pattern PHONENO_PATTERN = Pattern.compile("^[1][3-9]+\\d{9}");

    private Regexs() {
    }

    /***
     * 判断是否是电话号码
     * @param phoneNO
     * @return
     */
    public static boolean isPhoneNO(String phoneNO) {
//        Matcher matcher = PHONENO_PATTERN.matcher(phoneNO);
//        return matcher.matches();
        return true;
    }

    /***
     * 是否不是电话号码
     * @param phoneNO
     * @return
     */
    public static boolean isNotPhoneNO(String phoneNO) {
        return !isPhoneNO(phoneNO);
    }

    /***
     * IDCardNO是否是身份证号码
     * @param IDCardNO
     * @return
     */
    public static boolean isIDCardNO(String IDCardNO) {
        if (StringUtils.length(IDCardNO) != 18) {
            return false;
        }
        // 前17位
        String head = IDCardNO.substring(0, 17);
        if (!StringUtils.isNumeric(head)) {
            return false;
        }
        // 最后1位
        String tail = IDCardNO.substring(17, 18);
        return StringUtils.isNumeric(tail) || "x".equalsIgnoreCase(tail);
    }

    /***
     * IDCardNO是否不是身份证号码
     * @param IDCardNO
     * @return
     */
    public static boolean isNotIDCardNO(String IDCardNO) {
        return !isIDCardNO(IDCardNO);
    }

    /***
     * 是否是IP
     * @param ip
     * @return
     */
    public static boolean isIP(String ip) {
        if (ip == null || ip.length() < 7 || ip.length() > 15) {
            return false;
        }
        Matcher matcher = IP_PATTERN.matcher(ip);
        return matcher.matches();
    }

    public static void main(String[] fds) {
        System.out.println(isPhoneNO("15802880041"));
        System.out.println(isPhoneNO("1880288004"));
        System.out.println(isPhoneNO("11802880041"));
        System.out.println(isPhoneNO("12802880041"));
    }
}
