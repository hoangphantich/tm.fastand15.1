package com.hoangphan.tutor0301_hqvinfo;

import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends Activity {

	private static final int FROMI1TOI2 = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		Log.d("Life", "OnCreate");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("Life", "OnDestroy");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("Life", "OnPause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("Life", "OnRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("Life", "OnResume");
		
		Bundle b = getIntent().getExtras();
		TextView infos = (TextView) findViewById(R.id.txtInfo);
		
		//key-value
		if (b != null) {
			Set<String> k = b.keySet();
			for (String str : k) {
				String v = b.getString(str);
				infos.append("\n"+str+" is "+v+".");
			}
		}				
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("Life", "OnStart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("Life", "OnStop");
	}
	
	public void goTest(View v){
		Intent i2 = new Intent("com.hoangphan.tutor0301_hqvinfo.SecondActivity");
		i2.putExtra("helloMsg", "Hello friend");
		i2.putExtra("dayInt", 26);
		startActivity(i2);
	}
	
	public void goAccount(View v){
		Intent i2p = new Intent(FirstActivity.this, SecondActivity.class);
		i2p.putExtra("helloMsg", "Hello my friend");
		
		Bundle b = new Bundle();
		b.putString("helpMsg", "I am helper and is here to help you");
		b.putInt("monthInt", 12);
		b.putStringArray("helpStep", new String[] {"Step account", "Step place", "Step mobile"});
		i2p.putExtras(b);
		startActivityForResult(i2p, FROMI1TOI2);
	}

	//callback
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent i1) {
		//if ok
		//if (RESULT_OK == resultCode) {
			//if (FROMI1TOI2 == requestCode) {
				Log.d("Acti", "Vao day chua");
				String name = i1.getData().toString();
				String age = i1.getStringExtra("age");
				
				TextView txtInfos = (TextView) findViewById(R.id.txtInfo);
				txtInfos.append(name+"  "+age+" years old");
			//}
		//}
	}
}
