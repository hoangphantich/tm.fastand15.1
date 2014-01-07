package com.hkgiau.custom;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ElectricArrayAdapter extends ArrayAdapter<Electric>{

	Context app;
	ArrayList<Electric> list;
	int layoutID;
	
	public ElectricArrayAdapter(Activity context, int layoutId, ArrayList<Electric> arr) {
		// TODO Auto-generated constructor stub
		super(context, layoutId, arr);
		app = context;
	    list = (ArrayList<Electric>) arr;
	    layoutID=layoutId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View electricView = convertView;
	    if(null == electricView) {
	    	electricView = new ElectricView(app,layoutID);
	    }
	    Electric electric=list.get(position);
	    if (null!=electric) {
	    	ElectricView row=(ElectricView) electricView;
	    	if(layoutID==1)
	    	{				
				row.lb_name.setText(electric.getName());
				row.lb_price.setText(electric.getPrice()+ " VND");
	    	}
	    	else
	    	{
	    		row.price=electric.getPrice();
	    		row.txt_quantity.setText(String.valueOf(electric.getQuantity()));
	    		row.lb_total.setText(String.valueOf(electric.getPrice()));
	    		row.lb_consumer.setText(electric.getName());
	    	}
		}
		return electricView;
	}

}
