package com.richfinancial.qiyiplus;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import com.richfinancial.qiyifinance.base.BaseActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by huangweishui on 2016/9/12.
 */
public class InFormationActivity extends BaseActivity {
    @ViewInject( id = R.id.name,click = "onClick") TextView btn_apply;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_info);

    }
    /**
     *
     */
    public void setName() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processingLogic() {

    }
}
