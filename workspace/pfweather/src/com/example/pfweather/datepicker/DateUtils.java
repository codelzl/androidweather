package com.example.pfweather.datepicker;

import java.util.Calendar;

import android.util.Log;

public class DateUtils {
	
	// 通过年份和月份 得到当月的日子数目
	public static int getMonthDays(int year, int month) {
		month++;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
				return 29;
			} else {
				return 28;
			}
		default:
			return -1;
		}
	}

	// 返回当前月份1号位于周几
	public static int getFirstDayWeek(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}
