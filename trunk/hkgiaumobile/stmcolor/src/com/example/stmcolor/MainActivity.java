package com.example.stmcolor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//get ui
		Button bt_red=(Button)findViewById(R.id.bt_red);
		Button bt_blue=(Button)findViewById(R.id.bt_blue);
		Button bt_yellow=(Button)findViewById(R.id.bt_yellow);
		
		RadioButton rd_red=(RadioButton)findViewById(R.id.rd_red);
		RadioButton rd_blue=(RadioButton)findViewById(R.id.rd_blue);
		RadioButton rd_yellow=(RadioButton)findViewById(R.id.rd_yellow);
		
		TextView lb_color=(TextView)findViewById(R.id.lb_color);
		
		//add event
		bt_red.setOnClickListener(new ColorListenner(Color.RED,lb_color));
		bt_blue.setOnClickListener(new ColorListenner(Color.BLUE,lb_color));
		bt_yellow.setOnClickListener(new ColorListenner(Color.YELLOW,lb_color));
		
		rd_red.setOnClickListener(new ColorListenner(Color.RED,lb_color));
		rd_blue.setOnClickListener(new ColorListenner(Color.BLUE,lb_color));
		rd_yellow.setOnClickListener(new ColorListenner(Color.YELLOW,lb_color));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
