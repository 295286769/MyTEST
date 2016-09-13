package com.richfinancial.qiyifinance.base;


/**
 * 类描述:    上传Bean基类
 * 创建人:    android
 * 创建时间:  2016-07-18
 * 修改人:    android
 * 修改时间:  2016-07-18
 * 修改备注:  上传Bean基类
 * 版本:      v1.0
 */

public class Bean_C_Base {

    private String str_account_number = ""; //用户名，即电话号码
    private String str_password = ""; //经过md5加密后的密码
    private String n_phone_type = "2"; //手机类型（0：无类型，1：ios，2：android）
    private String str_phone_version = ""; //	手机型号，例：ipone6s

    public String getStr_account_number() {
        return str_account_number;
    }

    public void setStr_account_number(String str_account_number) {
        this.str_account_number = str_account_number;
    }

    public String getStr_password() {
        return str_password;
    }

    public void setStr_password(String str_password) {
        this.str_password = str_password;
    }

    public String getN_phone_type() {
        return n_phone_type;
    }

    public void setN_phone_type(String n_phone_type) {
        this.n_phone_type = n_phone_type;
    }

    public String getStr_phone_version() {
        return str_phone_version;
    }

    public void setStr_phone_version(String str_phone_version) {
        this.str_phone_version = str_phone_version;
    }
}
