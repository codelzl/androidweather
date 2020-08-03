package com.example.pfweather.gson;

import com.google.gson.annotations.SerializedName;



public class Now {
    @SerializedName("tmp")
    public String temperature;
    
    
    public String hum;
    

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
    public wind wind;
    
    public class wind{
    	
    	public String dir;
    	public String sc;
    	public String spd;
    }

}
