package com.richfinancial.qiyifinance.activity.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyifinance.adapter.ViewPagerAdapter;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.base.BaseFragmentActivity;
import com.richfinancial.qiyifinance.fragments.PayableFragment;
import com.richfinancial.qiyifinance.ui.TabScroll;
import com.richfinancial.qiyiplus.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * PayableAty
 * 项目名称:  XZBBusinessHelper
 * 包:        com.richfinancial.qiyifinance.activity.home
 * 类名称:    PayableAty
 * 类描述:    应付款
 * 创建人:    luqb
 * 创建时间:  2016-09-08
 * 修改人:    luqb
 * 修改时间:  [${date} ${time}]
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class PayableAty extends BaseFragmentActivity implements ViewPager.OnPageChangeListener{

    private TextView mTxtvTitle;     //title
    private ImageButton mImgbtnLeft;  //title左侧按钮
    private ImageButton mImgbtnRight;  //title左侧按钮
    private TextView mTxtvTabDue;   // 应收Text
    private TextView mTxtvTabPaid;      // 已收Text
    private TextView mTxtvTabSummary;       // 汇总Text
    private TabScroll mIdTabScroll;         // 自定义横向滑动tab
    private ViewPager mIdViewPager;         // ViewPager View

    private PayableFragment mUncollectedFragment;   // 应付Fragment
    private PayableFragment mReceivedFragment;      // 已付Fragment
    private PayableFragment mSummaryFragment;       // 汇总Frangent
    private TextView[] mTabs;  //tabs不封;
    private Fragment[] mFrags;  //内容部分
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_payable);
    }

    @Override
    public void initView() {
        mTxtvTitle = (TextView) findViewById(R.id.txtv_title);
        mImgbtnLeft = (ImageButton) findViewById(R.id.imgbtn_left);
        mImgbtnRight = (ImageButton) findViewById(R.id.imgbtn_right);
        mTxtvTabDue = (TextView) findViewById(R.id.txtv_tab_due);
        mTxtvTabPaid = (TextView) findViewById(R.id.txtv_tab_paid);
        mTxtvTabSummary = (TextView) findViewById(R.id.txtv_tab_summary);
        mIdTabScroll = (TabScroll) findViewById(R.id.id_tab_scroll);
        mIdViewPager = (ViewPager) findViewById(R.id.id_viewPager);
        mIdTabScroll.setPaintColor(getResources().getColor(R.color.orange_theme));    //设置TabScroll颜色

        mTxtvTitle.setText("应付款");
        mUncollectedFragment = new PayableFragment();
        mReceivedFragment = new PayableFragment();
        mSummaryFragment = new PayableFragment();
        initTab();

        /** 返回按钮 */
        mImgbtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /** 搜索按钮 */
        mImgbtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void processingLogic() {

    }

    /**
     * 初始化tab
     */
    private void initTab() {

        mTabs = new TextView[3];
        mTabs[0] = mTxtvTabDue;
        mTabs[1] = mTxtvTabPaid;
        mTabs[2] = mTxtvTabSummary;
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> list = new ArrayList<Fragment>();
        mFrags = new Fragment[3];
        // 应付
        mTabs[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(0);
            }
        });
        mFrags[0] = mUncollectedFragment;
        list.add(mUncollectedFragment);
        //正常用户
        mTabs[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(0);
            }
        });
        //删除用户
        mTabs[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(1);
            }
        });
        mFrags[1] = mReceivedFragment;
        list.add(mReceivedFragment);
        // 汇总
        mTabs[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(2);
            }
        });
        mFrags[2] = mSummaryFragment;
        list.add(mSummaryFragment);
        mIdTabScroll = (TabScroll) findViewById(R.id.id_tab_scroll);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        mIdTabScroll.setItemLen(width / list.size());
        mPagerAdapter = new ViewPagerAdapter(fm, list);
        mIdViewPager.setAdapter(mPagerAdapter);
        mIdViewPager.setOnPageChangeListener(this);
        mIdViewPager.setOffscreenPageLimit(1);// 保证2页都不被销毁,最大2页，基本上1页
        onTabClick(0);
        mIdTabScroll.scroll(0, 0);
    }

    /**
     * tab点击事件相应
     * @param index 索引
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void onTabClick(int index) {
        for (TextView txt : mTabs) {
            txt.setTextColor(getResources().getColor(R.color.black));
        }
        mTabs[index].setTextColor(getResources().getColor(R.color.black));
        mIdViewPager.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mIdTabScroll.scroll(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        onTabClick(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
