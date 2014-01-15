package com.project.babe_image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class MenuActivity extends Activity {

	@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	switch(item.getItemId()){
	 case R.id.item_back:
		 Intent Intent_option = new Intent();
		 Intent_option.setClass(this, Main_Activity.class);
		 startActivity(Intent_option);
		 finish();
		 break;
	 case R.id.item_search:
		 Intent Intent_gotocatalog = new Intent();
		 Intent_gotocatalog.setClass(this, SearchActivity.class);
		 Intent_gotocatalog.putExtra("Link", ShowNormalActivity.LinkShow);
		 startActivity(Intent_gotocatalog);
		 finish();
		 break;
	 case R.id.item_gallery:
		 Intent Intent_gotocheckout = new Intent();
		 Intent_gotocheckout.setClass(this, GalleryViewActivity.class);
		 Intent_gotocheckout.putExtra("Link", ShowNormalActivity.LinkShow);
		 startActivity(Intent_gotocheckout);
		 finish();
		 break;
	 case R.id.item_exit:
		 finish();
		 break;
	}
	return super.onOptionsItemSelected(item);
}
}