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
public class HomeBean extends Bean_S_Base {

    private String tag = "";
    private int mResIcon = 0;
    private String title = "";
    private String num = "";

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getmResIcon() {
        return mResIcon;
    }

    public void setmResIcon(int mResIcon) {
        this.mResIcon = mResIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
