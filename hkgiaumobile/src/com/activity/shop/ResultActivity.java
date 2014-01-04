package com.activity.shop;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity {
	
	ListView ls_result;
	TextView lb_total;
	MyArrayAdapter myarr;
	int total=0;
	final ArrayList<Product> arr=new ArrayList<Product>();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Product data = (Product) getIntent().getSerializableExtra("product");
		//get ui
		ls_result=(ListView)findViewById(R.id.ls_result);
		lb_total=(TextView)findViewById(R.id.lb_total);
		
		arr.add(data);		
		myarr=new MyArrayAdapter(this, 2, arr);
		ls_result.setAdapter(myarr);		
		
		//change quantity
//		ls_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) 
//			{
//				// TODO Auto-generated method stub
//				final ProductView productV = new ProductView(view.getContext(), 2);
//				TextView txt_quantity=productV.txt_quantity;
//				txt_quantity.setEnabled(true);
//				txt_quantity.setFocusable(true);
//				txt_quantity.setOnKeyListener(new OnKeyListener() {
//					
//					@Override
//					public boolean onKey(View v, int keyCode, KeyEvent event) {
//						// TODO Auto-generated method stub
//							Product product=new Product();
//							product=arr.get(position);							
//							product.setQuantity(Integer.valueOf(productV.txt_quantity.getText().toString()));
//							arr.set(position, product);
//							myarr.notifyDataSetChanged();
//							Toast.makeText(ResultActivity.this, position+"", Toast.LENGTH_LONG).show();	
//							//total
//							lb_total.setText(total()+" $");
//						return false;
//					}
//				});				
//			}
//		});
		
		
//		Toast.makeText(ResultActivity.this, data.getProduct(), Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		SharedPreferences pre=getSharedPreferences("mydata", MODE_PRIVATE);
		//save list
		SharedPreferences.Editor editor = pre.edit();
		if(!arr.isEmpty())
		{
			editor.putInt("length", arr.size());
			for(int i=0; i<arr.size();i++)
			{
				editor.putString("product"+i, arr.get(i).getProduct());
				editor.putInt("quantity"+i, arr.get(i).getQuantity());
				editor.putInt("price"+i, arr.get(i).getPrice());				
			}
			editor.commit();
		}
//		Toast.makeText(ResultActivity.this, arr.size()+"", Toast.LENGTH_LONG).show();	
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		

		SharedPreferences pre=getSharedPreferences("mydata", MODE_PRIVATE);
		//restore list
		int length = pre.getInt("length", -1);
		if (length != -1) 
		{
			for(int i=0; i<length;i++)
			{
				Product product=new Product();
				product.setProduct(pre.getString("product"+i, null));
				product.setQuantity(pre.getInt("quantity"+i, -1));
				product.setPrice(pre.getInt("price"+i, -1));		

				//check item in list
				if(arr.get(0).getProduct().compareTo(product.getProduct())==0)
				{						
					product.setQuantity(product.getQuantity()+1);
					arr.set(0, product);
				}
				else
					arr.add(product);
//				Toast.makeText(ResultActivity.this, flag+"", Toast.LENGTH_LONG).show();
				myarr.notifyDataSetChanged();
			}			
		}
		//total
		total=total();
		lb_total.setText(total+" $");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	public int total()
	{
		int total=0;
		for(int i=0; i<arr.size();i++)
		{
			total+=arr.get(i).getPrice()*arr.get(i).getQuantity();
		}
		return total;
	}
	public void ContinueBuy(View view)
	{
		finish();
	}
	public void Pay(View view)
	{
		Intent itpay=new Intent(this, PayActivity.class);
		itpay.putExtra("pay", total);
		startActivity(itpay);
		finish();		
	}
}
