package com.richfinancial.qiyifinance.activity.manage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;

/**
 * SelectNodeActivity
 * 项目名称:  QYManager
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    SelectNodeActivity
 * 类描述:    [选择父类别activity]
 * 创建人:    baix
 * 创建时间:  2016/9/7
 * 修改人:    baix
 * 修改时间:  2016/9/7
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class SelectNodeActivity extends BaseActivity implements View.OnClickListener{

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    @Override
    protected void initContentView(Bundle savedInanceState) {
        setContentView(R.layout.activity_select_node);
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        imgbtn_left.setOnClickListener(this);

        txtv_title.setText("父类别");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processingLogic() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
