package com.greatwqs.ssm2api.api.controller.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greatwqs.ssm2api.api.interceptor.ApiAuth;
import com.greatwqs.ssm2api.common.exception.HiException;
import com.greatwqs.ssm2api.common.util.Log4jUtil;
import com.greatwqs.ssm2api.common.util.Threads;
import com.greatwqs.ssm2api.common.util.json.ResponseUtil;
import com.greatwqs.ssm2api.service.user.UserServiceImpl;

/**
 * 用户接口 API
 * <p>
 * springmvc请求参数获取的几种方法
 * https://www.cnblogs.com/xiaoxi/p/5695783.html
 *
 * @author greatwqs
 */
@Controller
@RequestMapping("user")
public class UserController {

    private Logger apiLog = Log4jUtil.apiLog;

    @Resource
    private UserServiceImpl demoUserService;

    /**
     * http://127.0.0.1:8080/ssm2api/user/info?userID=1
     * @param userID
     * @return
     * @throws HiException
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "info")
    public Object info(@RequestParam(value = "userID") int userID) throws HiException {
        apiLog.warn(Threads.getCallLocation()+", userID:" + userID);
        return ResponseUtil.succ(demoUserService.getUserPojo(userID));
    }

    /***
     * http://127.0.0.1:8080/ssm2api/user/infotest
     * @param device : {"did":"1234567890"}
     * @param data : {"cur_uid":"1"}
     * @return
     * @throws HiException
     */
    @ApiAuth
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "infotest")
    public Object infoTest(@RequestParam(value = "device") String device,
    		@RequestParam(value = "data") String data) throws HiException {
    	apiLog.warn(Threads.getCallLocation()+", device:" + device+", data:"+data);
        final JSONObject json = new JSONObject(data);
        final int cur_uid = json.optInt("cur_uid");
        return ResponseUtil.succ(demoUserService.getUserPojo(cur_uid));
    }
}
