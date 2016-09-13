package com.richfinancial.qiyifinance.activity.manage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.activity.manage.bean.ManageBean;
import com.richfinancial.qiyifinance.base.BaseActivity;

/**
 * EditCustomerAty
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    EditCustomerAty
 * 类描述:    [编辑客户、编辑供应商]
 * 创建人:    baix
 * 创建时间:  2016/9/6
 * 修改人:    baix
 * 修改时间:  2016/9/6
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class EditCustomerAty extends BaseActivity implements View.OnClickListener{

    public static final int CUSTOMER = 0;

    public static final int SUPPLIER = 1;

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    private ManageBean manageBean = null;

    /**
     * 批量导入按钮
     * */
    private TextView txtv_delete_customer;

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
        setContentView(R.layout.activity_edit_customer);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        switch (getIntent().getIntExtra("type" , -1)){
            case CUSTOMER:
                txtv_title.setText(getString(R.string.str_txtv_customer_edit));
                break;

            case SUPPLIER:
                txtv_title.setText(getString(R.string.str_txtv_supplier_edit));
                break;
        }

        txtv_delete_customer = (TextView)findViewById(R.id.txtv_delete_customer);
        et_phone_num = (EditText)findViewById(R.id.et_phone_num);
        et_company_name = (EditText)findViewById(R.id.et_company_name);
        et_contact_person = (EditText) findViewById(R.id.et_contact_person);

        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);
        txtv_delete_customer.setOnClickListener(this);
    }

    @Override
    public void initData() {
        if(getIntent().hasExtra("customerInfo")){
            manageBean = (ManageBean)getIntent().getSerializableExtra("customerInfo");
        }

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

            case R.id.txtv_delete_customer:

                break;
        }
    }
}
