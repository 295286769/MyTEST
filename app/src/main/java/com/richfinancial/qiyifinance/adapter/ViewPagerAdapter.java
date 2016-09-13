package com.richfinancial.qiyifinance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 类描述:    任务发布的横向滑动布局适配器
 * 创建人:    android
 * 创建时间:  2016/5/30
 * 修改人:    android
 * 修改时间:  2016/5/30
 * 修改备注:
 * 版本:      v1.0
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragList; //部件列表

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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
