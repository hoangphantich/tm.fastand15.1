package com.project.babe_image;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

public class PicDetaiActivity extends Activity  {
	String _Str;
	String _Link;
	TextView ViewName;
	ImageView ShowPic;
	ZoomControls zoom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pic_detai);
		
		// New code
		//Received data 
		Intent i=getIntent();
		_Str=i.getStringExtra("Str");
		_Link=i.getStringExtra("Link");
				
		TouchImageView img = new TouchImageView(this);
        //img.setImageResource(R.drawable.ic_launcher);
		img.setImageBitmap(BitmapFactory.decodeFile(_Link));
        img.setMaxZoom(20f);
        setContentView(img);
		}
		}



