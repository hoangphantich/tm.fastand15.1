package com.hoangphan.tutor0602_bookprovider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//init uri (same as contact)
		// content://com.hoangphan.books/books
		Uri uri = Uri.parse(BookProvider.UriString);
		ContentResolver provider = getContentResolver();
		
		provider.delete(uri, null, null);
		
	    //insert 1 book
	    ContentValues editValues = new ContentValues();
	    editValues.put(BookProvider.TITLE, "Android programming");
	    editValues.put(BookProvider.ISBN, "APM");
	    editValues.put(BookProvider._ID, "1000");
	    provider.insert(uri, editValues);
	    
	    ContentValues editValues1 = new ContentValues();
	    editValues1.put(BookProvider.TITLE, "Advance Android programming");
	    editValues1.put(BookProvider.ISBN, "AAP100212");
	    editValues1.put(BookProvider._ID, "1010");
	    provider.insert(uri, editValues1);
		
		provider.delete(uri, BookProvider._ID+" = ?", new String[]{"100"});
		
	    ContentValues updateValues = new ContentValues();
	    updateValues.put(BookProvider.TITLE, "Android programming 2");
	    Uri uriUpdate = Uri.parse(BookProvider.UriString+"/1000");
	    getContentResolver().update(uriUpdate, updateValues, null, null);
		
	    
	    Button btnRetrieve = (Button) findViewById(R.id.btnRetrieve);
	    btnRetrieve.setOnClickListener(new View.OnClickListener() {
	      public void onClick(View v) {
	        // ---retrieve the titles---
	        Uri allTitles = Uri.parse(BookProvider.UriString);
	        Cursor c = managedQuery(allTitles, null, null, null, "title desc");
	        if (c.moveToFirst()) {
	          do {
	            Log.v(
	                "Books",
	                c.getString(c.getColumnIndex(BookProvider._ID)) + ", "
	                    + c.getString(c.getColumnIndex(BookProvider.TITLE)) + ", "
	                    + c.getString(c.getColumnIndex(BookProvider.ISBN)));
	          } while (c.moveToNext());
	        }
	      }
	    });
	    
	    Button btnAdd = (Button) findViewById(R.id.btnAdd);
	    btnAdd.setOnClickListener(new View.OnClickListener() {
	      public void onClick(View v) {
	        ContentValues values = new ContentValues();
	        EditText title = (EditText) findViewById(R.id.txtTitle);
	        EditText isbn = (EditText) findViewById(R.id.txtISBN);
	        values.put("title", title.getText().toString());
	        values.put("isbn", isbn.getText().toString());
	        Uri uri = getContentResolver().insert(Uri.parse(BookProvider.UriString),values);
	        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
	      }
	    });    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
