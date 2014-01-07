package com.hkgiau.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import com.hkgiau.sqlite.R;

public class ClassActivity extends Activity{		
	/**
	 * 
	 */
	protected static String DATA_NAME = "myOption";	
	protected static String OPTION_SAVEPASS = "savepass";	
	protected static String OPTION_LANGUAGE = "language";
	protected static String OPTION_DATA = "data";
	protected static String OPTION_USERNAME = "username";
	protected static String OPTION_PASS = "password";
	protected static String STRING_NAME="name";
	protected static String STRING_PRICE="price";
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.MENU_ITEM_OPTION:			
			Intent it_option=new Intent(this, OptionActivity.class);			
			startActivity(it_option);
			break;
		case R.id.MENU_ITEM_CATALOG:			
			Intent it_catalog=new Intent(this, CatalogActivity.class);
			startActivity(it_catalog);
			finish();
			break;
		case R.id.MENU_ITEM_CHECKOUT:			
			Intent it_checkout=new Intent(this, CheckOutActivity.class);
			startActivity(it_checkout);
			finish();
			break;
		case R.id.MENU_ITEM_LOGOUT:
			SharedPreferences pre=getSharedPreferences(DATA_NAME, MODE_PRIVATE);
			SharedPreferences.Editor editor=pre.edit();
			editor.putInt(OPTION_SAVEPASS, 0);
			editor.commit();				
			Intent it_main=new Intent(this, MainActivity.class);
			startActivity(it_main);
			finish();
			break;
		case R.id.MENU_ITEM_EXIT:
			System.exit(0);
			break;	
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
