package com.richfinancial.qiyifinance.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * MyListView
 * 项目名称:  XZBBusinessHelper
 * 包:        com.lc.xzbbusinesshelper.ui
 * 类名称:    MyListView
 * 类描述:    重写ListView 在ScorllView里嵌套ListView时，应调用重写后的MyListView
 * 创建人:    luqb
 * 创建时间:  2016-03-14
 * 修改人:    luqb
 * 修改时间:  2016-03-14
 * 修改备注:  重写ListView
 * 版本:      v1.0
 */
public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs,
                      int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
