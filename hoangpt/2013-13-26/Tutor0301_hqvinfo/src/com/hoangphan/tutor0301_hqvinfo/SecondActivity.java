package com.hoangphan.tutor0301_hqvinfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity {

	private static final int GOTOPLACE = 300;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Intent i2 = getIntent();
		String helloMsg = i2.getStringExtra("helloMsg");
		int dateInt = i2.getIntExtra("dayInt", 0);
		
		_toaster(helloMsg);
		_toaster("Today is "+dateInt);
		
		//get Bundle
		Bundle b = i2.getExtras();
		String helpMsg = b.getString("helpMsg");
		int monthInt = b.getInt("monthInt");
		String[] helpSteps = b.getStringArray("helpStep");
		_toaster(helpMsg);
		_toaster("Month is "+monthInt);
		for (String strStep : helpSteps) {
			_toaster(strStep);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("Life", "Act2 start");
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("Life", "Act2 destroy");
	}

	private void _toaster(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	public void goMainFrom2(View v){
		//ui
		EditText nameTxt = (EditText) findViewById(R.id.txtName);
		EditText ageTxt = (EditText) findViewById(R.id.txtAge);
		
		Intent i1 = new Intent();
		i1.putExtra("name", nameTxt.getText().toString());
		i1.putExtra("age", ageTxt.getText().toString());
		
		//finish
		setResult(RESULT_OK, i1);
		finish();
	}
	
	public void goPlace(View v){
		startActivityForResult(new Intent(this, ThirdActivity.class), GOTOPLACE);
		
	}
}
