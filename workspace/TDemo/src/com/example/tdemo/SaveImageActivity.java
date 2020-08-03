package com.example.tdemo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//http://blog.csdn.net/lanhuzi9999/article/details/31520547
public class SaveImageActivity extends Activity {

    private final static String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/download_test/";  
    private ImageView mImageView;  
    private Button mBtnSave;  
    private Bitmap mBitmap;  
    private String mFileName;  
    private Thread connectThread;  
   protected void onCreate(Bundle savedInstanceState) {  
	        // TODO Auto-generated method stub  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.save);  
	        mImageView = (ImageView) findViewById(R.id.imgSource);  
	        mBtnSave = (Button) findViewById(R.id.btnSave);  
	        connectThread = new Thread(connect);  
	        connectThread.start();  
	        mBtnSave.setOnClickListener(new View.OnClickListener() {  
	  
	            @Override  
	            public void onClick(View arg0) {  
	                try {
						saveFile(mBitmap, mFileName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	            }  
	        });  
	 }
	 
	 private Runnable connect=new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String filePath="http://img.my.csdn.net/uploads/201402/24/1393242467_3999.jpg";
				mFileName="text.jpg";
			
					try {
						mBitmap=BitmapFactory.decodeStream(getImageStream(filePath));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					connectHandler.sendEmptyMessage(0);
					
				} 
				
			};
			
	 private Handler connectHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(mBitmap!=null) mImageView.setImageBitmap(mBitmap);
		}
	 };
		

		 protected InputStream getImageStream(String path)throws Exception{
			 URL url = new URL(path);  
		     HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
		     conn.setConnectTimeout(10 * 1000);  
		        conn.setRequestMethod("GET");  
		        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {  
		            return conn.getInputStream();  
		        }  
		        return null;  
		 
		 }
		 
		 protected void saveFile(Bitmap bm, String fileName) throws IOException {  
		        File dirFile = new File(ALBUM_PATH);  
		        if (!dirFile.exists()) {  
		            dirFile.mkdir();  
		        }  
		        File myCaptureFile = new File(ALBUM_PATH + fileName);  
		        BufferedOutputStream bos = new BufferedOutputStream(  
		                new FileOutputStream(myCaptureFile));  
		        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
		        bos.flush();  
		        bos.close();  
		    }  
	
	
}
