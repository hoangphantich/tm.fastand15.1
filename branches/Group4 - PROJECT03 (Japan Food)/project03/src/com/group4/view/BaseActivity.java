package com.group4.view;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.group4.activity.AboutActivity;
import com.group4.activity.R;

public class BaseActivity extends Activity{

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_setting:
			Toast.makeText(this, "000", Toast.LENGTH_LONG).show();
			break;
		case R.id.menu_update:
			Toast.makeText(this, "111", Toast.LENGTH_LONG).show();
			break;
		case R.id.menu_about:
			Intent it=new Intent(getBaseContext(), AboutActivity.class);
			startActivity(it);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}	
}
