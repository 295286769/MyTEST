package com.richfinancial.qiyifinance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.bean.HomeBean;
import com.richfinancial.qiyifinance.bean.NormalUserBean;

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
public class NormalUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private Context mContext;   //上下文
    private LayoutInflater mInflater;   //布局加载
    private List<NormalUserBean> mList;     //布局数据

    private final int LAYOUT_TYPE_BODY = 0;
    private final int LAYOUT_TYPE_FOOT = 1;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private ViewHolder holder = null;
    public NormalUserAdapter(Context context, List<NormalUserBean> list) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mList = list;
    }

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, NormalUserBean data);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View inflatedView ;
        inflatedView = mInflater.inflate(R.layout.item_normal_user, viewGroup, false);
        holder = new ViewHolder (inflatedView);
        inflatedView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
         viewHolder.itemView.setTag(mList.get(position));
        holder.setHolderContent(mList.get(position));
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                 mOnItemClickListener.onItemClick(v, (NormalUserBean)v.getTag());
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {

           this.mOnItemClickListener = listener;
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

    /**
     * 正常数据布局
     */
    public class ViewHolder  extends RecyclerView.ViewHolder {
        ImageView imgv_message_icon=null;//已读未读图片
        TextView textvUserName;
        TextView textvMail;
        TextView textvPhone;

        public ViewHolder (View itemView) {
            super(itemView);
            imgv_message_icon=(ImageView)itemView.findViewById(R.id.img_item_user);
            textvUserName = (TextView) itemView.findViewById(R.id.textv_item_user_name);
            textvMail = (TextView) itemView.findViewById(R.id.textv_mail);
            textvPhone = (TextView) itemView.findViewById(R.id.textv_phone);
        }
        /**
         * 将数据赋到控件上
         */
        public void setHolderContent(NormalUserBean normalUserBean) {
            //指针校验
            if(normalUserBean==null)return;

            this.textvUserName.setText(normalUserBean.getUserName());
            this.textvMail.setText(normalUserBean.getUserMail());
            this.textvPhone.setText(normalUserBean.getUserPhone());


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
