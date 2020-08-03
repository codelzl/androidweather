package com.example.tdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ChatActivity extends Activity {

	private ListView lv;
	private  List<Map<String, Object>> data;
	private   MyDbHelper mdbhelper;
	private Context mcontext;
	MyAdapter adapter;
	Receiver  receiver;
	MyService myservice;
	
	protected void onCreate(Bundle savedInstanceState) {
		 sendNotificition();
		 //sendSimpleNotificationWithAction();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_main);
		lv = (ListView) findViewById(R.id.lv);
		data = getdata();
		adapter = new MyAdapter(this,data);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ChatActivity.this, ChatothersActivity.class);
				Bundle bundle = new Bundle();
				intent.putExtras(bundle);
				startActivity(intent);
			}});
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				// TODO Auto-generated method stub
				final AlertDialog.Builder builder=new Builder(ChatActivity.this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("确认信息");
				builder.setMessage("确认删？");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						data.remove(arg2);
						adapter.notifyDataSetChanged();
					}
				});
				builder.setNegativeButton("不，我拒绝", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
					}
				});
				builder.show();
				return true;
			}}
		);
		receiver=new Receiver();
		IntentFilter filter=new IntentFilter();
		filter.addAction("com.chat");
		this.registerReceiver(receiver, filter);
	
		
		ServiceConnection conn=new ServiceConnection(){

			@Override
			public void onServiceConnected(ComponentName arg0, IBinder arg1) {
				// TODO Auto-generated method stub
				Log.e("service","con");
			}

			@Override
			public void onServiceDisconnected(ComponentName arg0) {
				// TODO Auto-generated method stub
				Log.e("service","discon");
				
			}
			
			
		};
		Intent intent=new Intent(MyService.ACTION);
		startService(intent);
		bindService(intent, conn, BIND_AUTO_CREATE);
		stopService(intent);
	}

	private List<Map<String, Object>> getdata() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("img", R.drawable.ic_launcher);
			map.put("name", "heheh"+i);
			map.put("mes", "66666666");
			map.put("ChatDate", "7:20");
			list.add(map);
		}
		return list;

	}
	
	private void sendNotificition(){
		NotificationManager NotifyManger=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle("通知")
		.setContentText("小标题内容");
		NotifyManger.notify(1, builder.build());
	}
	
	private void sendSimpleNotificationWithAction(){
		
		NotificationManager NotifyManger=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Intent mainintent=new Intent(this,ChatActivity.class);
		PendingIntent mainPendingIntent=PendingIntent.getActivity(this, 0, mainintent,PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle("TITLE")
		.setContentText("TEXT")
		.setContentIntent(mainPendingIntent);
		Notification notification=builder.build();
		notification.flags=Notification.FLAG_AUTO_CANCEL;
		NotifyManger.notify(3,builder.build());
	}
	
  private void openDB(){
	  mdbhelper=new MyDbHelper(mcontext);
  }
  public void closeDB(){
	  mdbhelper.close();
  }
   public void insertTODB(){
	   SQLiteDatabase db=mdbhelper.getWritableDatabase();
	   db.beginTransaction();
	   for(int i=0;i<10;i++){
	   ContentValues contentvalues=new ContentValues();
	   contentvalues.put("Id", 3);
	   contentvalues.put("Img", R.drawable.ic_launcher);
	   contentvalues.put("Name", "name"+i);
	   contentvalues.put("Chatdate", "9:30");
	   db.insert( MyDbHelper.DB_TABLE, null,contentvalues );
	   db.setTransactionSuccessful();
	   }
	   db.endTransaction();
	   
   }


}
