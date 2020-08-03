package com.example.tdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	public static String ACTION="com.example.tdemo.MyService";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.e("service","create");
		super.onCreate();
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("service","destory");
		super.onDestroy();
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.e("service","startcmd");
		return super.onStartCommand(intent, flags, startId);
	}

}
