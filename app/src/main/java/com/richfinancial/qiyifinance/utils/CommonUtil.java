package com.richfinancial.qiyifinance.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:    程序中常用的工具
 * 创建人:    whb
 * 创建时间:  2016-03-28
 * 修改人:    whb
 * 修改时间:  2016-03-28
 * 修改备注:  初始版本
 * 版本:      v1.0
 */
public class CommonUtil {

    //常用的key
    public static final String KEY_MISSION_ID = "KEY_MISSION_ID";

    //广播

    /** SDcard路径 */
    public static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/";

    /**
     * 禁用输入框的粘贴功能
     * @param txtvTarget
     */
    public static void disableTextViewLongClick(TextView txtvTarget){
        //覆盖回调函数
        txtvTarget.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        });

        txtvTarget.setLongClickable(false); // Xml layout file android:longClickable="false"
        txtvTarget.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI); // android:imeOptions="flagNoExtractUi"
    }


    /**
     * 将多行的文本设置成额外的对其格式
     * @param edt 目标控件
     */
    public static void setGravityWhenMultiLine(final EditText edt){
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(edt.getLineCount()>1) {
                    edt.setGravity(Gravity.LEFT);
                } else {
                    edt.setGravity(Gravity.RIGHT);
                }
            }
        });
    }

    /**
     * 设置文字并调整对齐
     * @param edt
     * @param strInput
     */
    public static void setTextAndChangeGravity(final EditText edt,String strInput){
        if(!GsonUtil.isStringNull(strInput)){
            edt.setText(strInput);
            int i = edt.getWidth();
            TextPaint textPaint = edt.getPaint();
            float f = textPaint.measureText(edt.getText().toString()) + 6.0f;
            if(f > i){
                edt.setGravity(Gravity.LEFT);
            }else {
                edt.setGravity(Gravity.RIGHT);
            }
//            setGravityWhenMultiLine(edt);
        }
    }

    /**
     * 多行向左对齐 单行向右对齐
     * @param edt
     */
    public static void setTextMilutiGravity(final EditText edt){
        if(edt.getLineCount()>1) {
            edt.setGravity(Gravity.LEFT);
        } else {
            edt.setGravity(Gravity.RIGHT);
        }
    }
    /**
     * 检查字符串中是否含有中文
     * @param str 输入字符串
     * @return
     */
    static public boolean isCN(String str){
        try {
            byte [] bytes = str.getBytes("UTF-8");
            if(bytes.length == str.length()){
                return false;
            }else{
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取App版本号
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        String version = "1.0";
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo;
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
            version = packInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 判断当前是否有网络
     * @param context
     * @return true 有网路  false 无网络
     */
    public static boolean isHaveNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isAvailable()) {
            //当前无可用网络
            return false;
        }
        else {
            //当前有可用网络
            return true;
        }
    }
    /**
     * 检查文件夹是否存在，不存在的话则创建文件夹
     * @param path 文件夹路径
     */
    public static void isExist(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 禁止输入框输入某些内容
     * @param aty 为了方便弹出toast和messageBox
     * @param txtv 目标控件
     * @param bDisCN true 禁用中文；
     * @param nMaxLen 最大长度
     */
    public static void disableTextViewInput(final Activity aty, TextView txtv, final boolean bDisCN, final int nMaxLen){
        //对控件设置过滤方法
        txtv.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(nMaxLen),//限定输入长度
                new InputFilter(){

                    @Override
                    public CharSequence filter(CharSequence source, int start,
                                               int end, Spanned dest, int dstart, int dend) {
                        //禁止输入中文
                        if(isCN(source.toString())&&bDisCN==true){
                            Toast.makeText(aty, aty.getString(R.string.str_cannot_input_cn), Toast.LENGTH_SHORT).show();
                            return "";
                        } else {
                            return source;
                        }
                    }
                }//限定只输入中文
        });
    }

    /**
     * 判断字符串为空
     * @param s 字符串
     * @return true 空，false 非空
     */
    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    /***
     * 判断程序前后台运行状态
     * @param context 上下文
     * @return true 前台 false 后台
     */
    public static boolean isAppOnForeground(Context context) {
        // Returns a list of application processes that are running on the device
        ActivityManager activityManager;
        String packageName;
        activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        packageName = context.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取指定文件夹的大小
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSizes(File file) throws Exception {
        long s = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            s = fis.available();
        }
        else {
            file.createNewFile();
            System.out.println("文件不存在");
        }
        Log.d("dd" , "### s = " + s);
        s = s / 1024;
        return s;
    }

    /***
     * 获取唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID.
     * @param context 上下文
     * @return
     */
    public static String getIMEI(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
    /**
     * 调用系统发短信界面
     *
     * @param activity    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context activity, String phoneNumber, String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsContent);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);
    }
    /**
     * 获取App包名
     * @param context
     * @return
     * @throws Exception
     */
    public static String getPackageName(Context context) {
        // 获取packagemanager的实例
        String packageName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo;
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
            packageName = packInfo.packageName;
            return packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }
    /**
     * 打开网络设置界面
     * <p>3.0以下打开设置界面</p>
     *
     * @param context 上下文
     */
    public static void openWirelessSettings(Context context) {
/*        if (android.os.Build.VERSION.SDK_INT > 10) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }*/
        Intent intent=null;
        //判断手机系统的版本  即API大于10 就是3.0或以上版本
        if(android.os.Build.VERSION.SDK_INT>10){
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        }else{
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

}

    /**
     * 获取活动网络信息
     *
     * @param context 上下文
     * @return NetworkInfo
     */
    public static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 判断网络是否连接
     * <p>需添加权限 android.permission.ACCESS_NETWORK_STATE</p>
     *
     * @param context 上下文
     * @return true: 是<br>false: 否
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    /**
     * 拨打电话
     * @param activity 窗体
     * @param phoneNum 电话号码
     */
   public static void phoneCall(Activity activity,String phoneNum){
       if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.CALL_PHONE)
               != PackageManager.PERMISSION_GRANTED) {
           //申请权限
           ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 0);
       } else {
           //获得电话号码
           String tel =phoneNum.trim();
           Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           if (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
               activity.getApplicationContext().startActivity(intent);
           }
       }
   }

//    /**
//     * 获取联系人列表
//     * @param context 上下文
//     * @return 联系人列表
//     */
//    public static List<Contacts> getContactsInfos(Context context) {
//        ContentResolver resolver = context.getContentResolver();
//        List<Contacts> infos = new ArrayList<Contacts>();
//        // 获取联系人数据 访问联系人的内容提供者
//        // ContactsContract.AUTHORITY com.android.contacts 授权
//        // 该内容提供者操作是需要读写权限
//        // matcher.addURI(ContactsContract.AUTHORITY, "raw_contacts",
//        // RAW_CONTACTS);
//        // matcher.addURI(ContactsContract.AUTHORITY, "raw_contacts/#/data",
//        // RAW_CONTACTS_DATA);
//        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
//        Cursor curContacts = resolver.query(uri, new String[] { "_id" }, null,
//                null, null);
//        while (curContacts.moveToNext()) {
//            int _id = curContacts.getInt(0);
//            Contacts info = new Contacts();
//            uri = Uri.parse("content://com.android.contacts/raw_contacts/"
//                    + _id + "/data");
//            Cursor curContactData = resolver.query(uri, new String[] { "data1",
//                    "mimetype" }, null, null, null);
//            while (curContactData.moveToNext()) {
//                String data = curContactData.getString(0);
//                String mimetype = curContactData.getString(1);
//                if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {// 号码
//                    info.setPhone(data);
//                } else if ("vnd.android.cursor.item/name".equals(mimetype)) {// 姓名
//                    info.setName(data);
//                }
//            }
//            curContactData.close();
//            infos.add(info);
//        }
//        curContacts.close();
//        return infos;
//    }

}

