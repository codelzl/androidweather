package com.example.tdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		 Toast.makeText(context, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();

	}

}
