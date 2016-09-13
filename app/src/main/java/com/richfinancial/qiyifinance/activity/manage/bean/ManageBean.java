package com.richfinancial.qiyifinance.activity.manage.bean;

import java.io.Serializable;

/**
 * ManageBean
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.manage.bean
 * 类名称:    ManageBean
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/6
 * 修改人:    baix
 * 修改时间:  2016/9/6
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class ManageBean implements Serializable {

    private String id;
    private String name;
    private String phone;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
