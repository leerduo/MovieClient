package com.dy.ustc.lifeclient.utils;

public interface HttpCallBackListener {
	
	void onFinish(String response);
	
	void onError(Exception e);

}
