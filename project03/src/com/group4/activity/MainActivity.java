package com.group4.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.group4.foodclass.Food;
import com.group4.view.BaseActivity;

public class MainActivity extends BaseActivity implements OnClickListener{

	public ArrayList<Food> arrFood;
	private View bt_landmark;
	private View bt_restaurant;
	private View bt_entertaimain;
	private View bt_help;
	private View bt_exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//get screen size
//		DisplayMetrics metrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(metrics);
//		int screenHeight = metrics.heightPixels;
//		Toast.makeText(MainActivity.this, screenHeight+"", Toast.LENGTH_LONG).show();
		//Get ui
		bt_landmark=findViewById(R.id.bt_landmark);
		bt_restaurant=findViewById(R.id.bt_restaurant);
		bt_entertaimain=findViewById(R.id.bt_entertaimain);
		bt_help=findViewById(R.id.bt_help);
		bt_exit=findViewById(R.id.bt_exit);
		
		//Key listenner
		bt_landmark.setOnClickListener(this);
		bt_restaurant.setOnClickListener(this);
		bt_entertaimain.setOnClickListener(this);
		bt_help.setOnClickListener(this);
		bt_exit.setOnClickListener(this);	
//		Data dt=new Data("1.xml");
//		arrFood = new ArrayList<Food>();				
//		arrFood=dt.getArrFood();
//		Toast.makeText(MainActivity.this, arrFood.get(0).subject+"", Toast.LENGTH_LONG).show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_landmark:	
			Intent it=new Intent(this, MainTabActivity.class);
			startActivity(it);
			break;
		case R.id.bt_exit:
			System.exit(0);
			break;
		default:
			break;
		}
//		Toast.makeText(this, "", Toast.LENGTH_LONG).show();		
	}	
}
