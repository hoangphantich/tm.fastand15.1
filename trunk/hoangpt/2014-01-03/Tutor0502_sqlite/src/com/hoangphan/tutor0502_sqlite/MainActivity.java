package com.hoangphan.tutor0502_sqlite;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends ListActivity {

	private DBAdapter db;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//insert 1 user to
	    db = new DBAdapter(this);
	    //db.open(); //connnect
	    Log.d("Database", db.toString());
	    //db.createUser("Hoang Phan");
	    //db.createUser("Thuy Nguyen");
	    //db.createUser("Huy Tran");
	    db.deleteUser(3);
	    _getData();//display all user
		db.close();
	}

	private void _getData() {
	    cursor = db.getAllUsers();
	    startManagingCursor(cursor);
	    String[] from = new String[] {DBAdapter.KEY_NAME};
	    int[] to = new int[] {R.id.name1};
	    
	    //cursor
	    SimpleCursorAdapter users = new SimpleCursorAdapter(this, R.layout.user_row, cursor, from, to);
	    setListAdapter(users);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
