package com.nguyentiendung.mediaplayer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.Toast;

public class MainActivity1 extends Activity implements OnItemClickListener {
	private static final String tag = "Main";
	private Gallery _gallery;
	private ImageAdapter _imageAdapter;
	Context context;
	public static int x,y;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		 Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			x = display.getWidth();
			 y = display.getHeight();

			// Toast.makeText(this,x+"MMMMMMMM"+y+"", Toast.LENGTH_SHORT).show();
					 
					 
		setTitle("Cac buc anh yeu thich");
		_gallery = (Gallery) this.findViewById(R.id.gallery1);
		_imageAdapter = new ImageAdapter(this);
		_gallery.setAdapter(_imageAdapter);
		_gallery.setOnItemClickListener(this);		
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long duration) {
		int resourcId = (Integer) _imageAdapter.getItem(position);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourcId);
//		Toast.makeText(this,
//				"Selected Image: " + getResources().getText(resourcId) + "\n"
//						+ "Height: " + bitmap.getHeight() + "\n" + "Width: "
//						+ bitmap.getWidth(), Toast.LENGTH_SHORT).show();
		
	
		 
	}
	
	  @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

	        	Intent gui1=new Intent(MainActivity1.this, VidduActivity.class);

	       	 startActivity(gui1);

	       	 finish();
	       	 

	            return true;
	        } else if ((keyCode == KeyEvent.KEYCODE_MENU)) {
	        	finish();
	            return true;
	        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
	          
	            return true;
	        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
	            
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }
}