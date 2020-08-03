package com.example.pfweather.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;

import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class CityActivity extends Activity {
	private ImageView BackBtn;
	private TextView CityName;

	private String[] sprovience;
	private String[] scity;
	private String[] sarea;

	// 列表选择的省市区
	private String selectedPro = "";
	private String selectedCity = "";
	private String selectedArea = "";
	
	private Spinner sPro;
	private Spinner sCity;
	private Spinner sArea;

	private ArrayAdapter<String> mProvinceAdapter;
	private ArrayAdapter<String> mCityAdapter;
	private ArrayAdapter<String> mAreaAdapter;

	// 存省对应的所有市
	private Map<String, String[]> mCitiesDataMap = new HashMap<String, String[]>();
	// 存市对应的所有区
	private Map<String, String[]> mAreaDataMap = new HashMap<String, String[]>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citymanage);
		CityName = (TextView) findViewById(R.id.title_cityname);
		sPro = (Spinner) findViewById(R.id.spro);
		sCity = (Spinner) findViewById(R.id.scity);
		sArea = (Spinner) findViewById(R.id.sarea);
		BackBtn = (ImageView) findViewById(R.id.title_back);
		
		//点返回时，地区不空-则返回地区-否则返回城市
		BackBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				if (!selectedArea.equals(""))
					intent.putExtra("data2", selectedArea);
				else
					intent.putExtra("data2", selectedCity);
				setResult(1, intent);
				CityActivity.this.finish();
			}
		});

		parsingJson(initData());
		//初始化spinner
		mProvinceAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sprovience);
		mProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sPro.setAdapter(mProvinceAdapter);

		//选择省份之后Spinner
		sPro.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				selectedPro = "";
				selectedPro = (String) arg0.getSelectedItem();
				updateCity(selectedPro);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		
		//选择城市之后Spinner
		sCity.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				selectedCity = "";
				selectedCity = (String) arg0.getSelectedItem();
				updateArea(selectedCity);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		//选择地区之后Spinner
		sArea.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				selectedArea = "";
				selectedArea = (String) arg0.getSelectedItem();
				CityName.setText("已选择：" + selectedPro + selectedCity + selectedArea);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

		//传入省份，更新该省份下的城市信息
		private void updateCity(String pro) {

			String[] cities = mCitiesDataMap.get(pro);
			for (int i = 0; i < cities.length; i++) {
				
				mCityAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cities);
				mCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sCity.setAdapter(mCityAdapter);
				mCityAdapter.notifyDataSetChanged();
				sCity.setSelection(0);
			}

		}
		
	//传入城市，更新对应城市下的地区
	private void updateArea(String city) {
		String[] areas = mAreaDataMap.get(city);

		if (areas != null) {
			// 存在区
			sArea.setVisibility(View.VISIBLE);
			mAreaAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, areas);
			mAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sArea.setAdapter(mAreaAdapter);
			mAreaAdapter.notifyDataSetChanged();
			sArea.setSelection(0);
		} else {
			CityName.setText("已选择: " + selectedPro + selectedCity);
			sArea.setVisibility(View.GONE);
		}
	}

	

	// 初始化读取city.json为String
	private String initData() {
		StringBuffer sb = new StringBuffer();
		AssetManager assetmanager = this.getAssets();
		try {
			InputStream is = assetmanager.open("city.json");
			byte[] data = new byte[is.available()];
			int len = -1;
			while ((len = is.read(data)) != -1) {
				sb.append(new String(data, 0, len, "gb2312"));
			}
			is.close();
			return sb.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// JSON解析 jsonarray[] object的集合     jsonobject{}
	private void parsingJson(String cityJson) {
		if (!TextUtils.isEmpty(cityJson)) {
			try {
				JSONObject object = new JSONObject(cityJson);
				JSONArray array = object.getJSONArray("citylist");
				sprovience = new String[array.length()];
				String mprovience = null;
				for (int i = 0; i < array.length(); i++) {
					JSONObject mProvienceObject = array.getJSONObject(i);
					if (mProvienceObject.has("p")) {
						mprovience = mProvienceObject.getString("p");//取省份具体值
						sprovience[i] = mprovience;
					} else {
						sprovience[i] = "未知地点";
					}

					JSONArray cityArray;
					try {
						cityArray = mProvienceObject.getJSONArray("c");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						continue;
					}
					scity = new String[cityArray.length()];
					String mcity = null;
					for (int j = 0; j < cityArray.length(); j++) {
						JSONObject mCityObject = cityArray.getJSONObject(j);
						if (mCityObject.has("n")) {
							mcity = mCityObject.getString("n");
							scity[j] = mcity;//存储具体城市
						} else {
							scity[j] = "未知地点";
						}

						JSONArray areaArray;
						try {
							areaArray = mCityObject.getJSONArray("a");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							continue;
						}
						sarea = new String[areaArray.length()];

						for (int k = 0; k < areaArray.length(); k++) {
							JSONObject mAreaObject = areaArray.getJSONObject(k);
							if (mAreaObject.has("s")) {
								sarea[k] = mAreaObject.getString("s");

							} else {
								sarea[k] = "未知地点";
							}
						}
						mAreaDataMap.put(mcity, sarea);
					}

					mCitiesDataMap.put(mprovience, scity);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
