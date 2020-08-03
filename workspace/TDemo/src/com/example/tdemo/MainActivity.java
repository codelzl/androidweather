package com.example.tdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static int RESULT_CODE = 1;

	static int flag = 1;
	TextView TC;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void addimg() {
		final ImageView imgn = new ImageView(MainActivity.this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
		imgn.setLayoutParams(lp);
		final LinearLayout qzone = (LinearLayout) findViewById(R.id.qzone);
		imgn.setImageResource(R.drawable.zfb);
		qzone.addView(imgn, 0);
		imgn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				qzone.removeView(imgn);
			}
		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.e("s", "creat");
		TC = (TextView) findViewById(R.id.tc);
		TC.setVisibility(View.VISIBLE);

		Button Mybutton = (Button) findViewById(R.id.button1);
		Mybutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// TODO Auto-generated method stub
				if (flag == 1) {
					TC.setVisibility(View.GONE);// 设置文档的显示
					flag = 0;
				} else if (flag == 0) {
					TC.setVisibility(View.VISIBLE);
					flag = 1;
				}
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent intent = getIntent();
				Bundle bundle = intent.getExtras();
				intent.putExtra("back", "Back Data");
				setResult(RESULT_CODE, intent);
				finish();
			}
		});

		ImageView add = (ImageView) findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				addimg();
			}

		});

	}

}
