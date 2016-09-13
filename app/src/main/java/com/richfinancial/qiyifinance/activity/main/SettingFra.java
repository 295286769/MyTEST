package com.richfinancial.qiyifinance.activity.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.richfinancial.qiyifinance.activity.manage.AccountDetailAty;
import com.richfinancial.qiyifinance.activity.manage.CustomerManageAty;
import com.richfinancial.qiyifinance.activity.manage.DepartmentManageAty;
import com.richfinancial.qiyifinance.activity.manage.EmployeeManageAty;
import com.richfinancial.qiyifinance.activity.manage.ExpendTypeAty;
import com.richfinancial.qiyifinance.activity.manage.IncomeTypeAty;
import com.richfinancial.qiyifinance.activity.manage.ParitiesSettingAty;
import com.richfinancial.qiyifinance.activity.manage.ProjectManageAty;
import com.richfinancial.qiyifinance.activity.manage.SupplierManageAty;
import com.richfinancial.qiyifinance.base.BaseFragment;
import com.richfinancial.qiyiplus.R;

/**
 * SettingFra
 * 项目名称:  QYManager
 * 包:        com.richfinancial.qiyifinance.activity.main
 * 类名称:    SettingFra
 * 类描述:    [管理改为设置]
 * 创建人:    baix
 * 创建时间:  2016/9/8
 * 修改人:    baix
 * 修改时间:  2016/9/8
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class SettingFra extends BaseFragment implements View.OnClickListener{

    private ImageButton imgbtn_left;
    private ImageButton imgbtn_right;
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
    public void initView() {
        imgbtn_left = (ImageButton)getView().findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)getView().findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)getView().findViewById(R.id.txtv_title);
        rl_audit_accounts = (RelativeLayout)getView().findViewById(R.id.rl_audit_accounts);
        rl_customer_manage = (RelativeLayout)getView().findViewById(R.id.rl_customer_manage);
        rl_supplier_manage = (RelativeLayout)getView().findViewById(R.id.rl_supplier_manage);
        rl_project_manage = (RelativeLayout)getView().findViewById(R.id.rl_project_manage);
        rl_department_manage = (RelativeLayout)getView().findViewById(R.id.rl_department_manage);
        rl_employee_manage = (RelativeLayout)getView().findViewById(R.id.rl_employee_manage);
        rl_income_type = (RelativeLayout)getView().findViewById(R.id.rl_income_type);
        rl_expend_type = (RelativeLayout)getView().findViewById(R.id.rl_expend_type);
        rl_parities_setting = (RelativeLayout)getView().findViewById(R.id.rl_parities_setting);
        imgbtn_left.setVisibility(View.INVISIBLE);
        imgbtn_right.setVisibility(View.INVISIBLE);

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
    }

    @Override
    public void initData() {
        initLayout(R.layout.activity_manage);
    }

    @Override
    public void processingLogic() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.rl_audit_accounts:
                intent.setClass(getActivity() , AccountDetailAty.class);
                startActivity(intent);
                break;
            case R.id.rl_customer_manage:
                intent.setClass(getActivity() , CustomerManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_supplier_manage:
                intent.setClass(getActivity() , SupplierManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_project_manage:
                intent.setClass(getActivity() , ProjectManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_department_manage:
                intent.setClass(getActivity() , DepartmentManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_employee_manage:
                intent.setClass(getActivity() , EmployeeManageAty.class);
                startActivity(intent);
                break;
            case R.id.rl_income_type:
                intent.setClass(getActivity() , IncomeTypeAty.class);
                startActivity(intent);
                break;
            case R.id.rl_expend_type:
                intent.setClass(getActivity() , ExpendTypeAty.class);
                startActivity(intent);
                break;
            case R.id.rl_parities_setting:
                intent.setClass(getActivity() , ParitiesSettingAty.class);
                startActivity(intent);
                break;

//            case R.id.imgbtn_left:
//                finish();
//                break;

            default:
                break;
        }
    }
}
