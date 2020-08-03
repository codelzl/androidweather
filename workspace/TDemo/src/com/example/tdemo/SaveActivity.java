package com.example.tdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class SaveActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save);
		try {
			saveDrawableById(R.drawable.zfb, "zfb.jpg", Bitmap.CompressFormat.JPEG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //存储资源为ID图片
	public void saveDrawableById(int id ,String name,Bitmap.CompressFormat format) throws IOException{
		Drawable drawable=this.getResources().getDrawable(R.drawable.zfb);//资源ID转化为Drawable
		Bitmap bitmap=drawableToBitmap(drawable);
		saveBitmap(bitmap, name, format);
		
	}
    
	//drawable变为bitmap
	public Bitmap drawableToBitmap(Drawable drawable) {
		// TODO Auto-generated method stub
		if(drawable==null) return null;
	    return ((BitmapDrawable)drawable).getBitmap();
	}
	
	//bitmap保存指定路径
	public void saveBitmap(Bitmap bitmap,String name,Bitmap.CompressFormat format) throws IOException{
		File file=new File(Environment.getExternalStorageDirectory(), name);
		
		Log.e("path",""+Environment.getExternalStorageDirectory());
		FileOutputStream out=null;
		try {
			out=new FileOutputStream(file);
			bitmap.compress(format, 100, out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void DownLoadPic(){
		try{
			URL url=new URL("http://www.feizl.com/upload2007/allimg/170811/152T3F62-1.jpg");
		
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.connect();
			int code=conn.getResponseCode();
			if(code==200){
				InputStream is=conn.getInputStream();
			}
			else{
				Log.e("request",""+code);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	
	
}
