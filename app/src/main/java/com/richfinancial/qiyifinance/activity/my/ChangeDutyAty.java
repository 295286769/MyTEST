package com.richfinancial.qiyifinance.activity.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;

/**
 * Created by LENOVO on 2016/9/6.
 */
public class ChangeDutyAty extends BaseActivity {

    private ImageButton  m_imgbtnRight;//标题栏左侧按钮
    private  ImageButton  m_imgbtnLeft;
    private TextView m_txtvTitle;//标题栏中间标题
    private RelativeLayout m_dutyLayout;//职务layout
    private TextView m_txtvDutyName;
    private String str_dutyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_change_duty);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_imgbtnLeft= (ImageButton) this.findViewById(R.id.imgbtn_left);
        m_imgbtnRight= (ImageButton) this.findViewById(R.id.imgbtn_right);
        m_txtvTitle= (TextView) this.findViewById(R.id.txtv_title);
        m_dutyLayout=(RelativeLayout)this.findViewById(R.id.change_duty_layout);
        m_txtvDutyName=(TextView) this.findViewById(R.id.txtv_change_duty_name);
        m_txtvTitle.setText("职务修改");
    }

    @Override
    public void processingLogic() {
        m_imgbtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
       m_dutyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDutyList();
            }
        });
        m_imgbtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                str_dutyName=m_txtvDutyName.getText().toString();
                intent.putExtra("duty", str_dutyName);
                ChangeDutyAty.this.setResult(PersonalInformationAty.UPDATA_DUTY_BACK_CODE, intent);
                finish();
            }
        });
    }
    private void showDutyList(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangeDutyAty.this);
        builder.setTitle("职务");
        //    指定下拉列表的显示数据
        final String[] cities = {"总经理", "会计", "出纳", "销售", "其他员工"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Toast.makeText(ChangeDutyAty.this, "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
                m_txtvDutyName.setText(cities[which].toString());
            }
        });
        builder.show();
    }
}
