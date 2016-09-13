package com.richfinancial.qiyifinance.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.bean.HomeBean;

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
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> /*implements View.OnClickListener*/ {

    private Context mContext;   //上下文
    private LayoutInflater mInflater;   //布局加载
    private List<HomeBean> mList;     //布局数据

    private final int LAYOUT_TYPE_BODY = 0;
    private final int LAYOUT_TYPE_FOOT = 1;

    public HomeAdapter(Context context, List<HomeBean> list) {
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
                inflatedView = mInflater.inflate(R.layout.item_home_list, viewGroup, false);
                holder = new HomeViewHolderBody(inflatedView);
                break;
            case LAYOUT_TYPE_FOOT:
                inflatedView = mInflater.inflate(R.layout.item_home_list_foot, viewGroup, false);
                holder = new HomeViewHolderFoot(inflatedView);
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        HomeBean bean = mList.get(position);
        if (viewHolder instanceof HomeViewHolderBody) {
            HomeViewHolderBody bodyHolder = (HomeViewHolderBody)viewHolder;
            if (bean.getTag() != null && !bean.getTag().equals("")) {
                bodyHolder.tagView.setText(bean.getTag());
                bodyHolder.tagView.setVisibility(View.VISIBLE);
                bodyHolder.lineView.setVisibility(View.VISIBLE);
            } else {
                bodyHolder.tagView.setVisibility(View.GONE);
                bodyHolder.lineView.setVisibility(View.GONE);
            }
            bodyHolder.leftBtn.setBackgroundResource(bean.getmResIcon());
            bodyHolder.title.setText(bean.getTitle());
            bodyHolder.num.setText(bean.getNum());
//            bodyHolder.content.setOnClickListener(this);
            bodyHolder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "position : " + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    switch (position){
//                        case 3:
//                            intent.setClass(mContext, ReceivablesAty.class);
//                            mContext.startActivity(intent);
//                            break;
//                        case 4:
//                            intent.setClass(mContext, PayableAty.class);
//                            mContext.startActivity(intent);
//                            break;
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mList.size() - 1) {
            return LAYOUT_TYPE_FOOT;
        } else {
            return LAYOUT_TYPE_BODY;
        }
    }

//    @Override
//    public void onClick(View v) {
//
//    }

    /**
     * 正常数据布局
     */
    public class HomeViewHolderBody extends RecyclerView.ViewHolder {
        TextView tagView;
        View lineView;
        ImageButton leftBtn;
        TextView title;
        TextView num;
        LinearLayout content;

        public HomeViewHolderBody(View itemView) {
            super(itemView);
            tagView = (TextView) itemView.findViewById(R.id.txtv_tag);
            lineView = itemView.findViewById(R.id.v_line);
            leftBtn = (ImageButton) itemView.findViewById(R.id.imgbtn_left);
            title = (TextView) itemView.findViewById(R.id.txtv_title);
            num = (TextView) itemView.findViewById(R.id.txtv_num);
            content = (LinearLayout) itemView.findViewById(R.id.lay_content);
        }
    }

    /**
     * foot布局
     */
    public class HomeViewHolderFoot extends RecyclerView.ViewHolder {
        public HomeViewHolderFoot(View itemView) {
            super(itemView);
        }
    }
}
