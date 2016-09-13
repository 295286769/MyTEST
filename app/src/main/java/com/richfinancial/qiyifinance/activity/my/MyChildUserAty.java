package com.richfinancial.qiyifinance.activity.my;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.adapter.ViewPagerAdapter;
import com.richfinancial.qiyifinance.base.BaseFragmentActivity;
import com.richfinancial.qiyifinance.fragments.DeleteUserFragment;
import com.richfinancial.qiyifinance.fragments.NormalUserFragment;
import com.richfinancial.qiyifinance.ui.TabScroll;

import java.util.ArrayList;
import java.util.List;

/**
 * MyPointAty
 * 项目名称:  QYSurveryPersonal
 * 类描述:    我的积分页面
 * 创建人:    wangfangfang
 * 创建时间:  2016-07-06
 * 修改人:
 * 修改时间:
 * 修改备注:
 * 版本:      v1.0
 */
public class MyChildUserAty extends BaseFragmentActivity implements ViewPager.OnPageChangeListener {

    private TextView m_txtvTitle;     //title
    private ImageButton m_imgbtnLeft;  //title左侧按钮
    private ImageButton m_imgbtnRight;  //title左侧按钮
    private TabScroll tsView; /* 自定义横向滑动tab */
    private ViewPager viewPager;//视图容器
    private TextView m_txtvTabNormalUser;//正常用户
    private TextView m_txtvTabDeleteUser;//删除用户
    private ViewPagerAdapter m_pagerAdapter;
    private Fragment[] m_frags;  //内容部分
    private TextView[] m_tabs;  //tabs不封
    private NormalUserFragment fragNormal;
    private DeleteUserFragment fragDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_manager);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_txtvTitle=(TextView)this.findViewById(R.id.txtv_title);
        m_txtvTitle.setText("用户管理");
        m_imgbtnLeft= (ImageButton) this.findViewById(R.id.imgbtn_left);
        m_imgbtnRight= (ImageButton) this.findViewById(R.id.imgbtn_right);
        viewPager=(ViewPager)this.findViewById(R.id.id_viewPager);
        tsView=(TabScroll)this.findViewById(R.id.id_tab_scroll);
        m_txtvTabNormalUser=(TextView)this.findViewById(R.id.txt_tab_normal_user);
        m_txtvTabDeleteUser=(TextView)this.findViewById(R.id.txt_tab_delete_user);
        tsView.setPaintColor(getResources().getColor(R.color.orange_theme));    //设置TabScroll颜色
        fragNormal=new NormalUserFragment();
        fragDelete=new DeleteUserFragment();
        initTab();
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
               addUser(v);
            }
        });

    }
    /***
     * 添加用户
     * @param v
     */
    public void addUser(View v){
        Intent intent = new Intent(MyChildUserAty.this, UserAddAty.class);
        startActivity(intent);
    }
    /**
     * 初始化tab
     */
    private void initTab() {

        m_tabs = new TextView[2];
        m_tabs[0] = m_txtvTabNormalUser;
        m_tabs[1] = m_txtvTabDeleteUser;
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> list = new ArrayList<Fragment>();
        m_frags = new Fragment[2];
        //正常用户
        m_tabs[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(0);
            }
        });
        m_frags[0]=fragNormal;
        list.add(fragNormal);
        //正常用户
        m_tabs[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(0);
            }
        });
        //删除用户
        m_tabs[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(1);
            }
        });
        m_frags[1] = fragDelete;
        list.add(fragDelete);
        tsView = (TabScroll) findViewById(R.id.id_tab_scroll);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        tsView.setItemLen(width / list.size());
        m_pagerAdapter = new ViewPagerAdapter(fm, list);
        viewPager.setAdapter(m_pagerAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(1);// 保证2页都不被销毁,最大2页，基本上1页
        onTabClick(0);
        tsView.scroll(0, 0);
    }

    /**
     * tab点击事件相应
     * @param index 索引
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void onTabClick(int index) {

        for (TextView txt : m_tabs) {
            txt.setTextColor(getResources().getColor(R.color.black));

        }
        m_tabs[index].setTextColor(getResources().getColor(R.color.black));
        viewPager.setCurrentItem(index);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        tsView.scroll(arg0, arg1);
    }

    @Override
    public void onPageSelected(int arg0) {
        onTabClick(arg0);
    }
}


