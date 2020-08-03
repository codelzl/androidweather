package com.example.tdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class TestHandler extends Activity {
	TextView textview;
	private MyHandler myhandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myhandler=new MyHandler();
		myThread m=new myThread();
		new Thread(m).start();
		 
	}
	private class MyHandler extends Handler{
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			textview.setText("");
		}
		
	}
	private class myThread implements Runnable{
	 
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg=new Message();
			TestHandler.this.myhandler.sendMessage(msg);
		}
	}

	

}
