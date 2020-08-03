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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AsyncActivity extends Activity {
	
	   private ImageView mImageView;  
	    private Button mBtnSave;  
	    private Button mBtndown; 
	    private Bitmap mBitmap;  
	    private final static String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/download_test/";  
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async);  
		   mImageView = (ImageView) findViewById(R.id.imgSource);  
	        mBtnSave = (Button) findViewById(R.id.btnSave);  
	        mBtndown = (Button) findViewById(R.id.btndown);  
		
	        mBtnSave.setOnClickListener(new View.OnClickListener() {  
	      	  
	            @Override  
	            public void onClick(View arg0) {  
	                downLoadPictue();  
	            }  
	        });  
	        mBtndown.setOnClickListener(new View.OnClickListener() {  
	      	  
	            @Override  
	            public void onClick(View arg0) {  
	                try {
						saveFile(mBitmap, "myfile");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	            }  
	        });  
		
	}


	void downLoadPictue(){
		MyAsyncTask asyncTask = new MyAsyncTask();
	    asyncTask.execute("http://img.my.csdn.net/uploads/201402/24/1393242467_3999.jpg");
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
	
	class MyAsyncTask extends AsyncTask<String , Integer, Bitmap> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			
		        
		}
		
		@Override
		protected Bitmap doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Bitmap bitmap=null;
			try{
			 URL url = new URL(arg0[0]);
	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	         InputStream inputStream = conn.getInputStream();
	         bitmap = BitmapFactory.decodeStream(inputStream);
	         inputStream.close();
		   } catch (Exception e) {
	          e.printStackTrace();
	       
	       }
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			ImageView imageView =(ImageView)findViewById(R.id.imgSource);
		     imageView.setImageBitmap(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		

	}


}
