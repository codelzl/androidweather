package com.example.pfweather.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.pfweather.db.MyDBHelper;
import com.example.pfweather.ui.R;

public class NoteActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {

	private ListView listview;
	private SimpleAdapter simple_adapter;
	private List<Map<String, Object>> dataList;
	private Button addNote;
	private TextView tv_content;
	private ImageView backBtn;
	MyDBHelper memorydb=new MyDBHelper(NoteActivity.this);
	private SQLiteDatabase DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mem_main);

		InitView();
	}

	// 在activity显示的时候更新listview
	@Override
	protected void onStart() {
		super.onStart();
		RefreshNotesList();
	}

	private void InitView() {
		tv_content = (TextView) findViewById(R.id.tv_content);
		listview = (ListView) findViewById(R.id.listview);
		backBtn=(ImageView) findViewById(R.id.title_backcc);
		dataList = new ArrayList<Map<String, Object>>();
		addNote = (Button) findViewById(R.id.btn_editnote);
		DB = memorydb.getReadableDatabase();
		listview.setOnItemClickListener(this);
		listview.setOnItemLongClickListener(this);
		addNote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//添加新的备忘录-state值为0
				Intent intent = new Intent(NoteActivity.this, noteEditActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("info", "");
				bundle.putInt("enter_state", 0);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
			}});
	}

	// 刷新listview界面
	public void RefreshNotesList() {
		// 如果dataList已经有的内容，全部删掉
		// 并且更新simp_adapter
		int size = dataList.size();
		if (size > 0) {
			dataList.removeAll(dataList);
			simple_adapter.notifyDataSetChanged();
		}

		// 从数据库读取信息
		Cursor cursor=memorydb.select("memory_table");
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("memeory_content"));
			String date = cursor.getString(cursor.getColumnIndex("memeory_date"));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tv_content", name);//放于TextView
			map.put("tv_date", date);
			dataList.add(map);
		}
		simple_adapter = new SimpleAdapter(this, dataList, R.layout.mem_item,
				new String[] { "tv_content", "tv_date" }, new int[] {
						R.id.tv_content, R.id.tv_date });
		listview.setAdapter(simple_adapter);
	}

	// 点击listview中某一项item的点击监听事件
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//arg2，在适配器中的位置
		String content = listview.getItemAtPosition(arg2) + "";//点击该ITEM获取全部内容 {tv_content=今天晚上去上网, tv_date=2017 08 25}

	
		String content1 = content.substring(content.indexOf("=") + 1,content.indexOf(","));//截取字符串content内容
		
		Intent myIntent = new Intent(NoteActivity.this, noteEditActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("info", content1);
		bundle.putInt("enter_state", 1);//状态变为修改
		myIntent.putExtras(bundle);
		startActivity(myIntent);
	}

	// 点击listview中某一项长时间的点击事件
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
		Builder builder = new Builder(this);
		builder.setTitle("删除该备忘录");
		builder.setMessage("确认删除吗？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 获取listview中此item中的内容
				
				String content = listview.getItemAtPosition(arg2) + "";
				String content1 = content.substring(content.indexOf("=") + 1,
						content.indexOf(","));
				DB.delete("memory_table", "memeory_content = ?", new String[] { content1 });
				RefreshNotesList();//删除后进去刷新
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.create();
		builder.show();
		return true;
	}
}
