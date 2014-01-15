package com.project.babe_image;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import android.widget.Gallery.LayoutParams;

public class GalleryViewActivity extends Activity implements
AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
	String LinkSwithView="";
	private Context mContext;
    private ImageSwitcher mSwitcher;
	String[] _mImageIds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_view);
		//Received link file 
		Intent j=getIntent();
		LinkSwithView=j.getStringExtra("Link");
		 //Get data
		File dir =new File(LinkSwithView);
		File file[]=dir.listFiles();
		_mImageIds = new String[file.length];
		for (int i = 0; i < file.length; i++) {
			String Link=file[i].toString();
			_mImageIds[i]=Link;
		}
		
		//Display data
        mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
        mSwitcher.setFactory(this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
 
        Gallery g = (Gallery) findViewById(R.id.gallery);
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemSelectedListener(this);
		
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
	     ImageView i = new ImageView(this);
	        //i.setBackgroundColor(0xFF000000);
	        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
	        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
	        return i;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		mSwitcher.setImageURI(Uri.parse(_mImageIds[arg2]));
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
            mContext = c;
        }
        public int getCount() {
            return _mImageIds.length;
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);
            //i.setImageResource(mThumbIds[position]);
            i.setImageBitmap(BitmapFactory.decodeFile(_mImageIds[position]));
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            i.setBackgroundResource(R.drawable.ic_launcher);
            return i;
        }
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_image, menu);
		return true;
	}
	/*
	 *   call menu
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {
		  
		    case R.id.item_view:
		    	Intent  shownormal = new Intent(this, Main_Activity.class);
		  		startActivity(shownormal);
		    	// callback function here
		    	finish();
		    	return true;
	
		    case R.id.item_exit:
		    	// callback function here
		    		finish();
		      return true;
		      
		    default:
		      return super.onOptionsItemSelected(item);
		  }
	}		
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			Intent i = new Intent(this, Main_Activity.class);
			startActivity(i);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
