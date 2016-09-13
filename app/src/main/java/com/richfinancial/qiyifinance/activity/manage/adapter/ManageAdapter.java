package com.richfinancial.qiyifinance.activity.manage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.activity.manage.bean.ManageBean;

import java.util.List;

/**
 * ManageAdapter
 * 项目名称:  QYWealth
 * 包:        com.richibank.wealth.qywealth.activity.manage.adapter
 * 类名称:    ManageAdapter
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    baix
 * 创建时间:  2016/9/6
 * 修改人:    baix
 * 修改时间:  2016/9/6
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */

public class ManageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;   //上下文
    private LayoutInflater mInflater;   //布局加载

    private List<ManageBean> mList;     //布局数据

    private OnItemClickListener  onItemClickListener;

    private final int LAYOUT_TYPE_HEADER = 0;
    private final int LAYOUT_TYPE_BODY = 1;

    public ManageAdapter(Context context, List<ManageBean> list) {
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
                inflatedView = mInflater.inflate(R.layout.item_manage, viewGroup, false);
                holder = new ViewHolderBody(inflatedView);
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ManageBean bean = mList.get(position);
        if (viewHolder instanceof ViewHolderBody) {
            ViewHolderBody bodyHolder = (ViewHolderBody)viewHolder;
            bodyHolder.item_name.setText(bean.getName());
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

        private TextView item_name = null;//名称

        public ViewHolderBody(View itemView) {
            super(itemView);
            this.item_name = (TextView) itemView.findViewById(R.id.item_manage_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(v , getPosition());
            }
        }
    }

    /**
     * foot布局
     */
    public class ViewHolderHeader extends RecyclerView.ViewHolder {
        public ViewHolderHeader(View itemView) {
            super(itemView);
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
