package com.example.pfweather.ui;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.pfweather.db.MyDBHelper;
import com.example.pfweather.ui.R;

public class noteEditActivity extends Activity {
	private TextView tv_date;
	private EditText et_content;
	private Button btn_ok;
	private Button btn_cancel;
	MyDBHelper memorydb = new MyDBHelper(noteEditActivity.this);
	public int enter_state = 0;// 0是新建，1是修改
	public String last_content;// 用来获取edittext内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mem_edit);
		InitView();
		
		btn_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = memorydb.getReadableDatabase();
				// 获取edittext内容
				String content = et_content.getText().toString();
				// 标志为0---添加一个新的记录
				if (enter_state == 0) {
					if (!content.equals("")) {
						// 获取此时时刻时间
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
						String dateString = sdf.format(date);

						// 向数据库添加信息
						ContentValues values = new ContentValues();
						values.put("memeory_content", content);
						values.put("memeory_date", dateString);
						db.insert("memory_table", null, values);
						finish();
					} else {
						Toast.makeText(noteEditActivity.this, "请输入你的内容！",
								Toast.LENGTH_SHORT).show();
					}
				}
				// 标志位1--查看并修改一个已有的日志
				else {
					ContentValues values = new ContentValues();
					values.put("memeory_content", content);
					db.update("memory_table", values, "memeory_content = ?",
							new String[] { last_content });
					finish();
				}

			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	
	private void InitView() {
		tv_date = (TextView) findViewById(R.id.tv_date);
		et_content = (EditText) findViewById(R.id.et_content);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
		String dateString = sdf.format(date);
		tv_date.setText("当前日期：" + dateString);

		// 接收内容和id
		Bundle myBundle = this.getIntent().getExtras();
		last_content = myBundle.getString("info");
		enter_state = myBundle.getInt("enter_state");
		et_content.setText(last_content);
		
	}
		
}
