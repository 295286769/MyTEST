package com.richfinancial.qiyifinance.activity.tax;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.bean.Pickers;
import com.richfinancial.qiyifinance.ui.CustomDialog;
import com.richfinancial.qiyifinance.ui.PickerScrollView;
import com.richfinancial.qiyiplus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 2016/9/6.
 */
public class AccountTaxAty extends BaseActivity {

    private ImageButton  m_imgbtnRight;//标题栏左侧按钮
    private  ImageButton  m_imgbtnLeft;
    private TextView m_txtvTitle;//标题栏中间标题
    private RelativeLayout rlTaxType;//纳税人类型
    private RelativeLayout rlTaxArea;//地区
    private RelativeLayout rlTaxCompany;//公司名
    private RelativeLayout rlTaxIndustry;//行业类型
    private RelativeLayout rlTaxName;//姓名
    private RelativeLayout rlTaxPhone;//手机
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;
    private Button bt_ok; // 确定按钮
    private RelativeLayout picker_rel; // 选择器布局
    private Button bt_picker_cancel;
    private String  clickFlag="";
    private String chooseContent="";
    private TextView textv_taxt_type;
    private TextView textv_tax_area;
    private TextView textv_industry_type;
    private CustomDialog customDialog;
    private TextView textv_tax_name;
    private TextView textv_tax_phone;
    private String taxName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_accounts_tax);
    }

    @Override
    public void initData() {
    }

    /***
     * 初始化行业纳税人类型
     */
    private void initTaxType(){
        list = new ArrayList<Pickers>();
        id = new String[] { "0", "1"};
        name = new String[] { "一般纳税人", "小规模纳税人"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }
    /***
     * 初始化地区
     */
    private void initArea(){
        list = new ArrayList<Pickers>();
        id = new String[] { "0", "1","2"};
        name = new String[] { "杭州", "上海","其他"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }
    /***
     * 初始化地区
     */
    private void initIndustryType(){
        list = new ArrayList<Pickers>();
        id = new String[] { "0", "1","2","3","4"};
        name = new String[] { "电子/信息", "金融/保险","贸易/消费","制药/医疗","其他"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }
    @Override
    public void initView() {
        m_imgbtnLeft= (ImageButton) this.findViewById(R.id.imgbtn_left);
        m_imgbtnRight= (ImageButton) this.findViewById(R.id.imgbtn_right);
        rlTaxType=(RelativeLayout)this.findViewById(R.id.rl_tax_type);//纳税人类型
        rlTaxArea=(RelativeLayout)this.findViewById(R.id.rl_tax_area);//地区
        rlTaxCompany=(RelativeLayout)this.findViewById(R.id.rl_tax_company);//公司名
        rlTaxIndustry=(RelativeLayout)this.findViewById(R.id.rl_tax_Industry);//行业类型
        rlTaxName=(RelativeLayout)this.findViewById(R.id.rl_tax_name);//姓名
        rlTaxPhone=(RelativeLayout)this.findViewById(R.id.rl_tax_phone);//姓名
        picker_rel = (RelativeLayout) findViewById(R.id.picker_rel);
        pickerscrlllview = (PickerScrollView) findViewById(R.id.pickerscrlllview);
        bt_ok= (Button) findViewById(R.id.picker_ok);
        m_imgbtnRight.setVisibility(View.INVISIBLE);
        m_txtvTitle= (TextView) this.findViewById(R.id.txtv_title);
        bt_picker_cancel=(Button)this.findViewById(R.id.picker_cancel) ;
        m_txtvTitle.setText("代账报税");
        textv_taxt_type=(TextView) this.findViewById(R.id.textv_taxt_type);
        textv_tax_area=(TextView) this.findViewById(R.id.textv_tax_area);
        textv_industry_type=(TextView) this.findViewById(R.id.textv_industry_type);
        textv_tax_name=(TextView) this.findViewById(R.id.textv_tax_name);
        textv_tax_phone=(TextView) this.findViewById(R.id.textv_tax_name);
        taxName=textv_tax_name.getText().toString();
    }

    @Override
    public void processingLogic() {
        m_imgbtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        rlTaxType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaxTypeClick(v);
            }
        });
        rlTaxArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaxAreaClick(v);
            }
        });
        rlTaxCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaxCompanyClick(v);
            }
        });
        rlTaxIndustry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaxIndustryClick(v);
            }
        });
        rlTaxName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaxNameClick(v);
            }
        });
        rlTaxPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaxPhoneClick(v);
            }
        });
        pickerscrlllview.setOnSelectListener(pickerListener);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickFlag.equals("TaxType")){//纳税人类型
                    textv_taxt_type.setText(chooseContent);
                    chooseContent="";
                }else if(clickFlag.equals("TaxArea")){//地区
                    textv_tax_area.setText(chooseContent);
                    chooseContent="";
                }else if(clickFlag.equals("TaxIndustry")){//行业类型
                    textv_industry_type.setText(chooseContent);
                    chooseContent="";
                }
                picker_rel.setVisibility(View.GONE);
            }
        });
        bt_picker_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clickFlag="";
               chooseContent="";
                picker_rel.setVisibility(View.GONE);
            }
        });
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            Log.i("####ddd =","选择：" + pickers.getShowId() + "--银行："
                    + pickers.getShowConetnt());
            chooseContent= pickers.getShowConetnt();
        }
    };
    /***
     * 点击纳税人类型
     * @param v
     */
    public void onTaxTypeClick(View v){
        initTaxType();
        clickFlag="TaxType";
        picker_rel.setVisibility(View.VISIBLE);
    }
    /***
     * 点击地区
     * @param v
     */
    public void onTaxAreaClick(View v){
        initArea();
        clickFlag="TaxArea";
        picker_rel.setVisibility(View.VISIBLE);
    }
    /***
     * 点击公司名
     * @param v
     */
    public void onTaxCompanyClick(View v){

    }
    /***
     * 点击行业类型
     * @param v
     */
    public void onTaxIndustryClick(View v){
        initIndustryType();
        clickFlag="TaxIndustry";
        picker_rel.setVisibility(View.VISIBLE);
    }
    /***
     * 点击姓名
     * @param v
     */
    public void onTaxNameClick(View v){

   /*     customDialog = new CustomDialog(AccountTaxAty.this);
        EditText edt_input = (EditText) customDialog.getEditText();//方法在CustomDialog中实现
        TextView txtv_title = (TextView) customDialog.getText();//方法在CustomDialog中实现
        txtv_title.setText("修改姓名");
        edt_input.setText(textv_tax_name.getText().toString());
        customDialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.show();*/
        final EditText etName = new EditText(this);
        etName.setText(taxName);
        new AlertDialog.Builder(this).setTitle("请输入姓名")
                .setView(etName)
                .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        textv_tax_name.setText(etName.getText().toString());
                    }
                }).setNegativeButton("取消", null).show();
    }
    /***
     * 点击电话
     * @param v
     */
    public void onTaxPhoneClick(View v){
        final EditText etPhone = new EditText(this);
        String taxPhone=textv_tax_phone.getText().toString();
        etPhone.setText(taxPhone);
        new AlertDialog.Builder(this).setTitle("请输入电话")
                .setView(etPhone)
                .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        textv_tax_phone.setText(etPhone.getText().toString());
                    }
                }).setNegativeButton("取消", null).show();
    }
}
