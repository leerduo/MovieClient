package com.dy.ustc.lifeclient.app;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class MyApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);

	}

	

}
