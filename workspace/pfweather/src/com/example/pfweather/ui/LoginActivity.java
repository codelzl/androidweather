package com.example.pfweather.ui;


import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;


public class LoginActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        //设置为无标题栏  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        //设置为全屏模式  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        setContentView(R.layout.login);  
  
        //获取组件  
       
          
        //背景透明度变化3秒内从0.3变到1.0  
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);  
        aa.setDuration(3000);  
      
  
        //创建Timer对象  
        Timer timer = new Timer();  
        //创建TimerTask对象  
        TimerTask timerTask = new TimerTask() {  
            @Override  
            public void run() {  
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);  
                startActivity(intent);  
                finish();  
            }  
        };  
        //使用timer.schedule（）方法调用timerTask，定时3秒后执行run  
        timer.schedule(timerTask, 3000);  
    }  

}
