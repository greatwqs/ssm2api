package com.greatwqs.ssm2api.service.user;

import com.greatwqs.ssm2api.common.exception.HiException;
import com.greatwqs.ssm2api.domain.pojo.user.UserPojo;

/***
 * 
 * 样例用户Service
 * @author greatwqs
 *
 */
public interface UserService {

    /***
     * @param userID
     * @return
     */
    UserPojo getUserPojo(int userID) throws HiException;
}
