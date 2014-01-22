package com.caferestaurant.orderingsystem.app;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.os.Looper;
import android.util.Log;

public class OrderingSystemApplication extends Application{
	private static final String TAG = OrderingSystemApplication.class
			.getName();

	// ������Activity����
	public List<Activity> mActivityList = new ArrayList<Activity>();

	/** ��������ʱ�Ĵ��� */
	@Override
	public void onCreate() {
		super.onCreate();
		// ����Ӧ�ü��쳣ʱ�Ĵ���
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread thread, Throwable throwable) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// ����������ǿ���˳��ĶԻ���
						if (mActivityList.size() > 0) {
							Looper.prepare();
							new AlertDialog.Builder(getCurrentActivity())
									.setTitle(R.string.app_name)
									.setMessage(R.string.err_fatal)
									.setPositiveButton(
											R.string.confirm,
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// ǿ���˳�����
													finish();
												}
											}).show();
							Looper.loop();
						}
					}
				}).start();

				// ����LOG
				Log.e(TAG, throwable.getMessage(), throwable);
			}
		});
		init();
	}

	private void init() {
		try {
			Class.forName("TestDataUtil");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �رճ������е�Activity
	 */
	public void clearActivityList() {
		for (int i = 0; i < mActivityList.size(); i++) {
			mActivityList.get(i).finish();
		}
		mActivityList.clear();
	}

	/**
	 * �ر�ʱ�Ĵ���
	 */
	public void finish() {
		// �رճ������е�Activity
		clearActivityList();

		// �˳�
		System.exit(0);
	}

	/**
	 * ��õ�ǰ��ǰ�˵�Activity
	 */
	public Activity getCurrentActivity() {
		if (mActivityList.size() > 0) {
			return mActivityList.get(mActivityList.size() - 1);
		}
		return null;
	}
}