package com.richfinancial.qiyifinance.activity.manage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.activity.manage.adapter.ManageAdapter;
import com.richfinancial.qiyifinance.activity.manage.bean.ManageBean;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.ui.zrc.widget.search.SearchEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectManageAty
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    ProjectManageAty
 * 类描述:    [项目管理]
 * 创建人:    baix
 * 创建时间:  2016/9/5
 * 修改人:    baix
 * 修改时间:  2016/9/5
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class ProjectManageAty extends BaseActivity implements View.OnClickListener,EditText.OnEditorActionListener {

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    private SearchEditText searchEditText;


    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    private ManageAdapter adapter;

    private List<ManageBean> m_list;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_manage_lv);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        txtv_title.setText(getString(R.string.str_txtv_project));
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.activity_main_swipe_refresh_layout);
        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);

        searchEditText = (SearchEditText)findViewById(R.id.searchEditText);
        searchEditText.setOnEditorActionListener(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.custom_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.activity_main_swipe_refresh_layout);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(new ManageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ProjectManageAty.this , EditProjectAty.class);
                intent.putExtra("type" , AddProjectAty.PROJECT );
                intent.putExtra("customerInfo" , (Serializable) m_list.get(position));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
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
        m_list = new ArrayList<>();
        for(int i = 0; i < 4;i++){
            ManageBean bean = new ManageBean();
            bean.setId(""+i);
            bean.setName("项目"+ i);
            m_list.add(bean);
        }
        adapter = new ManageAdapter(this ,m_list);
    }

    @Override
    public void processingLogic() {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            // 先隐藏键盘
            ((InputMethodManager) searchEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(this
                                    .getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

            Toast.makeText(this, v.getText().toString(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_left:
                finish();
                break;

            case R.id.imgbtn_right:
                Intent intent = new Intent(ProjectManageAty.this , AddProjectAty.class);
                intent.putExtra("type" , EditProjectAty.PROJECT );
                startActivity(intent);
                break;
        }
    }
}
