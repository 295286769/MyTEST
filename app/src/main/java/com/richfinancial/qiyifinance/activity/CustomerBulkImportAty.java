package com.richfinancial.qiyifinance.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyifinance.activity.manage.adapter.SortAdapter;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.ui.SideBar;
import com.richfinancial.qiyifinance.utils.ContactUtils;
import com.richfinancial.qiyifinance.utils.EventUtils;
import com.richfinancial.qiyifinance.utils.PinyinComparator;
import com.richfinancial.qiyifinance.utils.PinyinUtils;
import com.richfinancial.qiyiplus.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CustomerBulkImportAty
 * 项目名称:  QYManager
 * 包:        com.richibank.wealth.qywealth.activity.manage
 * 类名称:    CustomerBulkImportAty
 * 类描述:    [客户批量导入]
 * 创建人:    baix
 * 创建时间:  2016/9/7
 * 修改人:    baix
 * 修改时间:  2016/9/7
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class CustomerBulkImportAty extends BaseActivity implements View.OnClickListener{

    public static final String TEL_PHONES = "TEL_PHONES";

    private ImageButton imgbtn_left;
    private ImageButton imgbtn_right;
    private TextView txtv_title;

    private ListView mListView;
    private SortAdapter mSortAdapter;
    private List<ContactUtils.ContactInfo> mContactsList;
    private SideBar mSidebar;
    private TextView mDialog;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contact_lv);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton)findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton)findViewById(R.id.imgbtn_right);
        txtv_title = (TextView)findViewById(R.id.txtv_title);
        txtv_title.setText(getString(R.string.str_txtv_bulk_import));

        imgbtn_left.setOnClickListener(this);
        imgbtn_right.setOnClickListener(this);

        mSidebar = (SideBar) findViewById(R.id.contact_sidebar);
        mListView = (ListView) findViewById(R.id.contact_listview);
        mDialog = (TextView) findViewById(R.id.contact_dialog);
        mSidebar.setTextView(mDialog);
        // 设置字母导航触摸监听
        mSidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = mSortAdapter.getPositionForSelection(s.charAt(0));

                if (position != -1) {
                    mListView.setSelection(position);
                }
            }
        });
        // 数据在放在adapter之前需要排序
        Collections.sort(mContactsList, new PinyinComparator());
        mSortAdapter = new SortAdapter(this, mContactsList);
        mListView.setAdapter(mSortAdapter);
    }

    @Override
    public void initData() {
        ContactUtils utils = new ContactUtils();
        ArrayList<String> phoneArr = new ArrayList<String>();
        //判断是否有已添加的电话号码
        if(getIntent().hasExtra(TEL_PHONES)){
            phoneArr = getIntent().getStringArrayListExtra(TEL_PHONES);
        }
        mContactsList = utils.getAllContacts(this , phoneArr);
        if(null!=mContactsList && mContactsList.size() > 0){
            for (int i = 0; i < mContactsList.size(); i++) {
                String pinyin = PinyinUtils.getPingYin(mContactsList.get(i).getName());
                String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                mContactsList.get(i).setPinYin(pinyin);
                // 正则表达式，判断首字母是否是英文字母
                if (Fpinyin.matches("[A-Z]")) {
                    mContactsList.get(i).setFirstPinYin(Fpinyin);
                } else {
                    mContactsList.get(i).setFirstPinYin("#");
                }
            }
        }
    }

    @Override
    public void processingLogic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_right:
                Toast.makeText(this, "提交", Toast.LENGTH_SHORT).show();
                EventUtils.contactEvent.post(mSortAdapter.getSelectedContactList());
                finish();
                break;

            case R.id.imgbtn_left:
                finish();
                break;

        }
    }
}
