package com.richfinancial.qiyifinance.activity.manage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyiplus.R;

/**
 * EditParitiesAty
 * 项目名称:  QYManager
 * 包:        com.richfinancial.qiyifinance.activity.manage
 * 类名称:    EditParitiesAty
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/8
 * 修改人:    baix
 * 修改时间:  2016/9/8
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class EditParitiesAty extends BaseActivity implements View.OnClickListener {

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    private TextView txtv_foreign_currency;

    private EditText et_parities;

    private TextView txtv_time;

    private TextView txtv_delete_parities;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_parities);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);


        txtv_title.setText(getString(R.string.str_txtv_parities_edit));

        txtv_foreign_currency = (TextView)findViewById(R.id.txtv_foreign_currency);
        et_parities = (EditText)findViewById(R.id.et_parities);
        txtv_time = (TextView)findViewById(R.id.txtv_time);
        txtv_delete_parities = (TextView)findViewById(R.id.txtv_delete_parities);
        txtv_delete_parities.setOnClickListener(this);
        txtv_time.setOnClickListener(this);
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

            case R.id.imgbtn_right:
                Toast.makeText(this, "提交", Toast.LENGTH_SHORT).show();
                break;

            case R.id.txtv_time:

                break;
        }
    }

}
