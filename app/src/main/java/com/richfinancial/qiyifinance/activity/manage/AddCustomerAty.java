package com.richfinancial.qiyifinance.activity.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyifinance.activity.CustomerBulkImportAty;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyiplus.R;

import java.util.ArrayList;

/**
 * AddCustomerAty
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    AddCustomerAty
 * 类描述:    [增加客户、增加供应商]
 * 创建人:    baix
 * 创建时间:  2016/9/6
 * 修改人:    baix
 * 修改时间:  2016/9/6
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class AddCustomerAty extends BaseActivity implements View.OnClickListener{

    public static final int CUSTOMER = 0;

    public static final int SUPPLIER = 1;

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    private ArrayList<String> phoneNums;


    /**
     * 批量导入按钮
     * */
    private TextView txtv_bulk_import;

    /**
     * 联系人
     * */
    private EditText et_contact_person;

    /**
     * 公司名称
     * */
    private EditText et_company_name;

    /**
     * 电话号码
     * */
    private EditText et_phone_num;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_customer);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);

        txtv_bulk_import = (TextView)findViewById(R.id.txtv_bulk_import);
        et_phone_num = (EditText)findViewById(R.id.et_phone_num);
        et_company_name = (EditText)findViewById(R.id.et_company_name);
        et_contact_person = (EditText) findViewById(R.id.et_contact_person);

        switch (getIntent().getIntExtra("type" , -1)){
            case CUSTOMER:
                txtv_title.setText(getString(R.string.str_txtv_customer_add));
                break;

            case SUPPLIER:
                txtv_title.setText(getString(R.string.str_txtv_supplier_add));
                break;
        }
        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);
        txtv_bulk_import.setOnClickListener(this);
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
                Toast.makeText(this, "提交"+getIntent().getIntExtra("type" , -1), Toast.LENGTH_SHORT).show();
                break;

            case R.id.txtv_bulk_import:
                Intent intent = new Intent(AddCustomerAty.this , CustomerBulkImportAty.class);
                if(getIntent().hasExtra(CustomerBulkImportAty.TEL_PHONES)){
                    intent.putStringArrayListExtra(CustomerBulkImportAty.TEL_PHONES , getIntent().getStringArrayListExtra(CustomerBulkImportAty.TEL_PHONES) );
                }
                startActivity(intent);
                finish();
                break;

        }
    }
}
