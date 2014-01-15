package com.project.babe_image;

import java.io.File;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
 
public class SwithViewActivity extends MenuActivity implements
AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
    private Context mContext;
    private ImageSwitcher mSwitcher;
	String Linkfile ="/mnt/sdcard/Download/dog/";
	//String Linkfile ="/mnt/USB/dog/";
	String[] _mImageIds;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.swith_view);
        
        //Get data
		File dir =new File(Linkfile);
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
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        //mSwitcher.setImageResource(mImageIds[position]);
		mSwitcher.setImageURI(Uri.parse(_mImageIds[position]));
    }
    public void onNothingSelected(AdapterView<?> parent) {
    }
    public View makeView() {
        ImageView i = new ImageView(this);
        //i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        return i;
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
	public boolean onCreateOptionsMenu(Menu menu) {	
		getMenuInflater().inflate(R.menu.my_menu, menu);
		return true;
	}
}