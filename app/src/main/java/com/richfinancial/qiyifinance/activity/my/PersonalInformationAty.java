package com.richfinancial.qiyifinance.activity.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.TotalSizeLimitedDiscCache;
import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.ui.view.PicHelper;
import com.richfinancial.qiyifinance.utils.CommonUtil;
import com.richfinancial.qiyifinance.utils.GsonUtil;

/**
 * 类描述:    个人信息页面
 * 创建人:    android
 * 创建时间:  2016-09-05
 * 修改人:    wangfangfang
 * 修改时间:  2016-09-08
 * 修改备注:  个人信息页面
 * 版本:
 */

public class PersonalInformationAty extends BaseActivity {

    private ImageButton  m_imgbtnRight;//标题栏左侧按钮
    private ImageButton m_imgbtnLeft;  //title左侧按钮
    private TextView m_txtvTitle;//标题栏中间标题
    private RelativeLayout m_rlHeadLayout;   //头像layout
    private RelativeLayout m_rlNameLayout;   //名字layout
    private RelativeLayout m_rlDutyLayout;//职务layout
    private RelativeLayout m_rlMailLayout;//邮箱layout
    private RelativeLayout m_rlPhoneLayout;//手机layout
    private RelativeLayout m_rlUpdatePwdLayout;//修改密码layout
    private RelativeLayout m_rlCompanyLayout;//公司名称layout
    private RelativeLayout m_rlAreaLayout;//地区layout
    private RelativeLayout m_rlIndustryTypeLayout;//行业类别layout
    private TextView m_txtvPhoneNmber;//电话号码
    private TextView m_txtvname;//姓名
    private TextView m_txtvduty;//职务
    private TextView m_txtvCompanyName;//公司
    private TextView m_txtvIndustryTypeName;//行业类型
    private String str_phoneNum;
    private String m_strFileName;          // 拍照使用的图片名
    private String m_strVCardURL = "";       //名片地址(本地文件时，传入本地文件名) 裁剪后的路径
    private TextView m_txtv_right;
    public static final int UPDATA_NAME_BACK_CODE = 100001;//修改姓名返回codestrName
    public static final int UPDATA_DUTY_BACK_CODE = 100002;//修改职务返回codestrName
    public static final int UPDATA_COMPANY_NAME_BACK_CODE = 100003;//修改返回公司名称codestrName
    public static final int UPDATA_INDUSTRY_TYPE_BACK_CODE= 100004;//修改返回行业类型codestrName
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_persional_information);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        m_rlHeadLayout=(RelativeLayout) this.findViewById(R.id.head_layout);
        m_rlNameLayout=(RelativeLayout) this.findViewById(R.id.name_layout);
        m_rlDutyLayout=(RelativeLayout) this.findViewById(R.id.duty_layout);
        m_rlMailLayout=(RelativeLayout) this.findViewById(R.id.mail_layout);
        m_rlPhoneLayout=(RelativeLayout) this.findViewById(R.id.phone_layout);
        m_rlUpdatePwdLayout=(RelativeLayout) this.findViewById(R.id.update_pwd_layout);
        m_rlCompanyLayout=(RelativeLayout) this.findViewById(R.id.company_layout);
        m_rlAreaLayout=(RelativeLayout) this.findViewById(R.id.area_layout);
        m_rlIndustryTypeLayout=(RelativeLayout) this.findViewById(R.id.industry_type_layout);
        m_txtvPhoneNmber=(TextView)this.findViewById(R.id.txtv_phone_number);
        m_txtv_right=(TextView)this.findViewById(R.id.txtv_right);
        m_txtvname=(TextView)this.findViewById(R.id.txtv_name);
        m_txtvCompanyName=(TextView)this.findViewById(R.id.txtv_company_name);
        m_txtvIndustryTypeName=(TextView)this.findViewById(R.id.txtv_industry_type_name);
        m_txtvduty=(TextView)this.findViewById(R.id.txtv_duty_name);
         m_imgbtnRight= (ImageButton) this.findViewById(R.id.imgbtn_right);
        m_txtvTitle= (TextView) this.findViewById(R.id.txtv_title);
        m_imgbtnLeft=  m_imgbtnLeft= (ImageButton) this.findViewById(R.id.imgbtn_left);
        m_imgbtnRight.setVisibility(View.INVISIBLE);
        m_txtvTitle.setText("个人信息");
    }

    @Override
    public void processingLogic() {
        m_rlHeadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImgHeadClick(v);
            }
        });
        m_rlNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNameClick(v);
            }
        });
        m_rlDutyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             onDutyCick(v);
            }
        });
        m_rlMailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMailCick(v);
            }
        });
        m_rlPhoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPhoneCick(v);
            }
        });
        m_rlUpdatePwdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdatePwdClick(v);
            }
        });
        m_rlCompanyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCompanyClick(v);
            }
        });
        m_rlAreaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAreaClick(v);
            }
        });
        m_rlIndustryTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIndustryTypeClick(v);
            }
        });
        m_imgbtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /***
     * 点击行业类型
     * @param v
     */
    public void  onIndustryTypeClick(View v){
        Intent intent = new Intent(PersonalInformationAty.this, ChangeIndustryTypeAty.class);
        startActivityForResult(intent, PersonalInformationAty.UPDATA_INDUSTRY_TYPE_BACK_CODE);
    }
    /***
     * 点击地区
     * @param v
     */
    public void  onAreaClick(View v){
        Intent intent = new Intent();
        intent.setClass(this, ChangeNameAty.class);
        startActivity(intent);
    }
    /***
     * 点击公司名称
     * @param v
     */
    public void  onCompanyClick(View v){
        Intent intent = new Intent(PersonalInformationAty.this, ChangeComapanyNameAty.class);
        startActivityForResult(intent, PersonalInformationAty.UPDATA_COMPANY_NAME_BACK_CODE);
    }
    /***
     * 点击修改密码
     * @param v
     */
    public void  onUpdatePwdClick(View v){
        Intent intent = new Intent();
        intent.setClass(this, ChangePwdAty.class);
        startActivity(intent);
    }
    /***
     * 点击手机
     * @param v
     */
    public void  onPhoneCick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInformationAty.this);
        builder.setTitle("操作");
        //    指定下拉列表的显示数据
        final String[] cities = {"验证手机", "修改手机"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Toast.makeText(ChangeDutyAty.this, "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
                if(which==0){

                }else{
                    Intent intent = new Intent();
                    intent.setClass(PersonalInformationAty.this, ChangePhoneAty.class);
                    startActivity(intent);
                }

            }
        });
        builder.show();
    }
    /***
     * 点击邮箱
     * @param v
     */
    public void onMailCick(View v){
        Intent intent = new Intent();
        intent.setClass(this, ChangeMailAty.class);
        startActivity(intent);
    }
    /***
     * 点击职务
     * @param v
     */
    public void onDutyCick(View v){
        Intent intent = new Intent(PersonalInformationAty.this, ChangeDutyAty.class);
        startActivityForResult(intent, PersonalInformationAty.UPDATA_DUTY_BACK_CODE);
    }
    /***
     * 点击姓名
     * @param v
     */
    public void onNameClick(View v){
        Intent intent = new Intent(PersonalInformationAty.this, ChangeNameAty.class);
        startActivityForResult(intent, PersonalInformationAty.UPDATA_NAME_BACK_CODE);
    }

    /***
     * 点击头像
     * @param v
     */
    public void onImgHeadClick(View v){
        m_strFileName = System.currentTimeMillis() + ".jpg";
        PicHelper.choosePic(PersonalInformationAty.this, m_strFileName);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //若取消则不做任何处理
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        //根据请求码做相应处理
        switch (requestCode) {
            case PicHelper._MSG_CODE_CHOOSE_PIC_FROM_LOCAL_:    //从本地相册返回
                if (null == data) {
                    Toast.makeText(this, "读取文件失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                String strPicturePath = PicHelper.getLocalChoosePicName(PersonalInformationAty.this, data); // 获取图片路径dfd
                //若取消选取 则直接返回
                if (strPicturePath == null || strPicturePath.length() == 0) {
                    return;
                }
                m_strVCardURL = PicHelper.s_strTempPath + System.currentTimeMillis() + ".jpg"; // 裁剪后的路径
                PicHelper.progressCutPic(PersonalInformationAty.this, strPicturePath, m_strVCardURL, 300, 300); // 裁剪
                break;
            case PicHelper._MSG_CODE_CAMERA_PHOTO_:     //从相册返回资源
                m_strFileName = (String) m_txtv_right.getText();
                String strfilePath = PicHelper.s_strTempPath + m_strFileName; // 拍照图片保存地址

                m_strVCardURL = PicHelper.s_strTempPath + System.currentTimeMillis() + ".jpg"; // 裁剪后的路径
                PicHelper.progressCutPic(PersonalInformationAty.this, strfilePath, m_strVCardURL, 300, 300); // 裁剪
                break;

            case PicHelper._MSG_CODE_CUT_PIC_:  //图片拆切返回
                //ImageUtils.setImageOptions(180, 180, 0); // 设置图片大小和圆角
                if(!GsonUtil.isStringNull(m_strVCardURL)){
                    if(!CommonUtil.isHaveNet(PersonalInformationAty.this)){//没有网络
                        Toast.makeText(PersonalInformationAty.this, R.string.str_error_no_net_msg, Toast.LENGTH_SHORT).show();
                    }else {
                        updataImage(); // 发送上传图片请求
                    }
                }
                break;
            case UPDATA_NAME_BACK_CODE:    //修改姓名
                String strName = data.getStringExtra("name");
                Log.i("# dddd name=",""+strName);
               /* if(!CommonUtil.isHaveNet(PersonalInformationAty.this)){//没有网络
                    Toast.makeText(PersonalInformationAty.this, R.string.str_error_no_net_msg, Toast.LENGTH_SHORT).show();
                }else {
                    updataName(strName);  // 修改姓名
                }*/
                if(!GsonUtil.isStringNull(strName)){
                    m_txtvname.setText(strName);   // 修改姓名
                }
                break;
            case UPDATA_DUTY_BACK_CODE:    //修改职务
                String strDutyName = data.getStringExtra("duty");
                Log.i("# dddd name=",""+strDutyName);
               /* if(!CommonUtil.isHaveNet(PersonalInformationAty.this)){//没有网络
                    Toast.makeText(PersonalInformationAty.this, R.string.str_error_no_net_msg, Toast.LENGTH_SHORT).show();
                }else {
                    updataDuty(strDutyName);  // 修改职务
                }*/
                if(!GsonUtil.isStringNull(strDutyName)){
                    m_txtvduty.setText(strDutyName);   // 修改职务
                }
                break;
            case UPDATA_COMPANY_NAME_BACK_CODE:    //修改公司名
                String strCompanyName = data.getStringExtra("companyName");
                Log.i("# dddd name=",""+strCompanyName);
               /* if(!CommonUtil.isHaveNet(PersonalInformationAty.this)){//没有网络
                    Toast.makeText(PersonalInformationAty.this, R.string.str_error_no_net_msg, Toast.LENGTH_SHORT).show();
                }else {
                    updataCompany(strCompanyName);  // 修改姓名
                }*/
                if(!GsonUtil.isStringNull(strCompanyName)){
                    m_txtvCompanyName.setText(strCompanyName);   // 修改公司名
                }
                break;
            case UPDATA_INDUSTRY_TYPE_BACK_CODE:    //修改行业类型
                String strIndustryName = data.getStringExtra("industryName");
                Log.i("# dddd name=",""+strIndustryName);
               /* if(!CommonUtil.isHaveNet(PersonalInformationAty.this)){//没有网络
                    Toast.makeText(PersonalInformationAty.this, R.string.str_error_no_net_msg, Toast.LENGTH_SHORT).show();
                }else {
                    updataIndustryType(strIndustryName);  // 修改姓名
                }*/
                if(!GsonUtil.isStringNull(strIndustryName)){
                    m_txtvIndustryTypeName.setText(strIndustryName);   // 修改公司名
                }
                break;
        }
    }

    /***
     * 修改头像网络请求
     */
    private void updataImage(){

    }

    /***
     * 修改姓名网络请求
     * @param strName 姓名
     */
    private void updataName(String strName){

    }
    /***
     * 修改职务网络请求
     * @param strDutyName 职务
     */
    private void updataDuty(String strDutyName){

    }
    /***
     * 修改职务网络请求
     * @param strCompanyName 公司名
     */
    private void updataCompanyName(String strCompanyName){

    }
    /***
     * 修改行业类型网络请求
     * @param strIndustryType 行业类型
     */
    private void updataIndustryType(String strIndustryType){

    }
}
