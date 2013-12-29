package com.example.stmcolor;
import android.view.View;
import android.widget.TextView;

public class ColorListenner implements View.OnClickListener {
	int color;
	TextView view;

	public ColorListenner(int color, TextView view)
	{
		this.color=color;
		this.view=view;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		view.setBackgroundColor(color);
	}
	

}
