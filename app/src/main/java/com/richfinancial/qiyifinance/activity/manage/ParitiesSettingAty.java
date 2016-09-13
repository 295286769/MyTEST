package com.richfinancial.qiyifinance.activity.manage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyifinance.activity.manage.adapter.ParitiesSettingAdapter;
import com.richfinancial.qiyifinance.activity.manage.bean.ParitiesSettingBean;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.ui.zrc.widget.search.SearchEditText;
import com.richfinancial.qiyiplus.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ParitiesSettingAty
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    ParitiesSettingAty
 * 类描述:    [汇率设置]
 * 创建人:    baix
 * 创建时间:  2016/9/5
 * 修改人:    baix
 * 修改时间:  2016/9/5
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class ParitiesSettingAty extends BaseActivity implements View.OnClickListener {

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    private SearchEditText searchEditText;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    private List<ParitiesSettingBean> m_List;

    private ParitiesSettingAdapter mAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_manage_lv);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        txtv_title.setText(getString(R.string.str_txtv_parities_setting));
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.activity_main_swipe_refresh_layout);
        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);

        searchEditText = (SearchEditText)findViewById(R.id.searchEditText);
        searchEditText.setVisibility(View.GONE);

        mRecyclerView = (RecyclerView)findViewById(R.id.custom_recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter.setOnItemClickListener(new ParitiesSettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ParitiesSettingAty.this , EditParitiesAty.class);
                intent.putExtra("customerInfo" , (Serializable) m_List.get(position));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange_theme, R.color.green_theme, R.color.blue_theme);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
    }

    @Override
    public void initData() {
        m_List = new ArrayList<>();
        for(int i = 0; i < 4;i++){
            ParitiesSettingBean bean = new ParitiesSettingBean();
            bean.setId(""+i);
            bean.setName("项目"+ i);
            bean.setMoney("33"+i);
            bean.setTime("2012-09-0"+i);
            m_List.add(bean);
        }
        mAdapter = new ParitiesSettingAdapter(this ,m_List);
    }

    @Override
    public void processingLogic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_left:
                finish();
                break;

            case R.id.imgbtn_right:
                Intent intent = new Intent(ParitiesSettingAty.this , AddParitiesAty.class);
                startActivity(intent);
                break;
        }
    }

}
