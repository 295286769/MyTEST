package com.richfinancial.qiyifinance.activity.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;

/**
 * Created by LENOVO on 2016/9/6.
 */
public class ChangeIndustryTypeAty extends BaseActivity {

    private ImageButton  m_imgbtnRight;//标题栏左侧按钮
    private  ImageButton  m_imgbtnLeft;
    private TextView m_txtvTitle;//标题栏中间标题
    private RelativeLayout m_IndustryTypeLayout;//职务layout
    private TextView m_txtvIndustryTypeName;
    private String str_industryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_change_industry_type);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_imgbtnLeft= (ImageButton) this.findViewById(R.id.imgbtn_left);
        m_imgbtnRight= (ImageButton) this.findViewById(R.id.imgbtn_right);
        m_txtvTitle= (TextView) this.findViewById(R.id.txtv_title);
        m_IndustryTypeLayout=(RelativeLayout)this.findViewById(R.id.change_industry_type_layout);
        m_txtvIndustryTypeName=(TextView) this.findViewById(R.id.txtv_change_industry_type_name);
        m_txtvTitle.setText("行业类型编辑");
    }

    @Override
    public void processingLogic() {
        m_imgbtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        m_IndustryTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDutyList();
            }
        });
        m_imgbtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                str_industryName=m_txtvIndustryTypeName.getText().toString();
                intent.putExtra("industryName", str_industryName);
                ChangeIndustryTypeAty.this.setResult(PersonalInformationAty.UPDATA_INDUSTRY_TYPE_BACK_CODE, intent);
                finish();
            }
        });
    }
    private void showDutyList(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangeIndustryTypeAty.this);
        builder.setTitle("行业类型");
        //    指定下拉列表的显示数据
        final String[] cities = {"电子/信息", "金融保险", "贸易销售", "制药医疗", "餐饮", "其他"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Toast.makeText(ChangeDutyAty.this, "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
                m_txtvIndustryTypeName.setText(cities[which].toString());
            }
        });
        builder.show();
    }
}
