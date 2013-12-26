package com.example.banhang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
Button giam, tang, ketqua;
TextView tong, soluong;
Spinner spinner1;

long giatien;
int them=1;
private String mang[]={"Banh","Thuoc","Nuoc","cofe","Ruou","Hoa qua"};
ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		giam=(Button)findViewById(R.id.giam);
		tang=(Button)findViewById(R.id.tang);
		ketqua=(Button)findViewById(R.id.ketqua);
		tong=(TextView)findViewById(R.id.tong);
		soluong=(TextView)findViewById(R.id.soluong);
		spinner1=(Spinner)findViewById(R.id.spinner1);
		
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mang);
		spinner1.setAdapter(adapter);
		
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				soluong.setText("1");
				if (arg2==0)
				{
					giatien=7000;
				}
				if (arg2==1)
				{
					giatien=20000;
				}	
				if (arg2==2)
				{
					giatien=1200;
				}	
				if (arg2==3)
				{
					giatien=24000;
				}
				if (arg2==4)
				{
					giatien=30000;
				}
				if (arg2==5)
				{
					giatien=22000;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		giam.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (them>1)
				{
					them=them-1;
					soluong.setText(String.valueOf(them));	
					giatien=giatien*them;
				}
			}
		});
		
		tang.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				them=them+1;
			soluong.setText(String.valueOf(them));	
			giatien=giatien*them;
			}
		});
		
		ketqua.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				giatien=(long) (giatien*1.01);
				tong.setText(String.valueOf(giatien));			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
