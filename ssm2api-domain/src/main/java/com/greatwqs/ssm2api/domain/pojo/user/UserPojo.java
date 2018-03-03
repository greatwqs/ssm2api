package com.greatwqs.ssm2api.domain.pojo.user;

/***
 *
 * 用户数据库模型, 对应表: ssm2user.demo_userlist
 * @author greatwqs
 *
 */
public class UserPojo {

    // id,userName,createTime
    private int id;
    private String userName;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserPojo [id=" + id + ", userName=" + userName + ", createTime=" + createTime + "]";
    }
}
