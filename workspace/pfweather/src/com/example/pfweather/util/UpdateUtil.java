package com.example.pfweather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.widget.ImageView;

import com.example.pfweather.gson.Weather;
import com.example.pfweather.ui.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UpdateUtil {

	public static void upweImg(String info, ImageView weatherImg) {
		// TODO Auto-generated method stub
		if (info.equals("多云"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_duoyun);
		else if (info.equals("暴雪"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoxue);
		else if (info.equals("暴雨"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoyu);
		else if (info.equals("大暴雨"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
		else if (info.equals("大雪"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_daxue);
		else if (info.equals("大雨"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_dayu);
		else if (info.equals("雷阵雨"))
			weatherImg
					.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
		else if (info.equals("雷阵雨冰雹"))
			weatherImg
					.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
		else if (info.equals("晴"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_qing);
		else if (info.equals("沙尘暴"))
			weatherImg
					.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
		else if (info.equals("特大暴雨"))
			weatherImg
					.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
		else if (info.equals("雾"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_wu);
		else if (info.equals("小雪"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
		else if (info.equals("小雨"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
		else if (info.equals("阴"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_yin);
		else if (info.equals("雨夹雪"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
		else if (info.equals("阵雪"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
		else if (info.equals("阵雨"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
		else if (info.equals("中雪"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
		else if (info.equals("中雨"))
			weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongyu);

	}

	// 转为String类型的串
	public static String getWeString(String urlpath) {

		try {
			URL url = new URL(urlpath);
			HttpURLConnection cn = (HttpURLConnection) url.openConnection();
			cn.setConnectTimeout(5 * 1000);
			cn.setRequestMethod("GET");
			InputStreamReader is = new InputStreamReader(cn.getInputStream());
			BufferedReader bf = new BufferedReader(is);
			String line = bf.readLine().toString();
			bf.close();
			is.close();
			return line;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 通过城市获取对应Weather实体类
	public static Weather UpdateWeather(String city) {
		String cname;

		try {
			String MyKey = "6130363df20b4d1ea928bb07257ace9a";
			cname = URLEncoder.encode(city, "utf-8");
			String url = "https://free-api.heweather.com/v5/weather?city="+ cname + "&key=" + MyKey;
			String jsonStr = UpdateUtil.getWeString(url);
			JsonParser parse = new JsonParser();
			JsonObject jsonObject = (JsonObject) parse.parse(jsonStr);
			JsonArray heWeather = jsonObject.get("HeWeather5").getAsJsonArray();
			if (heWeather.size() == 0) {
				return null;
			} else {
				String weatherStr = heWeather.get(0).toString();
				Weather weather = new Gson().fromJson(weatherStr, Weather.class);
				return weather;

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
