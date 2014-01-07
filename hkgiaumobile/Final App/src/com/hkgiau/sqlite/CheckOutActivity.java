package com.hkgiau.sqlite;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hkgiau.custom.Electric;
import com.hkgiau.custom.ElectricArrayAdapter;
import com.hkgiau.custom.ElectricView;

public class CheckOutActivity extends ClassActivity {
	
	private ArrayList<Electric> arr;
	private Electric electric;
	private ListView ls_cart;
	private TextView txt_result;
	private TextView txt_vat;
	private TextView txt_pay;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_out);
		//get ui
		ls_cart=(ListView)findViewById(R.id.ls_cart);
		txt_result=(TextView)findViewById(R.id.txt_result);
		txt_vat=(TextView)findViewById(R.id.txt_vat);
		txt_pay=(TextView)findViewById(R.id.txt_pay);
		arr=new ArrayList<Electric>();
		
		//get data
		Intent it=getIntent();
		electric=new Electric(it.getStringExtra(STRING_NAME),Integer.parseInt(it.getStringExtra(STRING_PRICE)));
		electric.setQuantity(1);
		arr.add(electric);
//		Toast.makeText(this, it.getStringExtra(STRING_PRICE), Toast.LENGTH_LONG).show();
		
		//set to list		
		ElectricArrayAdapter myarr=new ElectricArrayAdapter(this, 2, arr);
		ls_cart.setAdapter(myarr);
		setData(arr.get(0).getPrice());		
		
		ElectricView elView=new ElectricView(ls_cart.getContext(),2);
//		int position= ls_cart.getPositionForView((View) elView.getParent());
		elView.txt_quantity.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				Toast.makeText(v.getContext(), String.valueOf(arr.get(0).getQuantity()), Toast.LENGTH_LONG).show();
				return false;
			}
		});
//		Toast.makeText(this, String.valueOf(arr.get(0).getQuantity()), Toast.LENGTH_LONG).show();
	}

	public void setData(int price) {
		// TODO Auto-generated method stub
		int result=price*arr.get(0).getQuantity();
		double vat=result*0.1;
		double pay=result+vat;
		txt_result.setText(String.valueOf(result));
		txt_vat.setText(String.valueOf(vat));
		txt_pay.setText(String.valueOf(pay));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.getItem(2).setEnabled(false);
		return true;
	}

	public void PrintInvoice(View view)
	{
		
	}
}
