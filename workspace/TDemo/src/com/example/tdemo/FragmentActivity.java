package com.example.tdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FragmentActivity extends Activity implements OnClickListener {
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.fragment_main);
	        Button button = (Button) findViewById(R.id.button);
	        button.setOnClickListener(this);
	    }
	 
	 @SuppressLint("NewApi")
	@Override
	    public void onClick(View v) {
	        switch (v.getId()) {
	        case R.id.button:
	            RightFragmentB fragment = new RightFragmentB();
	            android.app.FragmentManager fragmentManager = getFragmentManager();
	            FragmentTransaction transaction = fragmentManager. beginTransaction();
	            transaction.replace(R.id.right_layout, fragment);
	            transaction.commit();
	            break;
	        default:
	            break;
	        }
	    }
}
