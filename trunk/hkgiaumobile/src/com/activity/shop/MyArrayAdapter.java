package com.activity.shop;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyArrayAdapter extends ArrayAdapter<Product>{

	Context app;
	ArrayList<Product> list;
	int layout;
	
	public MyArrayAdapter(Activity context, int layoutId, ArrayList<Product> arr) {
		// TODO Auto-generated constructor stub
		super(context, layoutId, arr);
		app = context;
		layout=layoutId;
	    list = (ArrayList<Product>) arr;
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent)
	{
		View productView = convertView;
	    if(null == productView) {
	    	productView = new ProductView(app, layout);
	    }
	    
	    Product product = list.get(position);
	    if(null != product) {
	    	ProductView row=(ProductView) productView;
	    	row.lb_product.setText(product.getProduct());
	    	if(layout==1)
		    {								
				row.lb_price.setText(product.getPrice()+ "$");
				
				//set image
				
				String uri_img = "drawable/" + product.getPicture();
				Resources rs = productView.getContext().getResources();
	            int ImageResoure = rs.getIdentifier(uri_img, null, productView.getContext().getApplicationContext().getPackageName());
	            Drawable image = rs.getDrawable(ImageResoure);
	            row.image.setImageDrawable(image);	
			}
	    	if(layout==2)
	    	{
	    		row.txt_quantity.setText(product.getQuantity()+"");
	    	}
			//set image
				
		}
		return productView;		
	}
}
