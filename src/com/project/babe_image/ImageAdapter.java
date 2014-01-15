package com.project.babe_image;


import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.Gallery.LayoutParams;

public class ImageAdapter extends BaseAdapter {
	int mGalleryItemBackground;
	private Context mContext;
	private List<String> FileList;
	
	public ImageAdapter(Context c, List<String> fList) {
		    mContext = c;
		    FileList = fList;
		    TypedArray a = obtainStyledAttributes(R.styleable.gallery_android_galleryItemBackground);
		    mGalleryItemBackground = a.getResourceId(R.styleable.gallery_android_galleryItemBackground,0);
		    a.recycle();
		}
	
	public int getCount() {
		    return FileList.size();
		}
	
	public Object getItem(int position) {
		    return position;
		} 
	
	public long getItemId(int position) {
		    return position;
		}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		    ImageView i = new ImageView(mContext);
		
		    Bitmap bm = BitmapFactory.decodeFile(
		      FileList.get(position).toString());
		    i.setImageBitmap(bm);
		
		    i.setLayoutParams(new Gallery.LayoutParams(150, 100));
		    i.setScaleType(ImageView.ScaleType.FIT_XY);
		    i.setBackgroundResource(mGalleryItemBackground);
		
		    return i;
		}
	
	public TypedArray obtainStyledAttributes(int theme) {
		// TODO Auto-generated method stub
		return null;
		}
}