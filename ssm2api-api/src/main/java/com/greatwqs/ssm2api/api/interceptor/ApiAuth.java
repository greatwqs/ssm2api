package com.greatwqs.ssm2api.api.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 拦截器注解:
 * 判断api的参数合法性验证!
 * 需要拦截的接口使用此注解注解一个即可
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
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ApiAuth {
}