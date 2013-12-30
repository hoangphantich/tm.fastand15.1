package com.hoangphan.tutor0301_hqvinfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ThirdActivity extends Activity {

	private Spinner spnWorkfor;
	private Spinner spnLiveat;
	Intent i1,i2,i3,i4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		//ui
		spnWorkfor = (Spinner) findViewById(R.id.spnWorkfor);
		spnLiveat = (Spinner)  findViewById(R.id.spnLiveat);
		
		//init adapter
		List<String> list = _getCity();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnLiveat.setAdapter(adapter);
		
		i1 = new Intent(this, FirstActivity.class);
		i4 = new Intent(this, FourActivity.class);
		i3 = getIntent(); //red from i2
		
		//put i3 into i1
		i1.putExtras(i3);
	}

	
	String[] cities = {"ha noi", "nha trang", "da nang"};
	private List<String> _getCity() {
		List<String> city = Arrays.asList(cities);
		Collections.shuffle(city);
		return city;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}
	
	public void goMainFrom3(View v){
		String workfor = spnWorkfor.getSelectedItem().toString();
		String liveat = spnLiveat.getSelectedItem().toString();
		
		i1.putExtra("workfor", workfor);
		i1.putExtra("liveat", liveat);
		//key-value name,age, workfor,liveat
		startActivity(i1);
	}
	
	public void goMobile(View v){
		startActivity(i4);
	}
}
