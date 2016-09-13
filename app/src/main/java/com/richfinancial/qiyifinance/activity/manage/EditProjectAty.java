package com.richfinancial.qiyifinance.activity.manage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;

/**
 * EditProjectAty
 * 项目名称:  QYManager
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    EditProjectAty
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/7
 * 修改人:    baix
 * 修改时间:  2016/9/7
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class EditProjectAty extends BaseActivity implements View.OnClickListener {

    public static final int PROJECT = 0;

    public static final int DEPARTMENT = 1;

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;
    /**
     * 名称
     * */
    private EditText et_project_name;

    /**
     * 备注
     * */
    private EditText et_project_remark;

    /**
     * 删除
     * */
    private TextView txtv_delete_project;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_project);
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);

        et_project_name = (EditText)findViewById(R.id.et_project_name);
        et_project_remark = (EditText) findViewById(R.id.et_project_remark);
        txtv_delete_project = (TextView)findViewById(R.id.txtv_delete_project);

        switch (getIntent().getIntExtra("type" , -1)){
            case PROJECT:
                txtv_title.setText(getString(R.string.str_txtv_project_edit));
                break;

            case DEPARTMENT:
                txtv_title.setText(getString(R.string.str_txtv_department_edit));
                break;
        }
        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);
        txtv_delete_project.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.imgbtn_left:
                finish();
                break;

            case R.id.imgbtn_right:
                Toast.makeText(this, "提交"+getIntent().getIntExtra("type" , -1), Toast.LENGTH_SHORT).show();
                break;

            case R.id.txtv_delete_project:

                break;

        }
    }

}
