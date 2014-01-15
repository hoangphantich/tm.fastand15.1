package com.project.babe_image;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BinderAdapter extends ArrayAdapter<InforData>{
	private Activity context;
	private int layout;
	private List<InforData>list;
	
	public BinderAdapter(Context context, int textViewResourceId, List<InforData> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context=(Activity) context;
		this.layout=textViewResourceId;
		this.list=objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater flater=context.getLayoutInflater();
		View pos=flater.inflate(layout, parent,false);
		TextView txt1=(TextView) pos.findViewById(R.id.textView1);
		ImageView Img1=(ImageView) pos.findViewById(R.id.imageView1);
		/*txt1.setTextAlignment(Gravity.LEFT);
		txt2.setTextAlignment(Gravity.LEFT);
		txt3.setTextAlignment(Gravity.LEFT);*/
		InforData data=list.get(position);
		txt1.setText(data.getField1()==null?"":data.getField1().toString());
		String str=data.getField4()+"";
		Img1.setImageBitmap(BitmapFactory.decodeFile(str));
		return pos;
	}
}