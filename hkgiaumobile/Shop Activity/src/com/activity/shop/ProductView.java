package com.activity.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductView extends LinearLayout{
	  public TextView lb_product;
	  public TextView lb_price;
	  public EditText txt_quantity;
	  public ImageView image;
	  
	  public ProductView(Context context, int layoutId) {
	    super(context);
	    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    if(layoutId==1)
    	{
	    	inflater.inflate(R.layout.list_custom, this, true);	 
	    	lb_product = (TextView) findViewById(R.id.lb_product); 
	 	    lb_price = (TextView) findViewById(R.id.lb_price); 
	 	    image=(ImageView)findViewById(R.id.imageView);
    	}
	    if(layoutId==2)
    	{
	    	inflater.inflate(R.layout.result_custom, this,true);
	    	lb_product=(TextView)findViewById(R.id.lb_product);
	    	txt_quantity=(EditText)findViewById(R.id.txt_quantity);
    	}    
	   
	  }
}
