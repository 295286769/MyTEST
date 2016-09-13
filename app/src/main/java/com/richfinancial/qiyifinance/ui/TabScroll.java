package com.richfinancial.qiyifinance.ui;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义view，用于tab下面的高亮显示
 */
public class TabScroll extends View {

	private int itemLen = 0;
	private int page = 0;
	private float pageRate = 0f;

	private Paint paint;

	public void setItemLen(int i) {
		itemLen = i;
	}

	public void scroll(int p, float pr) {
		page = p;
		pageRate = pr;
		invalidate();
	}

	public TabScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		float from = (page + pageRate) * itemLen;
		//paint.setColor(getResources().getColor(R.color.color_menu_bg2));
		canvas.drawRect(from, 0, from + itemLen, getBottom(), paint);
		super.onDraw(canvas);
	}

	/***
	 * 设置paint的颜色
	 * @param paintColor 颜色值
     */
	public void setPaintColor(int paintColor)
	{
		paint.setColor(paintColor);
	}
}
