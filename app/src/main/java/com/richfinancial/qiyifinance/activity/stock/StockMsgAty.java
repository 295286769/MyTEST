package com.richfinancial.qiyifinance.activity.stock;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.activity.main.StockFrg;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.bean.StockFunctionBean;
import com.richfinancial.qiyifinance.bean.StockMsgBean;

import net.tsz.afinal.annotation.view.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple To Introduction
 * 项目名称:  XZBBusinessHelper
 * 包:        com.richibank.wealth.qywealth.activity.stock
 * 类名称:    StockMsgAty
 * 类描述:    销售出货，采购进货，销售退货，采购退货，借贷销售
 * 创建人:    luqb
 * 创建时间:  2016-09-06
 * 修改人:    luqb
 * 修改时间:  [${date} ${time}]
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class StockMsgAty extends BaseActivity {

    private ImageButton imgbtn_left;
    private ImageButton imgbtn_right;
    private TextView txtv_title;
    private ListView lv_msg;
    private Button btn_statement;
    private TextView txtv_total;

    private List<StockMsgBean> ary_stockMsgBean;
    private MyAdapter m_adapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_stock_msg);
    }

    @Override
    public void initView() {

        imgbtn_left = (ImageButton) findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton) findViewById(R.id.imgbtn_right);
        txtv_title = (TextView) findViewById(R.id.txtv_title);
        lv_msg = (ListView) findViewById(R.id.lv_msg);
        btn_statement = (Button) findViewById(R.id.btn_statement);
        txtv_total = (TextView) findViewById(R.id.txtv_total);

        imgbtn_right.setVisibility(View.GONE);
        lv_msg.setCacheColorHint(00000000);
        lv_msg.setDividerHeight(0);

        m_adapter = new MyAdapter(this);
        lv_msg.setAdapter(m_adapter);

        imgbtn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        ary_stockMsgBean = new ArrayList<>();
        // 初始化测试数据
        for(int i=0; i<5; i++){
            StockMsgBean bean = new StockMsgBean();
            bean.setStr_name("测试"+(i+1));
            bean.setStr_num((i+1000)+"");
            bean.setStr_pice((i+100)+"");
            ary_stockMsgBean.add(bean);
        }

    }

    @Override
    public void processingLogic() {

    }

    public class MyAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater inflater;

        public MyAdapter(Context context){
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return ary_stockMsgBean.size();
        }

        @Override
        public Object getItem(int position) {
            return ary_stockMsgBean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (null == convertView) {
                //构造新的View
                convertView = inflater.inflate(R.layout.item_stock_message_layout, parent, false);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            StockMsgBean bean = ary_stockMsgBean.get(position);
            holder.setHolderContent(bean);

            holder.cb_selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Toast.makeText(context, "isChecked:" + isChecked, Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }

        private class Holder{
            private CheckBox cb_selected;
            private TextView txtv_name;
            private TextView txtv_pice;
            private TextView txtv_num;

            public Holder(View view){
                cb_selected = (CheckBox) view.findViewById(R.id.cb_selected);
                txtv_name = (TextView) view.findViewById(R.id.txtv_name);
                txtv_pice = (TextView) view.findViewById(R.id.txtv_pice);
                txtv_num = (TextView) view.findViewById(R.id.txtv_num);
            }

            private void setHolderContent(StockMsgBean data){
                txtv_name.setText(data.getStr_name());
                txtv_pice.setText(data.getStr_pice());
                txtv_num.setText(data.getStr_num());
            }
        }
    }

}
