package com.example.nangsg_tipster;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	String arr[]={"Beer","Juice","Soft-Drink"};
	EditText QuanID;
	EditText VatID;
	EditText ToalID;
	Button SubID;
	Button AddID;
	Button CalID;
	Spinner spinner1;	
	public int price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getControls();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinner1.setAdapter(adapter);
		spinner1.setOnItemSelectedListener(new MyProcessEvent());
		SubID.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String Qua = QuanID.getText().toString();
				int Qa = Integer.parseInt(Qua);
				// TODO Auto-generated method stub
				if (Qa>0)
					Qa--;
				QuanID.setText(Qa+"");				
			}
		});
		AddID.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String Qua = QuanID.getText().toString();
				int Qa = Integer.parseInt(Qua);
				// TODO Auto-generated method stub
				Qa++;
				QuanID.setText(Qa+"");				
			}
		});
	 CalID.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String Qua = QuanID.getText().toString();
			int Qa = Integer.parseInt(Qua);
			//String Vat = VatID.getText().toString();			
			//double Va = Double.parseDouble(Vat);
			// TODO Auto-generated method stub
			int tong = price*Qa;
			double total = tong+tong/10;
			DecimalFormat dfc = new DecimalFormat("#.00");
			String totalx = dfc.format(total);
			ToalID.setText(totalx);
		}
	});
	}
private void getControls()
{
	QuanID = (EditText)findViewById(R.id.QuanID);
	VatID = (EditText)findViewById(R.id.VatID);
	ToalID = (EditText)findViewById(R.id.ToalID);
	SubID = (Button)findViewById(R.id.SubID);
	AddID = (Button)findViewById(R.id.AddID);
	CalID = (Button)findViewById(R.id.CalID);
	spinner1 = (Spinner)findViewById(R.id.spinner1);
	
}
// Class create Events
public class MyProcessEvent implements OnItemSelectedListener {
	

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		switch(arg2)
		{
		case 1:
			price = 20000;
			break;
		case 2:
			price = 17000;
			
			break;
		case 3:
			price = 15000;
			break;		
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
