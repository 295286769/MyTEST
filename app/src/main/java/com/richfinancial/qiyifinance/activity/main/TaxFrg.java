package com.richfinancial.qiyifinance.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyifinance.activity.tax.AccountTaxAty;
import com.richfinancial.qiyifinance.base.BaseFragment;
import com.richfinancial.qiyiplus.R;


/**
 * 类描述:    报税
 * 创建人:    android
 * 创建时间:  2016-03-09
 * 修改人:    android
 * 修改时间:  2016-03-09
 * 修改备注:  登录页
 * 版本:      v1.0
 */
public class TaxFrg extends BaseFragment {

    private ImageButton m_imgbtnRight;//标题栏左侧按钮
    private  ImageButton  m_imgbtnLeft;
    private TextView m_txtvTitle;//标题栏中间标题
    private Button btn_apply;//立即申请按钮
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initLayout(R.layout.activity_tax);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_imgbtnLeft= (ImageButton) getView().findViewById(R.id.imgbtn_left);
        m_imgbtnRight= (ImageButton) getView().findViewById(R.id.imgbtn_right);
        btn_apply=(Button) getView().findViewById(R.id.btn_apply);
        m_imgbtnLeft.setVisibility(View.INVISIBLE);
        m_imgbtnRight.setVisibility(View.INVISIBLE);
        m_txtvTitle= (TextView) getView().findViewById(R.id.txtv_title);
        m_txtvTitle.setText("报税");
    }

    @Override
    public void processingLogic() {
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onApplyClick(v);
            }
        });
    }
    /**
     *
     * @param v
     *  * 跳转到代账报税页面
     */
    private void onApplyClick(View v){

        Intent intent = new Intent();
        intent.setClass(getActivity(), AccountTaxAty.class);
        startActivity(intent);
    }
}