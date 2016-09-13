package com.richfinancial.qiyifinance.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.activity.my.MyChildUserAty;
import com.richfinancial.qiyifinance.activity.my.PersonalInformationAty;
import com.richfinancial.qiyifinance.base.BaseFragment;

import net.tsz.afinal.annotation.view.ViewInject;


/**
 * 类描述:    我的
 * 创建人:    android
 * 创建时间:  2016-03-09
 * 修改人:    android
 * 修改时间:  2016-03-09
 * 修改备注:  登录页
 * 版本:      v1.0
 */
public class MyFrg extends BaseFragment {

    private RelativeLayout m_headLayout;//头像Layout
    private RelativeLayout m_childLayout;//子用户Layout
    private RelativeLayout m_helpLayout;//新手帮助Layout
    private RelativeLayout m_suggestLayout;//意见反馈Layout
    private RelativeLayout m_aboutLayout;//关于Layout
    @Override
    public void initData() {
        initLayout(R.layout.activity_my);
    }

    @Override
    public void initView() {
        ImageButton  m_imgbtnLeft= (ImageButton) getView().findViewById(R.id.imgbtn_left);
        ImageButton  m_imgbtnRight= (ImageButton) getView().findViewById(R.id.imgbtn_right);
        TextView m_txtvTitle= (TextView) getView().findViewById(R.id.txtv_title);
        m_imgbtnLeft.setVisibility(View.INVISIBLE);
        m_imgbtnRight.setVisibility(View.INVISIBLE);
        m_txtvTitle.setText("我的");
        m_headLayout=(RelativeLayout)getView().findViewById(R.id.head_layout);
        m_childLayout=(RelativeLayout)getView().findViewById(R.id.my_child_layout);
        m_helpLayout=(RelativeLayout)getView().findViewById(R.id.my_help_layout);
        m_suggestLayout=(RelativeLayout)getView().findViewById(R.id.my_suggestion_layout);
        m_aboutLayout=(RelativeLayout)getView().findViewById(R.id.my_about_layout);

    }

    public void processingLogic() {
        //头像点击
        m_headLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImgHeadClick(v);
            }
        });
        //子用户点击
        m_childLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChildUserClick(v);
            }
        });
        //新手帮助点击
        m_helpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHelpClick(v);
            }
        });
        //意见反馈点击
        m_suggestLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSuggestClick(v);
            }
        });
        //关于点击
        m_aboutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAboutClick(v);
            }
        });
    }
    /***
     * @param v
     * 跳转到个人信息
     */
    private void onImgHeadClick(View v){
      Intent intent = new Intent();
        intent.setClass(getActivity(), PersonalInformationAty.class);
        startActivity(intent);
    }

    /**
     *
     * @param v
     *  * 跳转到子用户页面
     */
    private void onChildUserClick(View v){

        Intent intent = new Intent();
        intent.setClass(getActivity(), MyChildUserAty.class);
        startActivity(intent);
    }
    /**
     *
     * @param v
     *  * 跳转到新手帮助页面
     */
    private void onHelpClick(View v){
        Toast.makeText(getActivity(), "点击新手帮助", Toast.LENGTH_SHORT).show();
    }
    /**
     * @param v
     *  * 跳转到意见反馈
     */
    private void onSuggestClick(View v){
        Toast.makeText(getActivity(), "点击意见反馈", Toast.LENGTH_SHORT).show();
    }
    /**
     * @param v
     *  * 跳转关于页面
     */
    private void onAboutClick(View v){
        Toast.makeText(getActivity(), "点击关于", Toast.LENGTH_SHORT).show();
    }
}
