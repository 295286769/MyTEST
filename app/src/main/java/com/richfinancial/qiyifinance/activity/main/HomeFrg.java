package com.richfinancial.qiyifinance.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;


import com.richfinancial.qiyifinance.activity.manage.ManageAty;
import com.richfinancial.qiyifinance.adapter.HomeAdapter;
import com.richfinancial.qiyifinance.base.BaseFragment;
import com.richfinancial.qiyifinance.bean.HomeBean;
import com.richfinancial.qiyiplus.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 类描述:    首页
 * 创建人:    android
 * 创建时间:  2016-03-09
 * 修改人:    android
 * 修改时间:  2016-03-09
 * 修改备注:  登录页
 * 版本:      v1.0
 */
public class HomeFrg extends BaseFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private List<HomeBean> mList;

    private LayoutInflater mInflater;

    private ImageButton mRightBtn , mLeftBtn;

    /**
     * 设置主页面
     */
    private MainAty mMainAty;
    public void setMain(MainAty mainAty) {
        mMainAty = mainAty;
    }

    @Override
    public void initData() {
        initLayout(R.layout.activity_home);
    }

    @Override
    public void initView() {
//
        mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View footerView = mInflater.inflate(R.layout.item_home_list_foot, null);


//        // 下拉刷新事件回调（）
//        mListView.setOnRefreshStartListener(new ZrcListView.OnStartListener() {
//            @Override
//            public void onStart() {
//            }
//        });

        mList = new ArrayList<>();
        //网络请求获得数据
        HomeBean hb2 = new HomeBean();
        hb2.setmResIcon(R.drawable.img_user);
        hb2.setTag("收支利润");
        hb2.setTitle("收入");
        HomeBean hb3 = new HomeBean();
        hb3.setmResIcon(R.drawable.img_user);
        hb3.setTag("");
        hb3.setTitle("支出");
        HomeBean hb4 = new HomeBean();
        hb4.setmResIcon(R.drawable.img_user);
        hb4.setTag("");
        hb4.setTitle("利润");

        HomeBean hb6 = new HomeBean();
        hb6.setmResIcon(R.drawable.img_user);
        hb6.setTag("应收应付");
        hb6.setTitle("应收款");
        HomeBean hb7 = new HomeBean();
        hb7.setmResIcon(R.drawable.img_user);
        hb7.setTag("");
        hb7.setTitle("应付款");

        HomeBean hb9 = new HomeBean();
        hb9.setmResIcon(R.drawable.img_user);
        hb9.setTag("股东权益等");
        hb9.setTitle("股本");
        HomeBean hb10 = new HomeBean();
        hb10.setmResIcon(R.drawable.img_user);
        hb10.setTag("");
        hb10.setTitle("分红");

//        mList.add(hb1);
        mList.add(hb2);
        mList.add(hb3);
        mList.add(hb4);
        mList.add(hb6);
        mList.add(hb7);
        mList.add(hb9);
        mList.add(hb10);
        mList.add(new HomeBean());

        mAdapter = new HomeAdapter(getActivity(), mList);
//        mListView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.activity_main_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.activity_main_recyclerview);
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
                        //模拟
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });

        //记一笔
        mRightBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_right);
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainAty.showPop();
            }
        });


        mLeftBtn = (ImageButton) rootView.findViewById(R.id.imgbtn_left);
        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , ManageAty.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void processingLogic() {

    }

}
