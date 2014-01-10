package com.group4.custom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.activity.R;
import com.group4.foodclass.Food;

public class CustomGridViewAdapter extends ArrayAdapter<Food> {
	Context context;
	int layoutResourceId;
	ArrayList<Food> data = new ArrayList<Food>();

	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<Food> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
			holder.imageFoodClass = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
		
		//list file in asset
		AssetManager assetManager = context.getAssets();
//		try {
//			String[] files = assetManager.list("image");
//			for(int i=0; i<files.length; i++)
//			{
//				Toast.makeText(context, files[0], Toast.LENGTH_LONG).show();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//		}		
		
		Food FoodClass = data.get(position);
//		String sdcard=Environment.getExternalStorageDirectory().getAbsolutePath();
		String img="image/howto_"+FoodClass.getCode()+"_"+FoodClass.getCat()+".jpg";
		
		holder.txtTitle.setText(FoodClass.getSubject());
//		holder.imageFoodClass.setImageBitmap(BitmapFactory.decodeFile(imgPath));
		
		//read image from asset
		InputStream ims = null;
		try {
			ims = assetManager.open(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Drawable d = Drawable.createFromStream(ims, null);
		// set the drawable to imageview
		
		holder.imageFoodClass.setImageDrawable(d);
		return row;

	}

	static class RecordHolder {
		TextView txtTitle;
		ImageView imageFoodClass;

	}
}