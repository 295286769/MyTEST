package com.richfinancial.qiyifinance.bean;

import com.richfinancial.qiyifinance.base.Bean_S_Base;

/**
 * 类描述:    首页
 * 创建人:    android
 * 创建时间:  2016/5/30
 * 修改人:    android
 * 修改时间:  2016/5/30
 * 修改备注:
 * 版本:      v1.0
 */
public class NormalUserBean extends Bean_S_Base {

    private String userName = "";
    private String userMail = "";
    private String userPhone = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }


}
