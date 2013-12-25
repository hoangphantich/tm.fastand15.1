package com.example.tipster;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	String str[]={"Heniken","Tiger","Ha Noi"};	
	int price[]={20000,15000,12000};
	int lst;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//get object
		final Spinner sp_beer=(Spinner)findViewById(R.id.sp_beer);
		final Button bt_tru=(Button)findViewById(R.id.bt_tru);
		final Button bt_cong=(Button)findViewById(R.id.bt_cong);
		final Button bt_cal=(Button)findViewById(R.id.bt_cal);
		final Button bt_exit=(Button)findViewById(R.id.bt_exit);
		final EditText txt_value=(EditText)findViewById(R.id.txt_value);	
		//final EditText txt_vat=(EditText)findViewById(R.id.txt_vat);
		final EditText txt_total=(EditText)findViewById(R.id.txt_total);
		
		bt_tru.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int a;
				// TODO Auto-generated method stub
				a=Integer.parseInt(txt_value.getText().toString());
				if(a>0)a--;
				txt_value.setText(String.valueOf(a).toString());
			}
		});
		bt_cong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int a;
				// TODO Auto-generated method stub
				a=Integer.parseInt(txt_value.getText().toString())+1;
				txt_value.setText(String.valueOf(a).toString());
			}
		});
		bt_cal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				// TODO Auto-generated method stub
				float total=0;
				int a;				
				a=Integer.parseInt(txt_value.getText().toString());				
				total=(float)(price[lst]*a*1.1);
				txt_total.setText(String.valueOf(total).toString());				
			}
		});
		bt_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		//spinner
		ArrayAdapter<String> adap=new ArrayAdapter<String>
		(this,android.R.layout.simple_spinner_item, str );
		adap.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_beer.setAdapter(adap);
		sp_beer.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this, arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_LONG).show();
				lst=arg2;
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
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
