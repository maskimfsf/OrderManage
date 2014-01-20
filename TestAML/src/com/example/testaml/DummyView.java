package com.example.testaml;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class DummyView extends View{

	private Handler mainHandler;
	
	public DummyView(Context context) {
		super(context);
	}
	
	public DummyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		this.setVisibility(View.GONE);
		if (this.mainHandler != null)
		{
			this.mainHandler.sendEmptyMessage(0);
		}
	}

	public void setMainHandler(Handler mainHandler) {
		this.mainHandler = mainHandler;
	}
	
	
	
}
