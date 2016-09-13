package com.richfinancial.qiyifinance.activity.manage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * HomeViewPagerAdapter
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.main.adapter
 * 类名称:    HomeViewPagerAdapter
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/6
 * 修改人:    baix
 * 修改时间:  2016/9/6
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragList; //部件列表

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragList = fragments;
    }

    @Override
    public int getCount() {
        return mFragList.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return mFragList.get(arg0);
    }

    public List<Fragment> getData() {
        return mFragList;
    }

}
