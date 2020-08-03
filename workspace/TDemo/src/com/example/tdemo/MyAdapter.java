package com.example.tdemo;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	static class ViewHolder {
		public ImageView img;
		public TextView name;
		public TextView mes;
		public TextView ChatDate;
		public Button viewBtn;  
		
	}
	public List<Map<String, Object>> data;
	private LayoutInflater mInflater = null;

	public MyAdapter(Context context,List<Map<String, Object>> data) {
		this.data = data;
		this.mInflater =LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertview, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertview==null){
			holder=new ViewHolder();
			convertview = mInflater.inflate(R.layout.chat_item, null); // 这里确定你listview里面每一个item的layout,加载布局
			holder.img = (ImageView) convertview.findViewById(R.id.img); // 此处是将内容与控件绑定。
			holder.name = (TextView) convertview.findViewById(R.id.t1);// 注意：此处的findVIewById前要加convertView.
			holder.mes = (TextView) convertview.findViewById(R.id.t2);
			holder.ChatDate = (TextView) convertview.findViewById(R.id.t3);
			convertview.setTag(holder);
		}
		else{
			holder=(ViewHolder) convertview.getTag();
		}
		holder.img.setBackgroundResource((Integer) data.get(position).get("img"));
		holder.name.setText((String)data.get(position).get("name"));
		holder.mes.setText((String)data.get(position).get("mes"));
		holder.ChatDate.setText((String)data.get(position).get("ChatDate"));
		return convertview;
	}
}
