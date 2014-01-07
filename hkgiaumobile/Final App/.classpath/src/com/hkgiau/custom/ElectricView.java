package com.hkgiau.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hkgiau.sqlite.CheckOutActivity;
import com.hkgiau.sqlite.R;

public class ElectricView extends LinearLayout{

	public static String STRING_NAME="name";
	public static String STRING_PRICE="price";
	
	public TextView lb_name;
	public TextView lb_price;
	public Button bt_add;

	//custom checkout
	public int price;
	public EditText txt_quantity;
	public TextView lb_total;
	public TextView lb_consumer;
	public Button bt_remove;
	
	public ElectricView(final Context context, int layoutID) {
		// TODO Auto-generated constructor stub
		super(context);
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(layoutID==1)
		{	
			inflater.inflate(R.layout.custom_electric, this,true);
		
			lb_name= (TextView) findViewById(R.id.lb_name); 
	 	    lb_price = (TextView) findViewById(R.id.lb_price);		
	 	    bt_add=(Button)findViewById(R.id.bt_add);	 	    
	 	    
	 	    //On click button
	 	    bt_add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
//					Toast.makeText(context, lb_name.getText(), Toast.LENGTH_LONG).show();
					String st=lb_price.getText().toString();
					String price=st.substring(0, st.length()-4);
					Intent it_checkout=new Intent(context, CheckOutActivity.class);
					it_checkout.putExtra(STRING_NAME, lb_name.getText());					
					it_checkout.putExtra(STRING_PRICE, price);
					context.startActivity(it_checkout);
				}
			});
		}
		else
		{
			inflater.inflate(R.layout.custom_checkout, this,true);
			//custom checkout
	 	    txt_quantity=(EditText)findViewById(R.id.txt_quantity);
	 	    lb_total= (TextView) findViewById(R.id.lb_total); 
	 	    lb_consumer = (TextView) findViewById(R.id.lb_consumer);		
	 	    bt_remove=(Button)findViewById(R.id.bt_remove);	 	    
		}
	}

}
