package com.example.pfweather.util;


import java.util.Calendar;
import java.util.TimeZone;

import android.util.Log;

public class DateUtil {

	private static final String[] WEEK = { "日", "一", "二", "三", "四", "五", "六" };
	public static final String XING_QI = "星期";
	public static final String ZHOU = "周";

	//返回星期几
	public static String getWeek(int num, String format) {
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		int weekNum = c.get(Calendar.DAY_OF_WEEK) + num;
		if (weekNum > 7)
			weekNum = weekNum - 7;
		return format + WEEK[weekNum - 1];
	}
	
}
