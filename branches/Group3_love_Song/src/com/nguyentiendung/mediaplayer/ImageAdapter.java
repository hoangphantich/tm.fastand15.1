package com.nguyentiendung.mediaplayer;

import java.io.File;
import java.io.FilenameFilter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

public class ImageAdapter extends BaseAdapter {
	
	private Context _context = null;
	private final int[] imageIds = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
			
		};
	File root;
	File[] fileArray;
	String file;  
	public ImageAdapter(Context context) {
		this._context = context;
	}

	@Override
	public int getCount() {
		return imageIds.length;
	}

	@Override
	public Object getItem(int index) {
		return imageIds[index];
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int postion, View view, ViewGroup group) {

		 root = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath());
			fileArray = root.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String filename) {
					return filename.toLowerCase().endsWith(".jpg");
				}
			});
			
			 file="/sdcard/"+fileArray[postion].getName();
			 ImageView imageView = new ImageView(_context);
			if (!fileArray[postion].getName().equals(""))
			{
		
		imageView.setImageBitmap(BitmapFactory.decodeFile(file));
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setLayoutParams(new Gallery.LayoutParams(MainActivity1.x, MainActivity1.y));
			}
		return imageView;
	}

	private WindowManager getSystemService(String windowService) {
		// TODO Auto-generated method stub
		return null;
	}
}
