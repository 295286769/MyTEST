package com.richfinancial.qiyifinance.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.bean.NormalUserBean;

/**
 * Created by LENOVO on 2016/9/6.
 */
public class UserScanAty extends BaseActivity {

    private ImageButton  m_imgbtnRight;//标题栏左侧按钮
    private  ImageButton  m_imgbtnLeft;
    private TextView m_txtvTitle;//标题栏中间标题
    private NormalUserBean normalUserBean;
    private TextView  textv_user_name;//用户名
    private TextView textv_user_mail;//邮箱
    private TextView textv_user_phone;//手机
    private Button btnOperater;
    private String strFlag="";//判断从哪个页面来
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_uer_scan);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_imgbtnLeft= (ImageButton) this.findViewById(R.id.imgbtn_left);
        m_imgbtnRight= (ImageButton) this.findViewById(R.id.imgbtn_right);
        m_txtvTitle= (TextView) this.findViewById(R.id.txtv_title);
        textv_user_name= (TextView) this.findViewById(R.id.textv_user_name);
        textv_user_mail= (TextView) this.findViewById(R.id.textv_user_mail);
        textv_user_phone= (TextView) this.findViewById(R.id.textv_user_phone);
        btnOperater=(Button) this.findViewById(R.id.btn_operater);
        m_txtvTitle.setText("用户编辑");
        normalUserBean=(NormalUserBean)this.getIntent().getSerializableExtra("normalUser");
        textv_user_name.setText(normalUserBean.getUserName());
        textv_user_mail.setText(normalUserBean.getUserMail());
        textv_user_phone.setText(normalUserBean.getUserPhone());
        strFlag=this.getIntent().getStringExtra("fragmentName");
        if(strFlag.equals("DeleteUserFragment")){
            btnOperater.setText("恢复");

        }else if(strFlag.equals("NormalUserFragment")){
            //判断是不是主用户，是主用户，隐藏按钮，不是现实删除
            btnOperater.setText("删除");
        }
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

                finish();
            }
        });
        btnOperater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strFlag.equals("DeleteUserFragment")){
                    recoverUser();

                }else if(strFlag.equals("NormalUserFragment")){
                    deleteUser();
                }
            }
        });
    }

    /***
     * 恢复用户
     */
    private void recoverUser(){

    }
    /***
     * 删除用户
     */
    private void deleteUser(){

    }
}
