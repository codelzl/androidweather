package com.example.pfweather.gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;



public class Weather implements Serializable{

    public String status;

    public Alarms alarms;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

    @SerializedName("hourly_forecast")
    public List<Hourly> hourlyList;
}
