package com.richfinancial.qiyifinance;

import android.app.Application;

import com.richfinancial.qiyifinance.utils.ImageUtils;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageUtils.initConfiguration(getApplicationContext());
    }
}
