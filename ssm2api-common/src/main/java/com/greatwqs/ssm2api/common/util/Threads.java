package com.greatwqs.ssm2api.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author greatwqs
 * @since 2015-06-18
 */
public final class Threads {

    private Threads() {
    }

    /**
     * 获取调用此方法处的类名#方法名(不带形参列表)#代码行数
     * 重载的情况下存在同名的方法, 可通过代码行数区分
     */
    public static String getCallLocation() {
        try {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
            return ste.getClassName() + "#" + ste.getMethodName() + "#" + ste.getLineNumber();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获取异常发生处的 EXCEPTION : e.getMessage() ON 类名#方法名(不带形参列表)#代码行数
     * EXCEPTION : / by zero ON com.yunyao.mocha.util.lang.Threads#main#44
     */
    public static String getExceptionLocation(Exception e) {
        try {
            StackTraceElement ste = e.getStackTrace()[0];
            StringBuilder message = new StringBuilder(128);
            message.append("EXCEPTION : ").append(e.getMessage()).append(" ON ").append(ste.getClassName()).append("#").append(ste.getMethodName()).append("#").append(ste.getLineNumber());
            return message.toString();
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println(getExceptionLocation(e));
        }
    }
}
