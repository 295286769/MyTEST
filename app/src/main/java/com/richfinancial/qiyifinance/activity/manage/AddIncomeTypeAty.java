package com.richfinancial.qiyifinance.activity.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;

/**
 * AddIncomeTypeAty
 * 项目名称:  QYManager
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    AddIncomeTypeAty
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/7
 * 修改人:    baix
 * 修改时间:  2016/9/7
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class AddIncomeTypeAty extends BaseActivity implements View.OnClickListener{
    public static final int INCOME_TYPE = 0;

    public static final int EXPEND_TYPE = 1;

    private ImageButton imgbtn_left;

    private ImageButton imgbtn_right;

    private TextView txtv_title;


    /**
     * 节点名称
     * */
    private EditText et_nickname;

    /**
     * 视为子节点开关
     * */
    private CheckBox ck_as_child_node;

    /**
     * 子节点layout
     * */
    private RelativeLayout rl_oper;

    /**
     * 子节点显示组件
     * */
    private TextView txtv_select_parent;

    /**
     * 线段
     * */
    private View oper_line;



    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_incometype);
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);

        et_nickname = (EditText)findViewById(R.id.et_nickname);
        ck_as_child_node = (CheckBox)findViewById(R.id.ck_as_child_node);
        oper_line = (View)findViewById(R.id.oper_line);
        rl_oper = (RelativeLayout) findViewById(R.id.rl_oper);
        txtv_select_parent = (TextView)findViewById(R.id.txtv_select_parent);
        rl_oper.setOnClickListener(this);
        switch (getIntent().getIntExtra("type" , -1)){
            case INCOME_TYPE:
                txtv_title.setText(getString(R.string.str_txtv_incometype_add));
                break;

            case EXPEND_TYPE:
                txtv_title.setText(getString(R.string.str_txtv_expendtype_add));
                break;
        }
        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);

        ck_as_child_node.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rl_oper.setVisibility(View.VISIBLE);
                    oper_line.setVisibility(View.VISIBLE);
                } else {
                    rl_oper.setVisibility(View.GONE);
                    oper_line.setVisibility(View.GONE);
                }
            }
        });
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

            case R.id.rl_oper:
                Intent intent = new Intent(AddIncomeTypeAty.this , SelectNodeActivity.class);
                startActivity(intent);
                break;

        }
    }
}
