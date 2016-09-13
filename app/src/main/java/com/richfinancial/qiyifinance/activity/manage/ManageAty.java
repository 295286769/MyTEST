package com.richfinancial.qiyifinance.activity.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyiplus.R;

/**
 * ManageAty
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    ManageAty
 * 类描述:    [管理主界面activity]
 * 创建人:    baix
 * 创建时间:  2016/9/5
 * 修改人:    baix
 * 修改时间:  2016/9/5
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class ManageAty extends BaseActivity implements View.OnClickListener{

    private ImageButton imgbtn_left;

    private TextView txtv_title;

    private RelativeLayout rl_audit_accounts;
    private RelativeLayout rl_customer_manage;
    private RelativeLayout rl_supplier_manage;
    private RelativeLayout rl_project_manage;
    private RelativeLayout rl_department_manage;
    private RelativeLayout rl_employee_manage;
    private RelativeLayout rl_income_type;
    private RelativeLayout rl_expend_type;
    private RelativeLayout rl_parities_setting;

    @Override
    protected void initContentView(Bundle savedInanceState) {
        setContentView(R.layout.activity_manage);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        rl_audit_accounts = (RelativeLayout)findViewById(R.id.rl_audit_accounts);
        rl_customer_manage = (RelativeLayout)findViewById(R.id.rl_customer_manage);
        rl_supplier_manage = (RelativeLayout)findViewById(R.id.rl_supplier_manage);
        rl_project_manage = (RelativeLayout)findViewById(R.id.rl_project_manage);
        rl_department_manage = (RelativeLayout)findViewById(R.id.rl_department_manage);
        rl_employee_manage = (RelativeLayout)findViewById(R.id.rl_employee_manage);
        rl_income_type = (RelativeLayout)findViewById(R.id.rl_income_type);
        rl_expend_type = (RelativeLayout)findViewById(R.id.rl_expend_type);
        rl_parities_setting = (RelativeLayout)findViewById(R.id.rl_parities_setting);


        txtv_title.setText(getString(R.string.str_txtv_manage_title));

        rl_audit_accounts.setOnClickListener(this);
        rl_customer_manage.setOnClickListener(this);
        rl_supplier_manage.setOnClickListener(this);
        rl_project_manage.setOnClickListener(this);
        rl_department_manage.setOnClickListener(this);
        rl_employee_manage.setOnClickListener(this);
        rl_income_type.setOnClickListener(this);
        rl_expend_type.setOnClickListener(this);
        rl_parities_setting.setOnClickListener(this);
        imgbtn_left.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processingLogic() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.rl_audit_accounts:
                intent.setClass(ManageAty.this , AccountDetailAty.class);
                startActivity(intent);
                break;
            case R.id.rl_customer_manage:
                intent.setClass(ManageAty.this , CustomerManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_supplier_manage:
                intent.setClass(ManageAty.this , SupplierManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_project_manage:
                intent.setClass(ManageAty.this , ProjectManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_department_manage:
                intent.setClass(ManageAty.this , DepartmentManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_employee_manage:
                intent.setClass(ManageAty.this , EmployeeManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_income_type:
                intent.setClass(ManageAty.this , IncomeTypeAty.class);
                startActivity(intent);
                break;
            case R.id.rl_expend_type:
                intent.setClass(ManageAty.this , ExpendTypeAty.class);
                startActivity(intent);
                break;
            case R.id.rl_parities_setting:
                intent.setClass(ManageAty.this , ParitiesSettingAty.class);
                startActivity(intent);
                break;

            case R.id.imgbtn_left:
                finish();
                break;

            default:
                break;
        }
    }
}
