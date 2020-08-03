package com.example.tdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressLint("NewApi")
public class ContactActivity extends Activity {

	private ContentResolver cr;
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	private SimpleAdapter simpleadapater;
	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_main);
		listview = (ListView) findViewById(R.id.ls_contacts);
		cr = getContentResolver();
		simpleadapater = new SimpleAdapter(this, data, R.layout.contact_item,
				new String[] { "name", "phone" }, new int[] {
						R.id.tv_item_list_name, R.id.tv_item_list_number });
		listview.setAdapter(simpleadapater);
	}

	public void getContacts(View view) {
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Cursor cs = cr.query(uri, null, null, null, null, null);
		while (cs.moveToNext()) {
			// 拿到联系人id 跟name
			int id = cs.getInt(cs.getColumnIndex("_id"));
			String name = cs.getString(cs.getColumnIndex("display_name"));
			// 得到这个id的所有数据（data表）
			Uri uri1 = Uri.parse("content://com.android.contacts/raw_contacts/"
					+ id + "/data");
			Cursor cs2 = cr.query(uri1, null, null, null, null, null);
			Map<String, Object> maps = new HashMap<String, Object>();// 实例化一个map
			while (cs2.moveToNext()) {
				// 得到data这一列 ，包括很多字段
				String data1 = cs2.getString(cs2.getColumnIndex("data1"));
				// 得到data中的类型
				String type = cs2.getString(cs2.getColumnIndex("mimetype"));
				String str = type.substring(type.indexOf("/") + 1,
						type.length());// 截取得到最后的类型
				if ("name".equals(str)) {// 匹配是否为联系人名字
					maps.put("name", data1);
				}
				if ("phone_v2".equals(str)) {// 匹配是否为电话
					maps.put("phone", data1);
				}
				Log.i("test", data1 + "       " + type);
			}
			data.add(maps);// 将map加入list集合中
		}
		simpleadapater.notifyDataSetChanged();// 通知适配器发生数据改变
		Button button = (Button) view;// 得到button
		button.setEnabled(false);// 设置不可点击
	}

}
