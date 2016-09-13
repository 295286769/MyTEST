package com.richfinancial.qiyifinance.activity.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.adapter.RecordAdapter;
import com.richfinancial.qiyifinance.base.BaseFragmentActivity;
import com.richfinancial.qiyifinance.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 类描述:    主页
 * 创建人:    android
 * 创建时间:  2016-03-09
 * 修改人:    android
 * 修改时间:  2016-03-09
 * 修改备注:  登录页
 * 版本:      v1.0
 */
public class MainAty extends BaseFragmentActivity {

    private PopupWindow m_popWindow; // 弹出
    private View m_vShadow;   //阴影部分
    private LayoutInflater mInflater;
    private RelativeLayout mLayContent;

    private FragmentManager m_fragmentManager; // fragment管理器
    private boolean m_firstBack = true;//判断是不是第一次点击back

    private ListView mListView;
    private List<RecordBean> mList;

    private static final int[] FRAGMENTS_RESID = {
            R.id.rbtn_home, // 首页
            R.id.rbtn_account, // 企业数据
//            R.id.rbtn_stock, // 企业数据
            R.id.rbtn_tax, // 企业数据
            R.id.rbtn_setting, // 设置
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_fragmentManager = this.getSupportFragmentManager();
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.rg_layout);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeFragment(checkedId);
            }
        });
        changeFragment(R.id.rbtn_home);

        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initPopupWindow();
        mLayContent = (RelativeLayout) findViewById(R.id.lay_content);
        m_vShadow = findViewById(R.id.v_shadow);
    }

    /**
     * 初始化popupwindow
     */
    private void initPopupWindow() {
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupView = mInflater.inflate(R.layout.layout_record, null, false);
        // 创建PopupWindow实例
        m_popWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupView.findViewById(R.id.v_space).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_popWindow.dismiss();
            }
        });
        // 设置动画效果
        m_popWindow.setAnimationStyle(R.style.popup_anim_style);
        m_popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                m_vShadow.startAnimation(AnimationUtils.loadAnimation(MainAty.this, R.anim.popup_fadeout));
                m_vShadow.setVisibility(View.GONE);
            }
        });


        mListView = (ListView) popupView.findViewById(R.id.lv_record_list);
        mList = new ArrayList<>();
        RecordBean rb1 = new RecordBean();
        rb1.setTitle("转账、取现、存现");
        RecordBean rb2 = new RecordBean();
        rb2.setTitle("收回欠款、预收款");
        RecordBean rb3 = new RecordBean();
        rb3.setTitle("还回欠款、预付款");
        RecordBean rb4 = new RecordBean();
        rb4.setTitle("应收的收入（收入挂账）");
        RecordBean rb5 = new RecordBean();
        rb5.setTitle("赊欠的支出（支出挂账）");
        mList.add(rb1);
        mList.add(rb2);
        mList.add(rb3);
        mList.add(rb4);
        mList.add(rb5);
        mListView.setAdapter(new RecordAdapter(MainAty.this, mList));
    }

    @Override
    public void processingLogic() {
    }

    public void showPop() {
        if (null != m_popWindow) {
            if (m_popWindow.isShowing()) {
                m_popWindow.dismiss();
            } else {
                m_vShadow.startAnimation(AnimationUtils.loadAnimation(this, R.anim.popup_fadein));
                m_vShadow.setVisibility(View.VISIBLE);
                m_popWindow.showAtLocation(mLayContent, Gravity.RIGHT, 0, 0);
            }
        }
    }


    /**
     * Fragment 选择
     *
     * @param resId
     */
    private void changeFragment(int resId) {
        if (null == m_fragmentManager) {
            return;
        }
        /** Fragment容器 */
        FragmentTransaction transaction = m_fragmentManager.beginTransaction();
        /** 隐藏所有页面 */
        for (int i = 0; i < FRAGMENTS_RESID.length; i++) {
            if (FRAGMENTS_RESID[i] == resId) {
                continue;
            }
            Fragment fragment = m_fragmentManager.findFragmentByTag(String.valueOf(FRAGMENTS_RESID[i]));
            if (null != fragment && fragment.isVisible()) {
                transaction.hide(fragment);
            }
        }
        final String fgTagString = String.valueOf(resId); // 设置Fragment Tag
        /** Fragment 对象 */
        Fragment fragment = m_fragmentManager.findFragmentByTag(fgTagString);
        if (null == fragment) {
            if (resId == R.id.rbtn_home) {
                fragment = new HomeFrg();
                ((HomeFrg) fragment).setMain(this);
            } else if (resId == R.id.rbtn_account) {
                fragment = new AccountFrg();
            } else if (resId == 0) {//TODO 库存
                fragment = new StockFrg();
            } else if (resId == R.id.rbtn_tax) {
                fragment = new TaxFrg();
            } else if (resId == R.id.rbtn_setting) {
                fragment = new SettingFra();
            }
            transaction.add(R.id.fl_layout, fragment, fgTagString); // 把指定Fragment放入容器中
            transaction.commit(); // 显示容器中的Fragment View
        } else {
            if (!fragment.isVisible()) {
                transaction.show(fragment);
                transaction.commit();
            }
        }
    }
}
