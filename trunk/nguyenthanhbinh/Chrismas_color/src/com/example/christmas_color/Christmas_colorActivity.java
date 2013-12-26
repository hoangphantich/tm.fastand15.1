package com.example.christmas_color;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.graphics.*;

public class Christmas_colorActivity extends Activity {
	
	private Button RedBtn;
	private Button GreenBtn;
	private Button YellowBtn;
	private TextView txt1;
	private RadioButton RedRdb;
	private RadioButton GreenRdb;
	private RadioButton YellowRdb;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_christmas_color);
		
		RedBtn=(Button) findViewById(R.id.RedBtn);
		GreenBtn=(Button) findViewById(R.id.GreenBtn);
		YellowBtn=(Button) findViewById(R.id.YellowBtn);
		RedRdb=(RadioButton) findViewById(R.id.RedRdb);
		GreenRdb=(RadioButton) findViewById(R.id.GreenRdb);
		YellowRdb=(RadioButton) findViewById(R.id.YellowRdb);
		txt1=(TextView) findViewById(R.id.txt1);
		
		RedBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txt1.setBackgroundColor(Color.RED);
			}
		});
		
		GreenBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txt1.setBackgroundColor(Color.GREEN);
			}
		});
		
		YellowBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txt1.setBackgroundColor(Color.YELLOW);
			}
		});
		
		RedRdb.setOnClickListener(new View.OnClickListener() {
			@Override        
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txt1.setBackgroundColor(Color.RED);
			}
		});
		
		GreenRdb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txt1.setBackgroundColor(Color.GREEN);
			}
		});
		
		YellowRdb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txt1.setBackgroundColor(Color.YELLOW);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.christmas_color, menu);
		return true;
	}

}
