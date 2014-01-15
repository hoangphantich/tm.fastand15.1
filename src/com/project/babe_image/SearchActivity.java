package com.project.babe_image;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;



public class SearchActivity extends MenuActivity {
	public ArrayList<InforImage> listDatas = new ArrayList<InforImage>();
	public ArrayList<InforImage> listBackupDatas = new ArrayList<InforImage>();
	public FilterAdapter adapter;
	ListView lv;
	EditText InputFilter;
	String[] _ImageName;
	String[] _ImageId;
	String LinkSearch="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);	
		lv = (ListView) findViewById(R.id.listView1);
		InputFilter = (EditText) findViewById(R.id.Input);
		
		//Received link file 
		Intent j=getIntent();
		LinkSearch=j.getStringExtra("Link");
		
//		Toast.makeText(SearchActivity.this,	LinkSearch, Toast.LENGTH_LONG).show();
		
		// Prepare dummy data
		File dir =new File(LinkSearch);
		File file[]=dir.listFiles();
		_ImageName = new String[file.length];
		_ImageId = new String[file.length];
		for (int i = 0; i < file.length; i++) {
			String Link=file[i].toString();
			StringBuffer sb=new StringBuffer(Link);
			sb.delete(0, LinkSearch.length());
			_ImageName[i]=sb.toString();
			_ImageId[i]=file[i].toString();
		}
		// Prepare dummy data
		for (int i = 0; i < _ImageId.length; i++) {
			InforImage footballPlayer = new InforImage();
			footballPlayer.name = _ImageName[i];
			footballPlayer.imageResourceId = _ImageId[i];
			listDatas.add(footballPlayer);
			listBackupDatas.add(footballPlayer);
		}
		adapter = new FilterAdapter();
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				//Get link and name
				String _ImgLink=listDatas.get(arg2).imageResourceId.toString();
				StringBuffer sb=new StringBuffer(_ImgLink);
				sb.delete(0, LinkSearch.length());
				String _ImgName=sb.toString();
				ShowImage(_ImgName,_ImgLink);		
			}
		});
		InputFilter.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int arg1, int arg2,
					int arg3) {
				adapter.getFilter().filter(s.toString());
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}
			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});
	}
	private class FilterAdapter extends BaseAdapter {
	private My_Filter myFilter;
		@Override
	public int getCount() {
			return listDatas.size();
	}
	public Filter getFilter() {
			if (myFilter == null)
				myFilter = new My_Filter();
			return myFilter;
		}
		@Override
	public InforImage getItem(int position) {
			return listDatas.get(position);
		}
		@Override
	public long getItemId(int position) {
			return 0;
		}
		@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			PlayerViewHolder holder;
			if (convertView == null) {
				convertView = (ViewGroup) LayoutInflater.from(
						getApplicationContext()).inflate(
						R.layout.search_item, null);
				holder = new PlayerViewHolder();
				holder.name = (TextView) convertView.findViewById(R.id.textView1);
				holder.thumb = (ImageView) convertView.findViewById(R.id.imageView1);
				convertView.setTag(holder);
			} else {
				holder = (PlayerViewHolder) convertView.getTag();
			}
			holder.name.setText(getItem(position).name);	
			holder.thumb.setImageURI(Uri.parse(getItem(position).imageResourceId));	
			return convertView;
		}
	}
	private class My_Filter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			ArrayList<InforImage> filters = new ArrayList<InforImage>();
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				for (InforImage player : listBackupDatas) {
					filters.add(player);
				}
				results.values = filters;
				results.count = filters.size();
			} else {
				// We perform filtering operation
				for (InforImage row : listBackupDatas) {
					if (((InforImage) row).name.toUpperCase().startsWith(
							constraint.toString().toUpperCase())) {
						filters.add(row);
					}
				}
				results.values = filters;
				results.count = filters.size();
			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			if (results.count == 0) {
				listDatas.clear();
				adapter.notifyDataSetChanged();
			} else {
				listDatas.clear();
				ArrayList<InforImage> resultList = (ArrayList<InforImage>) results.values;
				for (InforImage row : resultList) {
					listDatas.add(row);
				}
				adapter.notifyDataSetChanged();
			}
		}
	}
	private static class PlayerViewHolder {
		public ImageView thumb;
		public TextView name;
	}
	public class InforImage {
		/** Keep the id of resource file which is thumbnail's. */
		public String imageResourceId;
		/** Player name. */
		public String name;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_menu, menu);
		return true;
	}
	private void ShowImage(String ImageName, String ImageLink){
		Intent i = new Intent(SearchActivity.this, PicDetaiActivity.class);
		i.putExtra("Str", ImageName);
		i.putExtra("Link", ImageLink);
		startActivity(i);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			Intent i = new Intent(this, ShowNormalActivity.class);
			Bundle bundle=new Bundle();
			bundle.putString("Link", LinkSearch);
			i.putExtra("MyPackage", bundle);
			startActivity(i);
			finish();
		} else if (keyCode==KeyEvent.KEYCODE_HOME){
			Intent i = new Intent(this, Main_Activity.class);
			startActivity(i);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}