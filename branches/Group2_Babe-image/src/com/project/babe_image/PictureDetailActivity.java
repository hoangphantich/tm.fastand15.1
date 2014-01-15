package com.project.babe_image;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PictureDetailActivity extends Activity {
	String _Str;
	String _Link;
	TextView ViewName;
	ImageView ShowPic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_detail);
		
		ViewName=(TextView) findViewById(R.id.textView1);
		ShowPic=(ImageView)findViewById(R.id.imageView1);
		
		//Received data 
		Intent i=getIntent();
		_Str=i.getStringExtra("Str");
		_Link=i.getStringExtra("Link");
		
		ViewName.setText(_Link);
		ShowPic.setImageBitmap(BitmapFactory.decodeFile(_Str));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_menu, menu);
		return true;
	}
	public void Close(View v){
		finish();
	}
}
