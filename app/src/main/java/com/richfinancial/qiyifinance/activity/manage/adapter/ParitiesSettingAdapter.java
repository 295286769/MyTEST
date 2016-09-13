package com.richfinancial.qiyifinance.activity.manage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richfinancial.qiyifinance.activity.manage.bean.ParitiesSettingBean;
import com.richfinancial.qiyiplus.R;

import java.util.List;

/**
 * ParitiesSettingAdapter
 * 项目名称:  QYManager
 * 包:        com.richibank.wealth.qywealth.activity.manage.adapter
 * 类名称:    ParitiesSettingAdapter
 * 类描述:    [汇率显示adapter]
 * 创建人:    baix
 * 创建时间:  2016/9/8
 * 修改人:    baix
 * 修改时间:  2016/9/8
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class ParitiesSettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;   //上下文
    private LayoutInflater mInflater;   //布局加载

    private List<ParitiesSettingBean> mList;     //布局数据

    private OnItemClickListener  onItemClickListener;

    private final int LAYOUT_TYPE_BODY = 1;

    public ParitiesSettingAdapter(Context context, List<ParitiesSettingBean> list) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mList = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View inflatedView ;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case LAYOUT_TYPE_BODY:
                inflatedView = mInflater.inflate(R.layout.item_parities_setting , viewGroup, false);
                holder = new ViewHolderBody(inflatedView);
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ParitiesSettingBean bean = mList.get(position);
        if (viewHolder instanceof ViewHolderBody) {
            ViewHolderBody bodyHolder = (ViewHolderBody)viewHolder;
            bodyHolder.item_parities_name.setText(bean.getName());
            bodyHolder.item_parities_money.setText(bean.getMoney());
            bodyHolder.item_parities_time.setText(bean.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return LAYOUT_TYPE_BODY;
    }

    /**
     * 正常数据布局
     */
    public class ViewHolderBody extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView item_parities_name = null;//名称
        private TextView item_parities_money = null;//钱
        private TextView item_parities_time = null;//时间

        public ViewHolderBody(View itemView) {
            super(itemView);
            this.item_parities_name = (TextView) itemView.findViewById(R.id.item_parities_name);
            this.item_parities_money = (TextView) itemView.findViewById(R.id.item_parities_money);
            this.item_parities_time = (TextView) itemView.findViewById(R.id.item_parities_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(v , getPosition());
            }
        }
    }

    public interface OnItemClickListener  {
        public void onItemClick(View view, int position);
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
