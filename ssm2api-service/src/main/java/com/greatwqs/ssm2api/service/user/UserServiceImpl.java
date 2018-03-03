package com.greatwqs.ssm2api.service.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.greatwqs.ssm2api.common.exception.HiException;
import com.greatwqs.ssm2api.common.exception.HiStatus;
import com.greatwqs.ssm2api.common.util.Log4jUtil;
import com.greatwqs.ssm2api.dao.user.DemoUserListDAO;
import com.greatwqs.ssm2api.domain.pojo.user.UserPojo;

/***
 * 
 * 样例用户Service
 * @author greatwqs
 *
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger svcLog = Log4jUtil.svcLog;

    @Resource
    private DemoUserListDAO demoUserListDAO;

    /***
     * @param userID
     * @return
     */
    public UserPojo getUserPojo(int userID) throws HiException {
        if (userID == 0) {
            svcLog.warn("UserService, getUserPojo, userID:" + userID);
            throw new HiException(HiStatus.RESOURCE_NOT_FOUND);
        } else if (userID == 99) {
            svcLog.warn("UserService, getUserPojo, userID:" + userID);
            throw new NullPointerException();
        }

        UserPojo pojo = demoUserListDAO.getUserPojo(userID);
        svcLog.warn("UserService, getUserPojo, pojo:" + pojo.toString());
        return pojo;
    }
}
