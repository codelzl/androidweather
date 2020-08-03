package com.example.pfweather.ui;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;

import com.example.pfweather.db.MyDBHelper;
import com.example.pfweather.gson.Weather;
import com.example.pfweather.util.DateUtil;
import com.example.pfweather.util.UpdateUtil;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements AMapLocationListener {

	private ImageView CityBtn, LocateBtn, UpdateBtn, ShareBtn, CalBtn,
			weatherImg, todayImg, afterImg, lateImg, late1Img, late2Img,
			late3Img;
	private TextView cnameTv, uptimeTv, humidityTv, pm25Tv, pm25statusTv,
			tmpTv, weatherTv, windTv, weekTv, comfTv, drsgTv, fluTv, sportTv,
			uvTv, todayTv, afterTv, lateTv, late1Tv, late2Tv, late3Tv, time1Tv,
			time2Tv, time3Tv;
	Weather weather;

	public String cname = "呼和浩特";
	public AMapLocationClient mLocationClient = null;

	public MyDBHelper citydb = new MyDBHelper(this);
	public MyDBHelper memorydb = new MyDBHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weathermain_activity);
		initview();//初始化界面
		Cursor cursor = citydb.select("city_table");
		if (cursor.moveToLast()) {
			cursor.moveToLast();
			cname = cursor.getString(cursor.getColumnIndex("city_name"));
		}

		CalBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CalendarActivity.class);
				startActivity(intent);
			}
		});
		LocateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				initlocate();
				new Thread(runnable).start();//新起一个线程下载更新
				Toast t3 = Toast.makeText(MainActivity.this, "更新成功",Toast.LENGTH_LONG);
				t3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
				t3.setMargin(0f, 0.6f);
				t3.show();

			}
		});
		CityBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CityActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		UpdateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread(runnable).start();
				Toast t3 = Toast.makeText(MainActivity.this, "更新成功",Toast.LENGTH_SHORT);
				t3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
				t3.setMargin(0f, 0.6f);
				t3.show();
			}
		});
		ShareBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				shareIntent.putExtra(Intent.EXTRA_TEXT, "我在使用完美天气，欢迎你也来使用！！！");
				shareIntent.setType("text/plain");

				// 设置分享列表的标题，并且每次都显示分享列表
				startActivity(Intent.createChooser(shareIntent, "分享到"));

			}
		});

		new Thread(runnable).start();

	}
	
	public void initview(){
		CityBtn = (ImageView) findViewById(R.id.title_city);
		LocateBtn = (ImageView) findViewById(R.id.title_locate);
		LocateBtn = (ImageView) findViewById(R.id.title_locate);
		UpdateBtn = (ImageView) findViewById(R.id.title_update);
		ShareBtn = (ImageView) findViewById(R.id.title_share);
		CalBtn = (ImageView) findViewById(R.id.title_calendar);
		cnameTv = (TextView) findViewById(R.id.todayinfo1_cityName);
		uptimeTv = (TextView) findViewById(R.id.todayinfo1_updateTime);
		humidityTv = (TextView) findViewById(R.id.todayinfo1_humidity);
		pm25Tv = (TextView) findViewById(R.id.todayinfo1_pm25);
		pm25statusTv = (TextView) findViewById(R.id.todayinfo1_pm25status);
		tmpTv = (TextView) findViewById(R.id.todayinfo2_temperature);
		weatherTv = (TextView) findViewById(R.id.todayinfo2_weatherState);
		windTv = (TextView) findViewById(R.id.todayinfo2_wind);
		weekTv = (TextView) findViewById(R.id.todayinfo2_week);
		weatherImg = (ImageView) findViewById(R.id.todayinfo2_weatherStatusImg);
		comfTv = (TextView) findViewById(R.id.todayinfo3_comf);
		drsgTv = (TextView) findViewById(R.id.todayinfo3_drsg);
		fluTv = (TextView) findViewById(R.id.todayinfo3_flu);
		sportTv = (TextView) findViewById(R.id.todayinfo3_sport);
		uvTv = (TextView) findViewById(R.id.todayinfo3_uv);
		todayTv = (TextView) findViewById(R.id.todayinfo4_todayinf);
		afterTv = (TextView) findViewById(R.id.todayinfo4_afterinf);
		lateTv = (TextView) findViewById(R.id.todayinfo4_lateinf);
		todayImg = (ImageView) findViewById(R.id.todayinfo4_todayimg);
		afterImg = (ImageView) findViewById(R.id.todayinfo4_afterimg);
		lateImg = (ImageView) findViewById(R.id.todayinfo4_lateimg);
		late1Tv = (TextView) findViewById(R.id.todayinfo5_todayinf);
		late2Tv = (TextView) findViewById(R.id.todayinfo5_afterinf);
		late3Tv = (TextView) findViewById(R.id.todayinfo5_lateinf);
		late1Img = (ImageView) findViewById(R.id.todayinfo5_todayimg);
		late2Img = (ImageView) findViewById(R.id.todayinfo5_afterimg);
		late3Img = (ImageView) findViewById(R.id.todayinfo5_lateimg);
		time1Tv = (TextView) findViewById(R.id.todayinfo5_today);
		time2Tv = (TextView) findViewById(R.id.todayinfo5_after);
		time3Tv = (TextView) findViewById(R.id.todayinfo5_late);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		cname = data.getStringExtra("data2");
		new Thread(runnable).start();
		Toast t3 = Toast.makeText(MainActivity.this,"更新成功" + data.getStringExtra("data2"), Toast.LENGTH_LONG);
		t3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
		t3.setMargin(0f, 0.6f);
		t3.show();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle data = msg.getData();
			Weather w = (Weather) data.getSerializable("value");
			//Log.i("mylog", "请求城市结果-->" + w.basic.cityName);
			citydb.city_insert(cname);
			updateView(w);
		}
	};

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			Message msg = new Message();
			Bundle data = new Bundle();
			data.putSerializable("value", UpdateUtil.UpdateWeather(cname));
			msg.setData(data);
			handler.sendMessage(msg);
		}
	};

	public void updateView(Weather weather) {
		cnameTv.setText(weather.basic.cityName);
		humidityTv.setText("湿度" + weather.now.hum + "%");
		try {
			pm25Tv.setText("当前指数" + weather.aqi.city.pm25);
			pm25statusTv.setText("空气质量" + weather.aqi.city.qlty);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tmpTv.setText("当前温度" + weather.now.temperature + "°");
		weatherTv.setText(weather.now.more.info);
		uptimeTv.setText("发布于 " + weather.basic.update.loc);
		windTv.setText(weather.now.wind.dir + " " + weather.now.wind.sc + "~"+ weather.now.wind.spd);
		weekTv.setText("今天 " + DateUtil.getWeek(0, DateUtil.XING_QI));
		comfTv.setText("" + weather.suggestion.comfort.info);
		drsgTv.setText("" + weather.suggestion.clothes.info);
		fluTv.setText("" + weather.suggestion.cold.info);
		sportTv.setText("" + weather.suggestion.sport.info);
		uvTv.setText("" + weather.suggestion.uv.info);
		todayTv.setText("   " + weather.now.more.info + "  "
				+ weather.forecastList.get(0).temperature.min + "°~"
				+ weather.forecastList.get(0).temperature.max + "°");
		afterTv.setText("   " + weather.now.more.info + "  "
				+ weather.forecastList.get(1).temperature.min + "°~"
				+ weather.forecastList.get(1).temperature.max + "°");
		lateTv.setText("   " + weather.now.more.info + "  "
				+ weather.forecastList.get(1).temperature.min + "°~"
				+ weather.forecastList.get(2).temperature.max + "°");
		UpdateUtil.upweImg(weather.now.more.info, weatherImg);
		UpdateUtil.upweImg(weather.now.more.info, todayImg);
		UpdateUtil.upweImg(weather.now.more.info, afterImg);
		UpdateUtil.upweImg(weather.now.more.info, lateImg);
		try {
			late1Tv.setText(weather.hourlyList.get(0).cond.txt + "~"
					+ weather.hourlyList.get(0).tmp + "°");
			late2Tv.setText(weather.hourlyList.get(1).cond.txt + "~"
					+ weather.hourlyList.get(1).tmp + "°");
			late3Tv.setText(weather.hourlyList.get(2).cond.txt + "~"
					+ weather.hourlyList.get(2).tmp + "°");

			String late1 = new String(weather.hourlyList.get(0).date);
			String a[] = late1.split(" ");
			time1Tv.setText(a[1]);
			String late2 = new String(weather.hourlyList.get(1).date);
			String b[] = late2.split(" ");
			time2Tv.setText(b[1]);
			String late3 = new String(weather.hourlyList.get(2).date);
			String c[] = late3.split(" ");
			time3Tv.setText(c[1]);

			UpdateUtil.upweImg(weather.hourlyList.get(0).cond.txt, late1Img);
			UpdateUtil.upweImg(weather.hourlyList.get(1).cond.txt, late2Img);
			UpdateUtil.upweImg(weather.hourlyList.get(2).cond.txt, late3Img);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//初始化定位函数
	public void initlocate() {
		// 启动定位
		mLocationClient = new AMapLocationClient(getApplicationContext());
		mLocationClient.setLocationListener(this);
		AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
		// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
		mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// 设置定位间隔,单位毫秒,默认为2000ms
		mLocationOption.setInterval(2000);
		mLocationOption.setOnceLocation(true);
		mLocationClient.setLocationOption(mLocationOption);
		// 设置定位参数

		mLocationClient.startLocation();
	}

	//定位回调函数，手动进行实现
	@Override
	public void onLocationChanged(AMapLocation location) {

		cname = location.getCity();
		new Thread(runnable).start();
		Weather newweather = UpdateUtil.UpdateWeather(location.getCity());
		updateView(newweather);
		mLocationClient.stopLocation();
	}

}
