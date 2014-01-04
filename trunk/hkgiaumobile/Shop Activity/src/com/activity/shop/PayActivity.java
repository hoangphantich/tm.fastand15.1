package com.activity.shop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		Intent itpay=getIntent();
		int a=itpay.getIntExtra("pay", -1);
		TextView txt_payment=(TextView)findViewById(R.id.txt_payment);
		txt_payment.setText(a + " $");
//		Toast.makeText(PayActivity.this, a+"", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pay, menu);
		return true;
	}

	public void NewBuy(View view)
	{
		//clear all data
		SharedPreferences pre=getSharedPreferences("mydata", MODE_PRIVATE);
		SharedPreferences.Editor editor=pre.edit();
		editor.clear();
		editor.commit();
		finish();
	}
}
