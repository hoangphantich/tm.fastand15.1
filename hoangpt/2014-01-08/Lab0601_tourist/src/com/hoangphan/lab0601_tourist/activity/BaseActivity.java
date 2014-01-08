package com.hoangphan.lab0601_tourist.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int menuId = item.getItemId();
		switch (menuId) {
		case R.id.action_about:
			Intent about = new Intent(getApplicationContext(), AboutActivity.class);
			startActivity(about);
			break;

		default:
			break;
		}
		
		return false;
	}
}
