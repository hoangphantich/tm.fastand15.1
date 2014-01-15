package com.project.babe_image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class ShowNormalActivity extends MenuActivity {
	private BinderAdapter adapter=null;
	private List<InforData> list = new ArrayList<InforData>();
	String Linkfile =Main_Activity.Linkfile;
	private File dir;
	private File[] file;
	static String LinkShow="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_normal);
		
		
		Intent j=getIntent();
		 Bundle _package = j.getBundleExtra("MyPackage");
		String Str=_package.getString("Link");
//		
//		Intent j=getIntent();
//		String Str=j.getStringExtra("Link");
		
		if (Str.equals(Linkfile+"Icon/Camera.png")) {
			Str=Str.replace("Icon/Camera.png", "Camera/");
		}		
		if (Str.equals(Linkfile+"Icon/Funny.png")) {
			Str=Str.replace("Icon/Funny.png", "Funny/");
		}
		if (Str.equals(Linkfile+"Icon/Folder.png")) {
			Str=Str.replace("Icon/Folder.png", "Folder/");
		}
		if (Str.equals(Linkfile+"Icon/Download.png")) {
			Str=Str.replace("Icon/Download.png", "Download/");
		}
		LinkShow=Str;
		
		//Toast.makeText(ShowNormalActivity.this,"View -->"+ Str, Toast.LENGTH_LONG).show();
		
		//Get data
				dir =new File(LinkShow);
				file=dir.listFiles();
						
				for (int i = 0; i < file.length; i++) {
					InforData data=new InforData();
					String Link=file[i].toString();
					StringBuffer sb=new StringBuffer(Link);
					sb.delete(0, LinkShow.length());
					data.setField1(sb.toString());
					data.setField4(file[i].toString());
					list.add(data);	
				}
				//Display data
				Update_UI();
	}

	public void Update_UI(){

		adapter=new BinderAdapter(this, R.layout.grid_item, list);
		final GridView gv= (GridView) findViewById(R.id.Gridview1);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//Show Picture
				 showdetail(arg2);
				//Toast.makeText(MainActivity.this,"View -->"+ list.get(arg2).toString(), Toast.LENGTH_SHORT).show();
				}
			});	
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				CreatMyDialog(arg2);
				return true;
			}
		});
	}
	public void showdetail(int posistion) {	
		Intent i = new Intent(this, PicDetaiActivity.class);
		String ImageName=list.get(posistion).getField1().toString();
		String ImageLink= list.get(posistion).getField4().toString();
		i.putExtra("Str", ImageName);
		i.putExtra("Link", ImageLink);
		startActivity(i);
	 }
	public void DeleteImage(int position){
		final InforData data=list.get(position);
		final int pos=position;
		AlertDialog.Builder b=new Builder(ShowNormalActivity.this);
		b.setTitle("Remove");
		b.setMessage("Remove "+data.getField1());
		b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			file[pos].delete();	
			dir.delete();
			list.remove(pos);
			adapter.notifyDataSetChanged();
				Toast.makeText(ShowNormalActivity.this, "Remove ok", Toast.LENGTH_SHORT).show();
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_gallery_search, menu);
		return true;
	}
	/*
	 *   call menu
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) 
		  {
		  	case R.id.item_view:
		  		Intent  capture = new Intent(this, Main_Activity.class);
		  		startActivity(capture);
		  		finish();
		  	// callback capture
			  return true;
		  	case R.id.item_download:
		  		Intent  download = new Intent(this, DownloadActivity.class);
		  		startActivity(download);
		    	// callback function here
		    	finish();
		      return true;		    
		    case R.id.item_shareimage:
		    	Intent  shareimage = new Intent(this, ShareActivity.class);
		  		startActivity(shareimage);
		    	finish();
		    	return true;
		    case R.id.item_gallery1:
		    	Intent gallery = new Intent();
		    	gallery.setClass(this, GalleryViewActivity.class);
		    	gallery.putExtra("Link", ShowNormalActivity.LinkShow);
				 startActivity(gallery);			
		    	finish();
		    	return true;
		    case R.id.item_search1:
		    	Intent search = new Intent();
		    	search.setClass(this, SearchActivity.class);
		    	search.putExtra("Link", ShowNormalActivity.LinkShow);
				 startActivity(search);
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
	
	public void CreatMyDialog(final int Pos_Del){
		final CharSequence[] items = { "Delete image",
				"Multi delete", "Share this", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
            	switch (item) {
				case 0:
					DeleteImage(Pos_Del);
					break;
				case 1:
					Intent i = new Intent(ShowNormalActivity.this, MultiDelActivity.class);
					i.putExtra("Link", LinkShow);
					startActivity(i);
					break;
				case 2:
					Intent shareface = new Intent(ShowNormalActivity.this, FacebookActivity.class);
					shareface.putExtra("linkface", file[Pos_Del].toString());
					startActivity(shareface);
					// Share face
					break;
				default:
					break;
				}
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
	}


}

