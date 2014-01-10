package com.group4.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashActivity extends Activity {
	private static final long SPLASH_TIME_OUT = 1000;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_splash);
		
	   new Handler().postDelayed(new Runnable() {
		   @Override
		   public void run() {
			   Intent i = new Intent(SplashActivity.this, MainActivity.class);
			   startActivity(i);
			   finish();
	      }
	    }, SPLASH_TIME_OUT);
	  }	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
