package com.richfinancial.qiyifinance.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;

/**
 * Created by LENOVO on 2016/9/6.
 */
public class ChangeComapanyNameAty extends BaseActivity {

    private ImageButton  m_imgbtnRight;//标题栏左侧按钮
    private  ImageButton  m_imgbtnLeft;
    private TextView m_txtvTitle;//标题栏中间标题
    private EditText m_comapanyNameEdit;//姓名修改框
    private String str_name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_change_company_name);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_imgbtnLeft= (ImageButton) this.findViewById(R.id.imgbtn_left);
        m_imgbtnRight= (ImageButton) this.findViewById(R.id.imgbtn_right);
        m_comapanyNameEdit=(EditText) this.findViewById(R.id.edit);
        m_txtvTitle= (TextView) this.findViewById(R.id.txtv_title);
        m_txtvTitle.setText("公司名称修改");
    }

    @Override
    public void processingLogic() {
        m_imgbtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        m_imgbtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                str_name=m_comapanyNameEdit.getText().toString();
                intent.putExtra("companyName", str_name);
                ChangeComapanyNameAty.this.setResult(PersonalInformationAty.UPDATA_COMPANY_NAME_BACK_CODE, intent);
                finish();
            }
        });
    }
}
