package com.hkgiau.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hkgiau.sqlite.R;

public class UserView extends LinearLayout{

	public TextView lb_fullname;
	public TextView lb_email;
	
	public UserView(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.custom_user, this,true);
		lb_fullname= (TextView) findViewById(R.id.lb_name); 
 	    lb_email = (TextView) findViewById(R.id.lb_price);		
	}

}
