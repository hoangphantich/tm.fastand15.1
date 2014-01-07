package com.hkgiau.custom;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class UserArrayAdapter extends ArrayAdapter<User>{

	Context app;
	ArrayList<User> list;
	
	public UserArrayAdapter(Activity context, int layoutId, ArrayList<User> arr) {
		// TODO Auto-generated constructor stub
		super(context, layoutId, arr);
		app = context;
	    list = (ArrayList<User>) arr;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View userView = convertView;
	    if(null == userView) {
	    	userView = new UserView(app);
	    }
	    User usr=list.get(position);
	    if (null!=usr) {
			UserView row=(UserView) userView;
			row.lb_fullname.setText(usr.getFullname());
			row.lb_email.setText(usr.getEmail());
		}
		return userView;
	}
}
