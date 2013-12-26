package com.example.nangsg_tipster;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyProcessEvent implements OnItemSelectedListener {
	public int price;

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
