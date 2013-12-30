package com.hoangphan.tutor0301_hqvinfo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FourActivity extends Activity {

	
	class MobileAdater extends ArrayAdapter<String> {
		
		Context app;
		String[] list;
		
		public MobileAdater(Context context, int resource,
				String[] objects) {
			super(context, R.layout.mobile_row, objects);
			app = context;
			list = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//inflat 
			LayoutInflater inflater = (LayoutInflater)
					app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
				View rowView = inflater.inflate(R.layout.mobile_row, parent, false);
				TextView textView = (TextView) rowView.findViewById(R.id.mobileName);
				ImageView imageView = (ImageView) rowView.findViewById(R.id.mobileImg);
				textView.setText(list[position]);
		 
				// Change icon based on name
				String s = list[position];
		 
				if (s.equals("WindowsMobile")) {
					imageView.setImageResource(R.drawable.windowsmobile_logo);
				} else if (s.equals("iOS")) {
					imageView.setImageResource(R.drawable.ios_logo);
				} else if (s.equals("Blackberry")) {
					imageView.setImageResource(R.drawable.blackberry_logo);
				} else {
					imageView.setImageResource(R.drawable.android_logo);
				}
		 
				return rowView;
		}
	}
	
	
	String[] mobileArr =  new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four);
		
		MobileAdater adapter = new MobileAdater(this, R.layout.mobile_row, mobileArr);
		ListView mobileList = (ListView) findViewById(R.id.mobileList);
		mobileList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.four, menu);
		return true;
	}

}
