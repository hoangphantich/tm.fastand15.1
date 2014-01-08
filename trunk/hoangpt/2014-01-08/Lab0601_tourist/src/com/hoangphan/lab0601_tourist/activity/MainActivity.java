package com.hoangphan.lab0601_tourist.activity;

import java.util.ArrayList;

import com.hoangphan.lab0601_tourist.activity.fragme.MenuHolder;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//detect horizontal vertical
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.linearlo);
        if (savedInstanceState == null) {
            Fragment f = new MenuHolder();
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(layout.getId(), f).commit();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
