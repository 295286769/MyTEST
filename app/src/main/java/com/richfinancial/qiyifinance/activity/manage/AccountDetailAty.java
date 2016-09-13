package com.richfinancial.qiyifinance.activity.manage;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyifinance.activity.manage.adapter.HomeViewPagerAdapter;
import com.richfinancial.qiyifinance.base.BaseFragmentActivity;
import com.richfinancial.qiyifinance.ui.TabScroll;
import com.richfinancial.qiyifinance.utils.ScreenUtils;
import com.richfinancial.qiyiplus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountDetailAty
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    AccountDetailAty
 * 类描述:    [账户明细]
 * 创建人:    baix
 * 创建时间:  2016/9/5
 * 修改人:    baix
 * 修改时间:  2016/9/5
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class AccountDetailAty extends BaseFragmentActivity implements View.OnClickListener , ViewPager.OnPageChangeListener{

    private ImageButton imgbtn_left;

    private TextView txtv_title;


    /**
     * 待审核
     */
    private TextView txtv_wait_audit;
    /**
     * 已通过
     */
    private TextView txtv_already_pass;
    /**
     * 未通过
     */
    private TextView txtv_unpass;

    /**
     * TabScroll
     */
    private TabScroll tab_scroll;

    private ViewPager mViewPager;

    private TextView[] m_tabs;
    private HomeViewPagerAdapter m_pagerAdapter;
    private Fragment[] m_frags;  //内容部分

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_acc_detail);
    }

    private void initFragment(){
        m_tabs = new TextView[3];
        m_tabs[0] = txtv_wait_audit;
        m_tabs[1] = txtv_already_pass;
        m_tabs[2] = txtv_unpass;
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> list = new ArrayList<Fragment>();
        m_frags = new Fragment[3];
        //积分明细
        m_tabs[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(0);
            }
        });
        Fragment auditFragment = new WaitAuditFrg();
        m_frags[0]=auditFragment;
        list.add(auditFragment);

        Fragment alreadyPassFrg = new AlreadyPassFrg();
        m_frags[1] = alreadyPassFrg;
        list.add(alreadyPassFrg);
        //兑换记录
        m_tabs[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(1);
            }
        });


        Fragment unPassFrg = new UnPassFrg();
        m_frags[2] = unPassFrg;
        list.add(unPassFrg);
        //兑换记录
        m_tabs[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(2);
            }
        });


        tab_scroll.setItemLen(ScreenUtils.getScreenWidth(this) / list.size());
        m_pagerAdapter = new HomeViewPagerAdapter(fm, list);
        mViewPager.setAdapter(m_pagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(2);// 保证2页都不被销毁,最大2页，基本上1页
        onTabClick(0);
        tab_scroll.scroll( 0 , 0 );
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        txtv_title.setText(getString(R.string.str_txtv_account_detail));


        txtv_wait_audit = (TextView)findViewById(R.id.txtv_wait_audit);
        txtv_already_pass = (TextView)findViewById(R.id.txtv_already_pass);
        txtv_unpass = (TextView)findViewById(R.id.txtv_unpass);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tab_scroll = (TabScroll) findViewById(R.id.tab_scroll);

        imgbtn_left.setOnClickListener(this);

        initFragment();
    }

    @Override
    public void initData() {

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

            default:
                break;
        }
    }

    /**
     * tab点击事件相应
     * @param index 索引
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void onTabClick(int index) {
        for (TextView txt : m_tabs) {
            txt.setTextColor(getResources().getColor(R.color.wealth_font));
        }
        m_tabs[index].setTextColor(getResources().getColor(R.color.blue_theme));
        mViewPager.setCurrentItem(index);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        tab_scroll.scroll(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override

    public void onPageScrollStateChanged(int state) {

    }
}
