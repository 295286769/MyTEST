package com.richfinancial.qiyifinance.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.richfinancial.qiyifinance.utils.ActivityCollector;


/**
 * Simple To Introduction
 * 项目名称:
 * 包:        com.lc.demo.base
 * 类名称:    BaseActivity
 * 类描述:    Activity基类
 * 创建人:    luqb
 * 创建时间:  2016-08-16
 * 修改人:    luqb
 * 修改时间:  2016-08-16
 * 修改备注:  Activity基类
 * 版本:      v1.0
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ActivityCollector.addActivity(this);
        initContentView(savedInstanceState);
        initData();
        initView();
        processingLogic();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }

    // 初始化UI setContentView
    protected abstract void initContentView(Bundle savedInstanceState);
    // 初始化控件
    public abstract void initView();
    // 初始数据
    public abstract void initData();
    // 逻辑处理
    public abstract void processingLogic();
}
