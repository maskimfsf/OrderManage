package com.example.testaml;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv1 = null;
	private TextView tv2 = null;
	private TextView tv3 = null;
	private TextView tv4 = null;
	private TextView tv5 = null;
	
	private TabHost tabHost = null;
	
	private LinearLayout linearLayout1;
	private LinearLayout linearLayout2;
	private LinearLayout linearLayout3;
	private int layoutWidth1 = -1;
	private int layoutWidth2 = -1;
	private int layoutWidth3 = -1;
	
	private DummyView dummy1;
	private DummyView dummy2;
	private DummyView dummy3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		this.initTabs();
		this.initControls();
		this.loadImagesInit();
		this.initLinearLayouts();
		this.initEvents();
		
		
		this.testSoftReferenceImageView();
		
		this.tabHost.setCurrentTabByTag("lou");
		this.adjustToolbalIcons(2);
		//View v = this.findViewById(R.id.view3);
		//int aa = v.getMeasuredWidth();
		
	}
	
	
	private void initControls()
	{
		this.tv1 = (TextView)this.findViewById(R.id.tv_love_beauty);
		this.tv2 = (TextView)this.findViewById(R.id.tv_search);
		this.tv3 = (TextView)this.findViewById(R.id.tv_fashion);
		this.tv4 = (TextView)this.findViewById(R.id.tv_shoppingcart);
		this.tv5 = (TextView)this.findViewById(R.id.tv_me);
		
		this.dummy1 = (DummyView)this.findViewById(R.id.dummy1);
		this.dummy2 = (DummyView)this.findViewById(R.id.dummy2);
		this.dummy3 = (DummyView)this.findViewById(R.id.dummy3);
		
		this.tv1.setClickable(true);
		this.tv2.setClickable(true);
		this.tv3.setClickable(true);
		this.tv4.setClickable(true);
		this.tv5.setClickable(true);
	
		this.tv1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				tabHost.setCurrentTabByTag("song");
				MainActivity.this.adjustToolbalIcons(0);
			}
		});
		
		this.tv2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				tabHost.setCurrentTabByTag("shou");
				MainActivity.this.adjustToolbalIcons(1);
			}
		});
		
		this.tv3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				tabHost.setCurrentTabByTag("lou");
				MainActivity.this.adjustToolbalIcons(2);
			}
		});
		
		this.tv4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				tabHost.setCurrentTabByTag("shui");
				MainActivity.this.adjustToolbalIcons(3);
			}
		});
		
		this.tv5.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				tabHost.setCurrentTabByTag("me");
				MainActivity.this.adjustToolbalIcons(4);
			}
		});
	}
	
	private void initLinearLayouts()
	{
		this.linearLayout1 = (LinearLayout)this.findViewById(R.id.linearLayout1);
		this.linearLayout2 = (LinearLayout)this.findViewById(R.id.linearLayout2);
		this.linearLayout3 = (LinearLayout)this.findViewById(R.id.linearLayout3);
		
		// coloring for debug
		//this.linearLayout1.setBackgroundColor(Color.RED);
		//this.linearLayout2.setBackgroundColor(Color.GREEN);
		//this.linearLayout3.setBackgroundColor(Color.BLUE);

		// for test
		
		/*DummyView dv = (DummyView)this.findViewById(R.id.layoutWidthViewListener);
		dv.setMeasuredSizeListener(new DummyView.MeasuredSizeChangedListener() {			
			@Override
			public void onMeasuredSizeChanged(int measuredWidth, int measuredHeight) {
				if (measuredWidth > 100)
				{
					
					for(int i = 0; i < 10; ++i)
					{
						SoftReferenceImageView v1 = new SoftReferenceImageView(MainActivity.this);
						v1.setImageIndex(ImageCacheManager.getInstance().getImageIDRandom());
						v1.measure(MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY), 
								MeasureSpec.makeMeasureSpec(10000, MeasureSpec.AT_MOST));
						
						MainActivity.this.linearLayout1.addView(v1);
					}
					
					/*SoftReferenceImageView v2 = new SoftReferenceImageView(MainActivity.this);
					v2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
					v2.setImageIndex(ImageCacheManager.getInstance().getImageIDRandom());
					MainActivity.this.linearLayout2.addView(v2);
					
					SoftReferenceImageView v3 = new SoftReferenceImageView(MainActivity.this);
					v3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
					v3.setImageIndex(ImageCacheManager.getInstance().getImageIDRandom());
					MainActivity.this.linearLayout3.addView(v3);
				}
			}
		});*/
	}
	
	class MyHandler extends Handler{
	 
		public MyHandler(Looper looper){
			super(looper);
		}
	
		@Override
		public void handleMessage(Message msg) {//处理消息
			for(int i = 0; i < 40; ++i)
			{
				LinearLayout ll = this.getShortestLayout();
				SoftReferenceImageView v1 = new SoftReferenceImageView(MainActivity.this);
				v1.setImageIndex(ImageCacheManager.getInstance().getImageIDRandom());
				v1.setLayoutParams(new LayoutParams(-1, -2));
				ll.addView(v1);
				ll.measure(
						MeasureSpec.makeMeasureSpec(MainActivity.this.linearLayout1.getMeasuredWidth(), MeasureSpec.EXACTLY),
						MeasureSpec.makeMeasureSpec(1000000, MeasureSpec.AT_MOST));
			}
		}
		
		private LinearLayout getShortestLayout()
		{
			LinearLayout ll = MainActivity.this.linearLayout1;
			int l1 = MainActivity.this.linearLayout1.getMeasuredHeight();
			int l2 = MainActivity.this.linearLayout2.getMeasuredHeight();
			int l3 = MainActivity.this.linearLayout3.getMeasuredHeight();
			
			if (l2 < l1)
			{
				if (l3 > l2)
				{
					return MainActivity.this.linearLayout2;
				}
				else
				{
					return MainActivity.this.linearLayout3;
				}
			}
			else
			{
				if (l3 > l1)
				{
					return MainActivity.this.linearLayout1;
				}
				else
				{
					return MainActivity.this.linearLayout3;
				}
			}
		}
	}
	
	
	private void initEvents()
	{
		Looper looper = Looper.myLooper();//取得当前线程里的looper
		 
        MyHandler mHandler =new MyHandler(looper);//构造一个handler使之可与looper通信
        mHandler.removeMessages(0);

		
		this.dummy1.setMainHandler(mHandler);
	}
	
	private void initTabs()
	{
		this.tabHost = (TabHost)this.findViewById(android.R.id.tabhost);
		tabHost.setup();
		//TabWidget tabWidget = tabHost.getTabWidget();
		
		tabHost.addTab(
				tabHost.newTabSpec("song").setIndicator("tab1").setContent(R.id.view1)
				);
		
		tabHost.addTab(
				tabHost.newTabSpec("shou").setIndicator("tab2").setContent(R.id.view2)
				);
		
		tabHost.addTab(
				tabHost.newTabSpec("lou").setIndicator("tab3").setContent(R.id.view3)
				);
		
		tabHost.addTab(
				tabHost.newTabSpec("shui").setIndicator("tab4").setContent(R.id.view4)
				);
		
		tabHost.addTab(
				tabHost.newTabSpec("me").setIndicator("tab5").setContent(R.id.view5)
				);
	}
	
	private void loadImagesInit()
	{
		ImageCacheManager.getInstance().addBitmapDrawable
			((BitmapDrawable)(this.getResources().getDrawable(R.drawable.weng1)));
		ImageCacheManager.getInstance().addBitmapDrawable
			((BitmapDrawable)(this.getResources().getDrawable(R.drawable.weng2)));
		ImageCacheManager.getInstance().addBitmapDrawable
			((BitmapDrawable)(this.getResources().getDrawable(R.drawable.weng3)));
		ImageCacheManager.getInstance().addBitmapDrawable
			((BitmapDrawable)(this.getResources().getDrawable(R.drawable.weng4)));
		ImageCacheManager.getInstance().addBitmapDrawable
			((BitmapDrawable)(this.getResources().getDrawable(R.drawable.weng5)));
		ImageCacheManager.getInstance().addBitmapDrawable
			((BitmapDrawable)(this.getResources().getDrawable(R.drawable.weng6)));
		ImageCacheManager.getInstance().addBitmapDrawable
			((BitmapDrawable)(this.getResources().getDrawable(R.drawable.weng7)));
		ImageCacheManager.getInstance().addBitmapDrawable
			((BitmapDrawable)(this.getResources().getDrawable(R.drawable.weng8)));
	}
	
	void testSoftReferenceImageView()
	{
		// this is only for test!!!
		//SoftReferenceImageView sriv = (SoftReferenceImageView)this.findViewById(R.id.test_image_view);
		//sriv.setImageIndex(ImageCacheManager.getInstance().getImageIDRandom());
	}
	
	private void adjustToolbalIcons(int currentSel)
	{
		this.tv1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_community_normal, 0, 0);
		this.tv2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_category_normal, 0, 0);
		this.tv3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_ico_home_normal, 0, 0);
		this.tv4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_shoppingcart_normal, 0, 0);
		this.tv5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_me_normal, 0, 0);
		
		switch (currentSel){
		case 0:
			this.tv1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_community_active, 0, 0);
			break;
		case 1:
			this.tv2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_category_active, 0, 0);
			break;
		case 2:
			this.tv3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_ico_home_active, 0, 0);
			break;
		case 3:
			this.tv4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_shoppingcart_active, 0, 0);
			break;
		default:
			this.tv5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.toolbar_me_active, 0, 0);
			break;
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
