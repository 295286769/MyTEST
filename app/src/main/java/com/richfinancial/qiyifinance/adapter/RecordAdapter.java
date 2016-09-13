package com.richfinancial.qiyifinance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.bean.HomeBean;
import com.richfinancial.qiyifinance.bean.RecordBean;

import java.util.List;

/**
 * 类描述:    首页列表adapter
 * 创建人:    android
 * 创建时间:  2016/5/30
 * 修改人:    android
 * 修改时间:  2016/5/30
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */
public class RecordAdapter extends BaseAdapter{

    private Context mContext;   //上下文
    private LayoutInflater mInflater;   //布局加载
    private List<RecordBean> mList;     //布局数据

    public RecordAdapter(Context context , List<RecordBean> list){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecordBean bean = mList.get(position);
        convertView = mInflater.inflate(R.layout.item_record_list, parent, false);
        TextView title = (TextView) convertView.findViewById(R.id.txtv_title);
        title.setText(bean.getTitle());
        return convertView;
    }

}
