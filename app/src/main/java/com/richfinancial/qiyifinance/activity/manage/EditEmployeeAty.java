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
 * EditEmployeeAty
 * 项目名称:  QYManager
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    EditEmployeeAty
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/7
 * 修改人:    baix
 * 修改时间:  2016/9/7
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class EditEmployeeAty extends BaseActivity implements View.OnClickListener{

    public static final int CUSTOMER = 0;

    public static final int SUPPLIER = 1;

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    private ManageBean manageBean = null;

    /**
     * 批量导入按钮
     * */
    private TextView txtv_delete_employee;


    /**
     * 手机号
     * */
    private EditText et_phone_num;

    /**
     *邮箱
     * */
    private EditText et_email;

    /**
     * 备注
     * */
    private EditText et_remark;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_employee);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        txtv_title.setText(getString(R.string.str_txtv_employee_edit));

        txtv_delete_employee = (TextView)findViewById(R.id.txtv_delete_employee);
        et_phone_num = (EditText)findViewById(R.id.et_phone_num);
        et_email = (EditText)findViewById(R.id.et_email);
        et_remark = (EditText) findViewById(R.id.et_remark);

        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);
        txtv_delete_employee.setOnClickListener(this);
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
                Toast.makeText(this, "提交" , Toast.LENGTH_SHORT).show();
                break;

            case R.id.txtv_delete_employee:
                Toast.makeText(this, "删除" , Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
