package com.richfinancial.qiyifinance.activity.manage.bean;

import java.io.Serializable;

/**
 * ParitiesSettingBean
 * 项目名称:  QYManager
 * 包:        com.richfinancial.qiyifinance.activity.manage.bean
 * 类名称:    ParitiesSettingBean
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/8
 * 修改人:    baix
 * 修改时间:  2016/9/8
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class ParitiesSettingBean implements Serializable {

    private String id;
    private String name;
    private String time;
    private String money;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}
