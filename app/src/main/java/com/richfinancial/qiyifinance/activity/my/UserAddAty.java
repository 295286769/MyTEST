package com.richfinancial.qiyifinance.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;

/**
 * Created by LENOVO on 2016/9/6.
 */
public class UserAddAty extends BaseActivity {

    private ImageButton  m_imgbtnRight;//标题栏左侧按钮
    private  ImageButton  m_imgbtnLeft;
    private TextView m_txtvTitle;//标题栏中间标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_uer_add);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_imgbtnLeft= (ImageButton) this.findViewById(R.id.imgbtn_left);
        m_imgbtnRight= (ImageButton) this.findViewById(R.id.imgbtn_right);
        m_txtvTitle= (TextView) this.findViewById(R.id.txtv_title);
        m_txtvTitle.setText("用户新增");
    }

    @Override
    public void processingLogic() {
        m_imgbtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        m_imgbtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
    }

    /***
     * 修改密码网络请求
     */
    private void saveUser(){

    }
}
