package com.richfinancial.qiyifinance.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.richfinancial.qiyifinance.utils.ActivityCollector;


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

public abstract class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        // TODO Auto-generated method stub
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ActivityCollector.addActivity(this);

        initContentView(bundle);
        initData();
        initView();
        processingLogic();
    }

    // 初始化UI setContentView
    protected abstract void initContentView(Bundle savedInstanceState);
    // 初始化控件
    public abstract void initView();
    // 初始化控件
    public abstract void initData();
    // 逻辑处理
    public abstract void processingLogic();

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

}
