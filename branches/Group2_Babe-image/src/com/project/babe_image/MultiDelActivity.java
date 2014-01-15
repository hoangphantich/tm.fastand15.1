package com.project.babe_image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Toast;

public class MultiDelActivity extends MenuActivity {
	File dir;
	File[] file;
	private BinderAdapter adapter=null;
	private List<InforData> list = new ArrayList<InforData>();
	static String LinkDel="";
	GridView gv;
//	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_normal);
		gv= (GridView) findViewById(R.id.Gridview1);
		
	Intent i=getIntent();
	LinkDel=i.getStringExtra("Link");
		
//		Toast.makeText(MultiDelActivity.this,"View -->"+ Str, Toast.LENGTH_LONG).show();
	
		//Get data
				dir =new File(LinkDel);
				file=dir.listFiles();
						
				for (int k = 0; k< file.length; k++) {
					InforData data=new InforData();
					String Link=file[k].toString();
					StringBuffer sb=new StringBuffer(Link);
					sb.delete(0, LinkDel.length());
					data.setField1(sb.toString());
					data.setField4(file[k].toString());
					list.add(data);	
				}
				//Display data
				Update_UI();
	}

	public void Update_UI(){

		adapter=new BinderAdapter(this, R.layout.del_item, list);
		gv.setAdapter(adapter);	
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				DeleteImage();
				return false;
			}
		});
	}
	public void DeleteImage(){
		AlertDialog.Builder b=new Builder(MultiDelActivity.this);
		b.setTitle("Multi Delete");
		b.setMessage("Delete all");
		b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
				for (int i = gv.getChildCount()-1; i >= 0; i--) {
					//lấy ra dòng thứ i trong ListView
					View v1=gv.getChildAt(i);
					//Ta chỉ lấy CheckBox ra kiểm tra
					CheckBox chk=(CheckBox) v1.findViewById(R.id.chkitem);
					if(chk.isChecked())	{
						file[i].delete();
						list.remove(i);
						adapter.notifyDataSetChanged();
						}
					dir.delete();
				}
				Toast.makeText(MultiDelActivity.this,"Remove ok", Toast.LENGTH_SHORT).show();
			}
		});
		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		
	});
		b.show();
	}	
}

