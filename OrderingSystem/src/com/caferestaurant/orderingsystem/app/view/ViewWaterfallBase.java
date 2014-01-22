package com.caferestaurant.orderingsystem.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 瀑布流的基类视图
 * 所有涉及到瀑布流的视图必须从此派生
 * 参照TestViewWaterfall类的实现
 * @author wangji
 *
 */
public abstract class ViewWaterfallBase extends ViewBase{

	private LayoutInflater inflater;
	
	public ViewWaterfallBase(Context context) {
		super(context);
		this.init(context);
	}
	
	public ViewWaterfallBase(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init(context);
	}

	private WaterfallLayout waterfall;
	
	private void init(Context context)
	{
		this.setInflater(LayoutInflater.from(context));
		
		this.waterfall = new WaterfallLayout(context);
		this.waterfall.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		this.addView(this.waterfall);
		
		this.waterfall.setFirstTimeDrawHandler(new WaterfallLayout.FirstTimeDrawListener() {
			
			@Override
			public void onFirstTimeDraw(View target) {
				onDrawStart(target);
			}
		});
		
		this.waterfall.setScrollToBottomHandler(new WaterfallLayout.ScrollToBottomListener() {
			
			@Override
			public void onScrollToBottom(View target) {
				onGotoBottom(target);
			}
		});

	}
	
	protected void addViewToWaterfall(View v)
	{
		this.waterfall.addViewToIndexFlow(v, this.waterfall.getShortestFlowIndex());
	}
	
	abstract void onDrawStart(View t);
	
	abstract void onGotoBottom(View t);
	
	protected void setDataFinished()
	{
		this.waterfall.setLoadingFinished(true);
	}
	
	protected void setLoadingFinished()
	{
		this.waterfall.setLoadingProcess(false);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed)
		{
			this.waterfall.layout(l, t, r, b);
		}
	}

	public LayoutInflater getInflater() {
		return inflater;
	}

	private void setInflater(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	@Override
	public String getViewCaption() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.waterfall.measure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	
}
