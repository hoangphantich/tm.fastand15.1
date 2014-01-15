package com.project.babe_image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Main_Activity extends Activity {
	//public static String Linkfile ="/mnt/USB/Baitap/";
	public static String Linkfile =Environment.getExternalStorageDirectory().getPath()+ "/download/Baitap/";
	private BinderAdapter adapter=null;
	private List<InforData> list = new ArrayList<InforData>();
	GridView gv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			//Get data
			File dir =new File(Linkfile+"/Icon");
			File file[]=dir.listFiles();
					
			for (int i = 0; i < file.length; i++) {
				InforData data=new InforData();
				String Link=file[i].toString();
				StringBuffer sb=new StringBuffer(Link);
				sb.delete(0, (Linkfile.length()+5));		//Remove link file
				sb.delete((sb.length()-4), sb.length());	//Remove ipg; png; gif....
				data.setField1(sb.toString());
				data.setField4(file[i].toString());
				list.add(data);	
			}
			//Display data
			Update_UI();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(Main_Activity.this,
					"File not found "+"\n" + Linkfile, Toast.LENGTH_LONG).show();
		}
	}
	public void Update_UI(){
		adapter=new BinderAdapter(this, R.layout.grid_item, list);
		gv= (GridView) findViewById(R.id.gridView1);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//Show Picture
							
				Intent i1 = new Intent(Main_Activity.this, ShowNormalActivity.class);
				Bundle b=new Bundle();
				b.putString("Link", list.get(arg2).getField4().toString());
				i1.putExtra("MyPackage", b);
				 startActivity(i1);
			//	 finish();
				
//				//Intent i1 = new Intent(Main_Activity.this, ShowNormalActivity.class);
//				Intent i1 = new Intent(Main_Activity.this, ShowNormalActivity.class);
//				i1.putExtra("Link", list.get(arg2).getField4().toString());
//				startActivity(i1);
				}
			});	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {
		  		  	case R.id.item_capture:
		  		Intent  capture = new Intent(this, OpenCamActivity.class);
		  		startActivity(capture);
		  		finish();
		  	// callback capture
			  return true;
		  	case R.id.item_download:
		  		Intent  download = new Intent(this, DownloadActivity.class);
		  		startActivity(download);
		     // changeBackground();
		  		finish();
		      return true;		   
		    case R.id.item_shareimage:
		    	Intent  shareimage = new Intent(this, ShareActivity.class);
		  		startActivity(shareimage);
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

	
}