package com.richfinancial.qiyifinance.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.activity.main.MainAty;
import com.richfinancial.qiyifinance.activity.my.UserScanAty;
import com.richfinancial.qiyifinance.adapter.DeleteUserAdapter;
import com.richfinancial.qiyifinance.adapter.HomeAdapter;
import com.richfinancial.qiyifinance.adapter.NormalUserAdapter;
import com.richfinancial.qiyifinance.base.BaseFragment;
import com.richfinancial.qiyifinance.bean.HomeBean;
import com.richfinancial.qiyifinance.bean.NormalUserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 2016/9/7.
 */
public class DeleteUserFragment extends BaseFragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private DeleteUserAdapter mAdapter;
    private List<NormalUserBean> mList;


    @Override
    public void initData() {
        initLayout(R.layout.normal_user_fragment);
    }

    @Override
    public void initView() {

        mList = new ArrayList<>();
        //网络请求获得数据
        NormalUserBean hb2 = new NormalUserBean();
        hb2.setUserName("张三");
        hb2.setUserMail("12345@qq.com");
        hb2.setUserPhone("123412323");
        NormalUserBean hb3 = new NormalUserBean();
        hb3.setUserName("李四");
        hb3.setUserMail("22345@qq.com");
        hb3.setUserPhone("123412323");
        NormalUserBean hb4 = new NormalUserBean();
        hb4.setUserName("王五");
        hb4.setUserMail("22345@qq.com");
        hb4.setUserPhone("22223412323");
        mList.add(hb2);
        mList.add(hb3);
        mList.add(hb4);

        mAdapter = new DeleteUserAdapter(getActivity(), mList);
//        mListView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.activity_normal_user_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.activity_normal_user_recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange_theme, R.color.green_theme, R.color.blue_theme);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mRecyclerView.setAdapter(mAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
        mAdapter.setOnItemClickListener(new DeleteUserAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view ,NormalUserBean data){
                Intent intent = new Intent(getActivity(), UserScanAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("normalUser", data);
                bundle.putString("fragmentName","DeleteUserFragment");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void processingLogic() {

    }
}
