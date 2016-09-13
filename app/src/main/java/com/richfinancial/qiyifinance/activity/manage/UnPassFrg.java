package com.richfinancial.qiyifinance.activity.manage;

import android.view.View;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UnPassFrg
 * 项目名称:  QYManager
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    UnPassFrg
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/6
 * 修改人:    baix
 * 修改时间:  2016/9/6
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class UnPassFrg extends BaseFragment implements View.OnClickListener{
    /**
     *  减月份
     */
    private TextView txtv_subtract_date;

    /**
     *  显示月份
     */
    private TextView txtv_show_date;

    /**
     *  增月份
     */
    private TextView txtv_add_date;

    private Date mDate;

    private SimpleDateFormat sdf;

    @Override
    public void initView() {
        txtv_subtract_date = (TextView)rootView.findViewById(R.id.txtv_subtract_date);
        txtv_show_date = (TextView)rootView.findViewById(R.id.txtv_show_date);
        txtv_add_date = (TextView)rootView.findViewById(R.id.txtv_add_date);
        txtv_subtract_date.setOnClickListener(this);
        txtv_add_date.setOnClickListener(this);
        initTime();
    }

    @Override
    public void initData() {
        initLayout(R.layout.fragment_unpass);
    }

    @Override
    public void processingLogic() {

    }

    private void initTime(){
        sdf = new SimpleDateFormat("yyyy-MM");
        mDate = new Date();
        txtv_show_date.setText(sdf.format(mDate));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtv_subtract_date:
                mDate.setMonth(mDate.getMonth() - 1);
                txtv_show_date.setText(sdf.format(mDate));
                break;
            case R.id.txtv_add_date:
                mDate.setMonth(mDate.getMonth() + 1);
                txtv_show_date.setText(sdf.format(mDate));
                break;
        }
    }
}
