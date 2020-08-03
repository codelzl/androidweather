package com.example.tdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChatothersActivity extends Activity {
    private Button sbutton;
    private EditText sedit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_ohers);
		sedit=(EditText) this.findViewById(R.id.edit1);
	     
		sbutton=(Button) this.findViewById(R.id.Sbutton);
		sbutton.setOnClickListener( new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    String mes1=sedit.getText().toString();
				Intent intent = new Intent();
				intent.setAction("com.chat");
				intent.putExtra("name", ""+mes1);
				ChatothersActivity.this.sendBroadcast(intent);
				intent.setClass(ChatothersActivity.this, ChatActivity.class);
				Bundle bundle = new Bundle();
				intent.putExtras(bundle);
				startActivity(intent);
			}
			
			
		});
		
	}

}
