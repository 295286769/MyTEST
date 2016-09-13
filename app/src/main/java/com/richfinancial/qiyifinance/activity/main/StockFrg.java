package com.richfinancial.qiyifinance.activity.main;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;
import com.richfinancial.qiyifinance.activity.stock.StockDocketAty;
import com.richfinancial.qiyifinance.activity.stock.StockMsgAty;
import com.richfinancial.qiyifinance.base.BaseActivity;
import com.richfinancial.qiyifinance.base.BaseFragment;
import com.richfinancial.qiyifinance.bean.StockFunctionBean;
import com.richfinancial.qiyifinance.ui.view.MyListView;

import net.tsz.afinal.annotation.view.ViewInject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * 类描述:    库存
 * 创建人:    android
 * 创建时间:  2016-03-09
 * 修改人:    luqb
 * 修改时间:  2016-03-09
 * 修改备注:  登录页
 * 版本:      v1.0
 */
public class StockFrg extends BaseFragment {

    private ImageButton imgbtn_right;

    private TextView txtv_title;

    private ImageButton imgbtn_left;

    private ScrollView sv_layout;

    /** 本月销售额 */
//    @ViewInject(id = R.id.txtv_monthSales)
    private TextView txtv_monthSales;
    /** 总库存 */
//    @ViewInject(id = R.id.txtv_allStock)
    private TextView txtv_allStock;
    /** 仓库管理 */
//    @ViewInject(id = R.id.rl_storeManger)
    private RelativeLayout rl_storeManger;
    /** 货品管理 */
//    @ViewInject(id = R.id.rl_goodsManger)
    private RelativeLayout rl_goodsManger;
    /** 功能列表 */
//    @ViewInject(id = R.id.lv_msg)
    private MyListView lv_msg;

    /** 功能列表的bean */
    private List<StockFunctionBean> m_listStockFunctionBean;
    private MyAdapter m_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initLayout(R.layout.activity_stock);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initData() {
        m_listStockFunctionBean = new ArrayList<>();
        StockFunctionBean bean = new StockFunctionBean();
        bean.setStr_liftText("销售出货");
        bean.setStr_rightText("销售单");
        m_listStockFunctionBean.add(bean);
        bean = new StockFunctionBean();
        bean.setStr_liftText("采购进货");
        bean.setStr_rightText("采购单");
        m_listStockFunctionBean.add(bean);
        bean = new StockFunctionBean();
        bean.setStr_liftText("销售退货");
        bean.setStr_rightText("销售退货单");
        m_listStockFunctionBean.add(bean);
        bean = new StockFunctionBean();
        bean.setStr_liftText("采购退货");
        bean.setStr_rightText("采购退货单");
        m_listStockFunctionBean.add(bean);
        bean = new StockFunctionBean();
        bean.setStr_liftText("借贷销售");
        bean.setStr_rightText("借贷销售单");
        m_listStockFunctionBean.add(bean);
    }

    @Override
    public void initView() {
        sv_layout = (ScrollView) getView().findViewById(R.id.sv_layout);
        txtv_title = (TextView) getView().findViewById(R.id.txtv_title);
        imgbtn_left = (ImageButton) getView().findViewById(R.id.imgbtn_left);
        imgbtn_right = (ImageButton) getView().findViewById(R.id.imgbtn_right);
        txtv_monthSales = (TextView) getView().findViewById(R.id.txtv_monthSales);
        txtv_allStock = (TextView) getView().findViewById(R.id.txtv_allStock);
        rl_storeManger = (RelativeLayout) getView().findViewById(R.id.rl_storeManger);
        rl_goodsManger = (RelativeLayout) getView().findViewById(R.id.rl_goodsManger);
        lv_msg = (MyListView) getView().findViewById(R.id.lv_msg);

        txtv_title.setText(R.string.tab_stock);
        imgbtn_left.setVisibility(View.INVISIBLE);
        imgbtn_right.setVisibility(View.VISIBLE);
        lv_msg.setDividerHeight(0);
        lv_msg.setCacheColorHint(00000000);
        m_adapter = new MyAdapter(getContext());
        lv_msg.setAdapter(m_adapter);

        // 将View放在顶部
        sv_layout.smoothScrollTo(0,0);
        getView().setFocusable(true);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
    }

    @Override
    public void processingLogic() {
        txtv_monthSales.setText("123456.78");
        txtv_allStock.setText("99999.99");
        // 右上角管理按钮
        imgbtn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击：管理", Toast.LENGTH_SHORT).show();
            }
        });
        // 仓库管理
        rl_storeManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击：仓库管理", Toast.LENGTH_SHORT).show();
            }
        });
        // 货品管理
        rl_goodsManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击：货品管理", Toast.LENGTH_SHORT).show();
            }
        });
        // 功能列表管理
        lv_msg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setClass(getContext(), StockMsgAty.class);
                getContext().startActivity(intent);

//                Toast.makeText(getContext(), "点击：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater inflate;

        public MyAdapter(Context context){
            this.context = context;
            this.inflate = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return m_listStockFunctionBean.size();
        }

        @Override
        public Object getItem(int position) {
            return m_listStockFunctionBean.get(position);
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
                convertView = inflate.inflate(R.layout.item_stock_msg_layout, parent, false);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            StockFunctionBean bean = m_listStockFunctionBean.get(position);
            holder.setHolderContent(bean);

            holder.ll_rightLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), StockDocketAty.class);
                    getContext().startActivity(intent);
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
