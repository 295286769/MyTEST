package com.richfinancial.qiyifinance.activity.manage;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyiplus.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * AddParitiesAty
 * 项目名称:  QYManager
 * 包:        com.richfinancial.qiyifinance.activity.manage
 * 类名称:    AddParitiesAty
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/8
 * 修改人:    baix
 * 修改时间:  2016/9/8
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class AddParitiesAty extends BaseActivity implements View.OnClickListener {

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    private TextView txtv_foreign_currency;

    private EditText et_parities;

    private TextView txtv_time;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_parities);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);

        txtv_title.setText(getString(R.string.str_txtv_parities_add));

        txtv_foreign_currency = (TextView)findViewById(R.id.txtv_foreign_currency);
        et_parities = (EditText)findViewById(R.id.et_parities);
        txtv_time = (TextView)findViewById(R.id.txtv_time);
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
                Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);//获取一个日历对象；
                int v_year = dateAndTime.get(Calendar.YEAR);
                int v_month = dateAndTime.get(Calendar.MONTH);
                int v_day = dateAndTime.get(Calendar.DAY_OF_MONTH);
                // dataPicker初始化
                // 初始化DatePickerDialog
                DatePickerDialog picker = new DatePickerDialog(this, R.style.ActionSheetDialogPickerStyle  , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtv_time.setText(Integer.valueOf(year) + "年-" + Integer.valueOf(monthOfYear + 1) + "月-" + Integer.valueOf(dayOfMonth)+"日");
                    }
                }, v_year, v_month, v_day);
                picker.show();
                break;
        }
    }
}
