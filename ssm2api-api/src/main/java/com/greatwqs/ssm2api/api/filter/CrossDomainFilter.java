package com.greatwqs.ssm2api.api.filter;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.common.collect.Lists;
//
///**
// * <pre>
// * wap层级下所有接口统一设置h5跨域支持header
// * </pre>
// * 
// * @author greatwqs
// */
//public class CrossDomainFilter implements Filter {
//
//    /** 允许域名带cookie访问接口, 不受浏览器限制的域名列表 **/
//    private static final List<String> DOMAINS_ALLOW_COOKIE_LIST = Lists.newArrayList("http://api.greatwqs.com",
//            "http://develop.greatwqs.com", "http://share.greatwqs.com");
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    /**
//     * 
//     * 为WAP设置真题跨域设置responseHeaer;
//     * 
//     * 允许DOMAINS_ALLOW_COOKIE_LIST带cookie跨域请求, 用于在 /wap/* 接口中使用cookie请求;
//     * 其他非DOMAINS_ALLOW_COOKIE_LIST得列表,
//     * 可以使用跨域Header("Access-Control-Allow-Origin", "*");
//     * 
//     * @param request
//     * @param response
//     * @param chain
//     * @return
//     * @throws IOException, ServletException
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        if (response instanceof HttpServletResponse) {
//            final HttpServletRequest servletRequest = (HttpServletRequest) request;
//            String originDomain = "";
//            if (servletRequest.getHeader("Origin") == null || servletRequest.getHeader("Origin").length() == 0) {
//                originDomain = "";
//            } else {
//                originDomain = servletRequest.getHeader("Origin").trim();
//            }
//            if (DOMAINS_ALLOW_COOKIE_LIST.contains(originDomain)) {
//                // 如果浏览器访问允许带cookie, 必须把来的域名写死
//                // 允许带cookie
//                ((HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials", "true");
//                // 允许跨域请求
//                ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", originDomain);
//            } else {
//                // 如果浏览器访问不带cookie, 可以直接使用下面的*
//                ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
//            }
//        }
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//}
