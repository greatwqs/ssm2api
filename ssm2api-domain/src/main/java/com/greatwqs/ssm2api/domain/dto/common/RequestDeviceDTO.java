package com.greatwqs.ssm2api.domain.dto.common;

import com.greatwqs.ssm2api.common.util.PublicUtil;

/***
 *
 * Device请求参数,应用信息;
 * @author greatwqs
 *
 */
public class RequestDeviceDTO {

    // system_device表主键;
    private int id;
    // 设备生成的唯一ID；
    private String did;
    // channel: 渠道
    private String channel;
    // platform: 1为iOS，2为android；
    private int platform;
    // model: 机器型号，如iphone4,三星xxx；
    private String model;
    // sys_ver: 系统版本，如android4.1；
    private String sys_ver;
    // app_ver：我们app的版本
    private String app_ver;

//	{
//		  "did" : "13181a89c2194883ece8c4432adeb19e37a5d769",
//		  "app_ver" : "1.2.0",
//		  "channel" : "appstore",
//		  "platform" : "1",
//		  "model" : "iPhone 5",
//		  "sys_ver" : "7.0.3"
//        "app_ver" : "1.0.0"
//		}

    /**
     * 这里的参数验证还需要补充;
     *
     * @return
     */
    public boolean validate() {
        if (did == null || did.trim().length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * trim
     *
     * @return
     */
    public void trim() {
        did = PublicUtil.getTrimIfNullValueBlank(did);
        channel = PublicUtil.getTrimIfNullValueBlank(channel);
        model = PublicUtil.getTrimIfNullValueBlank(model);
        // BUG修复: 线上有异常显示model的数字位已经超过50个字符;
        // 在这里进行截取; 保留48个字符;
        if (model != null && model.length() >= 50) {
            model = model.substring(0, 48);
        }
        sys_ver = PublicUtil.getTrimIfNullValueBlank(sys_ver);
        app_ver = PublicUtil.getTrimIfNullValueBlank(app_ver);
    }

    @Override
    public String toString() {
        return "DeviceModel [app_ver=" + app_ver + ", channel=" + channel
                + ", did=" + did + ", id=" + id + ", model=" + model
                + ", platform=" + platform + ", sys_ver=" + sys_ver + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSys_ver() {
        return sys_ver;
    }

    public void setSys_ver(String sysVer) {
        sys_ver = sysVer;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String appVer) {
        app_ver = appVer;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}