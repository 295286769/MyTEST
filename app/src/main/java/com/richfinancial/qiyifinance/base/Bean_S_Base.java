package com.richfinancial.qiyifinance.base;

import java.io.Serializable;

/**
 * BaseNetBean
 * 项目名称:  QYManager
 * 类描述:    返回Bean基类
 * 创建人:    luqb
 * 创建时间:  2016-03-09
 * 修改人:    luqb
 * 修改时间:  2016-03-09
 * 修改备注:  返回Bean基类
 * 版本:      v1.0
 */
public class Bean_S_Base implements Serializable {

    /**
     * code : 错误码
     * msg :  错误信息
     */
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
