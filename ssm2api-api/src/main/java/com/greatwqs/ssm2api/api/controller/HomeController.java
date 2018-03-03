package com.greatwqs.ssm2api.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 首页接口 API
 * 
 * springmvc请求参数获取的几种方法
 * https://www.cnblogs.com/xiaoxi/p/5695783.html
 * 
 * @author greatwqs
 *
 */
@Controller
public class HomeController {
	
    @RequestMapping("/")
    public String index(){
        return "redirect:/index.html";
    }
}
