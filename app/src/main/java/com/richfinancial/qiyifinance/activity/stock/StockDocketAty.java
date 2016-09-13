package com.richfinancial.qiyifinance.activity.stock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.bean.StockFunctionBean;
import com.richfinancial.qiyifinance.ui.zrc.widget.ZrcListView;
import com.richfinancial.qiyifinance.utils.TimeUtil;
import com.richfinancial.qiyiplus.R;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * Simple To Introduction
 * 项目名称:  XZBBusinessHelper
 * 包:        com.richibank.wealth.qywealth.activity.stock
 * 类名称:    StockDocketAty
 * 类描述:    账单列表
 * 创建人:    luqb
 * 创建时间:  2016-09-06
 * 修改人:    luqb
 * 修改时间:  [${date} ${time}]
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class StockDocketAty extends BaseActivity {

    private ImageButton imgbtn_left;
    private ImageButton imgbtn_right;
    private TextView txtv_title;
    private ImageButton imgbtn_lastMonth;
    private TextView txtv_date;
    private ImageButton imgbtn_nextMonth;
    private ZrcListView zlv_list;

    private String str_time;
    private Date m_date;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_stock_docket);
    }

    @Override
    public void initView() {
        imgbtn_left = (ImageButton) findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton) findViewById(R.id.imgbtn_right);
        txtv_title = (TextView) findViewById(R.id.txtv_title);
        imgbtn_lastMonth = (ImageButton) findViewById(R.id.imgbtn_lastMonth);
        txtv_date = (TextView) findViewById(R.id.txtv_date);
        imgbtn_nextMonth = (ImageButton) findViewById(R.id.imgbtn_nextMonth);
        zlv_list = (ZrcListView) findViewById(R.id.zlv_list);

        txtv_date.setText(str_time);
        txtv_title.setText("1211");
        imgbtn_right.setVisibility(View.GONE);
        /** 返回 */
        imgbtn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /** ListView点击事件 */
        zlv_list.setOnItemClickListener(new ZrcListView.OnItemClickListener() {
            @Override
            public void onItemClick(ZrcListView parent, View view, int position, long id) {
                Toast.makeText(StockDocketAty.this, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        /** 上一个月 */
        imgbtn_lastMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_date.setMonth(m_date.getMonth() - 1);
                str_time = (m_date.getYear() + 1899) + "年" + (m_date.getMonth() + 1) + "月";
                txtv_date.setText(str_time);
            }
        });
        /** 下一个月 */
        imgbtn_nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_date.setMonth(m_date.getMonth() + 1);
                str_time = (m_date.getYear() + 1899) + "年" + (m_date.getMonth() + 1) + "月";
                txtv_date.setText(str_time);
            }
        });
    }

    @Override
    public void initData() {
        m_date = TimeUtil.getCurTimeDate(); // 获取当前时间
        str_time = (m_date.getYear() + 1899) + "年" + (m_date.getMonth() + 1) + "月";
    }

    @Override
    public void processingLogic() {

    }

    public class DocketMsgAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater inflater;

        public DocketMsgAdapter(Context context){
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (null == convertView) {
                //构造新的View
                convertView = inflater.inflate(R.layout.item_stock_docket_msg_layout, parent, false);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
//            StockFunctionBean bean = m_listStockFunctionBean.get(position);
//            holder.setHolderContent(bean);

            holder.ll_rightLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(getContext(), StockDocketAty.class);
//                    getContext().startActivity(intent);
                }
            });

            return convertView;
        }

        private class Holder{
            private LinearLayout ll_rightLayout;
            private TextView txtv_liftText;
            private TextView txtv_rightText;

            public Holder(View view){
                ll_rightLayout = (LinearLayout) view.findViewById(R.id.ll_rightLayout);
                txtv_liftText = (TextView) view.findViewById(R.id.txtv_liftText);
                txtv_rightText = (TextView) view.findViewById(R.id.txtv_rightText);
            }

            private void setHolderContent(StockFunctionBean data){
                txtv_liftText.setText(data.getStr_liftText());
                txtv_rightText.setText(data.getStr_rightText());
            }
        }
    }

    public class DocketMsgListAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater inflater;

        public DocketMsgListAdapter(Context context){
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (null == convertView) {
                //构造新的View
                convertView = inflater.inflate(R.layout.item_stock_docket_msg_layout, parent, false);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
//            StockFunctionBean bean = m_listStockFunctionBean.get(position);
//            holder.setHolderContent(bean);

            holder.ll_rightLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(getContext(), StockDocketAty.class);
//                    getContext().startActivity(intent);
                }
            });

            return convertView;
        }

        private class Holder{
            private LinearLayout ll_rightLayout;
            private TextView txtv_liftText;
            private TextView txtv_rightText;

            public Holder(View view){
                ll_rightLayout = (LinearLayout) view.findViewById(R.id.ll_rightLayout);
                txtv_liftText = (TextView) view.findViewById(R.id.txtv_liftText);
                txtv_rightText = (TextView) view.findViewById(R.id.txtv_rightText);
            }

            private void setHolderContent(StockFunctionBean data){
                txtv_liftText.setText(data.getStr_liftText());
                txtv_rightText.setText(data.getStr_rightText());
            }
        }
    }
}
