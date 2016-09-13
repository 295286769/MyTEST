package com.richfinancial.qiyifinance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.richfinancial.qiyifinance.activity.login.LoginAty;
import com.richfinancial.qiyifinance.activity.main.MainAty;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyiplus.R;


/**
 * 类描述:    启动页
 * 创建人:    android
 * 创建时间:  2016-09-09
 * 修改人:    android
 * 修改时间:  2016-09-08
 * 修改备注:  启动页
 * 版本:      v1.2
 */

public class StartUpAty extends BaseActivity {

    /**
     * 在没有账号信息的情况下需要延迟一段时间，用来显示欢迎页
     */
    private Handler m_delayStart = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //进入登录页面
            Intent intent = new Intent();
            intent.setClass(StartUpAty.this, MainAty.class);
            startActivity(intent);
            finish();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示
        m_delayStart.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start_up);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void processingLogic() {

    }
}
