package com.example.pfweather.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfweather.datepicker.MonthDateView;
import com.example.pfweather.datepicker.MonthDateView.DateClick;
import com.example.pfweather.db.MyDBHelper;
import com.example.pfweather.ui.R;

public class CalendarActivity extends FragmentActivity {
	private ImageView iv_left, iv_right, backBtn, plusBtn;
	private TextView tv_date, tv_week, tv_today;
	private MonthDateView monthDateView;
	private LinearLayout mem_lin;
	private Button save_Btn;
	private EditText mem_edit;
	private String newmonth, newday;
	MyDBHelper memorydb = new MyDBHelper(CalendarActivity.this);
	String mem_data = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date);
	   
		initcal();

		//点加号跳转到增加备忘录界面
		plusBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(CalendarActivity.this, NoteActivity.class);
				startActivity(intent);
			}
		});
		// 插入保存任意日期的备忘录
		save_Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String smemtext = mem_edit.getText().toString();
				memorydb.mem_insert(smemtext, mem_data);
				Toast.makeText(getApplication(), "添加备忘事件：" + smemtext,
						Toast.LENGTH_SHORT).show();

			}
		});

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				finish();

			}
		});

		// 设置点击任意日期对应的事件响应
		monthDateView.setDateClick(new DateClick() {

			@Override
			public void onClickOnDate() {

				mem_lin.setVisibility(1);
				mem_edit.setText(null);

				if (monthDateView.getmSelMonth() < 10) {
					newmonth = "0" + (monthDateView.getmSelMonth() + 1);
				} else {
					newmonth = "" + (monthDateView.getmSelMonth() + 1);
				}
				if (monthDateView.getmSelDay() < 10) {
					newday = "0" + monthDateView.getmSelDay();
				} else {
					newday = "" + monthDateView.getmSelDay();
				}
				mem_data = monthDateView.getmSelYear() + " " + newmonth + " "
						+ newday;

				// 查询点击日期对应的content
				SQLiteDatabase db = memorydb.getReadableDatabase();
				Cursor mem_cursor = db.query("memory_table", new String[] {
						"memeory_content", "memeory_date" }, "memeory_date=?",
						new String[] { mem_data }, null, null, null);

				while (mem_cursor.moveToNext()) {
					String name = mem_cursor.getString(mem_cursor
							.getColumnIndex("memeory_content"));
					mem_edit.setText(name);
				}

			}

		});
		setOnlistener();

	}
	
	private void initcal(){
		plusBtn = (ImageView) findViewById(R.id.plus);
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
		tv_date = (TextView) findViewById(R.id.date_text);
		tv_week = (TextView) findViewById(R.id.week_text);
		tv_today = (TextView) findViewById(R.id.tv_today);
		monthDateView.setTextView(tv_date, tv_week);
		backBtn = (ImageView) findViewById(R.id.title_backd);
		mem_lin = (LinearLayout) findViewById(R.id.mem_linear);
		save_Btn = (Button) findViewById(R.id.Sbutton);
		mem_edit = (EditText) findViewById(R.id.mem_text);
	}

	private void setOnlistener() {
		iv_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.onLeftClick();
			}
		});

		iv_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.onRightClick();
			}
		});

		tv_today.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.setTodayToView();

			}
		});
	}

}
