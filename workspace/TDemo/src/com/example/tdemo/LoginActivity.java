package com.example.tdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private final static int REQUEST_CODE = 1;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE) {
			if (resultCode == MainActivity.RESULT_CODE) {
				Bundle bundle = data.getExtras();
				String str = bundle.getString("back");
				Toast.makeText(LoginActivity.this, str, Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		Button LButton = (Button) findViewById(R.id.Lbutton);
		LButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);

			}
		});

		Button RButton = (Button) findViewById(R.id.Rbutton);
		RButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, RegistActivity.class);
				Bundle bundle = new Bundle();
				intent.putExtras(bundle);
				// startActivity(intent);
				startActivityForResult(intent, REQUEST_CODE);

			}
		});

		EditText pw = (EditText) findViewById(R.id.password);
		EditText nn = (EditText) findViewById(R.id.nickname);
		String password = pw.getText().toString();
		String nickname = nn.getText().toString();
		SharedPreferences sp = this.getSharedPreferences("User",Context.MODE_PRIVATE);
		Editor spEditor = sp.edit();
		spEditor.putString("name", nickname);
		spEditor.putString("password", password);
		spEditor.commit();

	}

}
