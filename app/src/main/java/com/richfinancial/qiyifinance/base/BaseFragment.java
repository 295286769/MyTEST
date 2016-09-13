package com.richfinancial.qiyifinance.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richfinancial.qiyiplus.R;

/**
 * Simple To Introduction
 * 项目名称:  XZBBusinessHelper
 * 包:        [${package_name}]
 * 类名称:    [${type_name}]
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    luqb
 * 创建时间:  [${date} ${time}]
 * 修改人:    luqb
 * 修改时间:  [${date} ${time}]
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public abstract class BaseFragment extends Fragment {
    public int layout;
    public View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        rootView = inflater.inflate(layout, container, false);
        initView();
        processingLogic();
        return rootView;
    }

    // 初始化UI setContentView
    public void initLayout(int layout){
        this.layout = layout;
    }
    /** 返回View */
    public View getView(){
        return rootView;
    }

    // 初始化控件
    public abstract void initView();
    // 初始化数据
    public abstract void initData();
    // 逻辑处理
    public abstract void processingLogic();
}
