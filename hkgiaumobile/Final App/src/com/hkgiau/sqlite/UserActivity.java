package com.hkgiau.sqlite;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.hkgiau.custom.User;
import com.hkgiau.custom.UserArrayAdapter;

public class UserActivity extends ClassActivity {

	private DBAdapter db;
	private ArrayList<User> arrUser;
	private ListView ls_user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		db = new DBAdapter(this);
		arrUser=new ArrayList<User>();
		
		//getui
		ls_user=(ListView)findViewById(R.id.ls_user);
		GetData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.getItem(2).setEnabled(false);
		return true;
	}
	private void GetData() {		
	    Cursor cursor = db.getAllUsers();
	    if(cursor.moveToFirst())
	    {
		    do {	   
		    	User usr = new User();
		    	usr.setID(Integer.parseInt(cursor.getString(0)));
		    	usr.setFullname(cursor.getString(3));
		    	usr.setEmail(cursor.getString(4));
		    	arrUser.add(usr);	    	
		    } while (cursor.moveToNext());	    
//			Toast.makeText(UserActivity.this,arrUser.get(0).getFullname() , Toast.LENGTH_LONG).show();
	    }
		//set custom listview
	    final UserArrayAdapter adapter=new UserArrayAdapter(this, 0, arrUser);
	    ls_user.setAdapter(adapter);
	    
	    //list listener (edit user)
	    ls_user.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    //delete user
	    ls_user.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder= new AlertDialog.Builder(UserActivity.this);
				builder.setTitle("Warning");
			    builder.setMessage("Do you want to delete this user?");
			    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {	        
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			        	
			        	//cannot delete admin
			        	if(arrUser.get(arg2).getFullname().equals("Admin"))
			        		Toast.makeText(UserActivity.this,"Cannot delete admin."
									, Toast.LENGTH_LONG).show();
			        	else
			        	{
				        	long flag=db.deleteUser(arrUser.get(arg2).getID());
				        	if(flag==1)
				        	{
				        		Toast.makeText(UserActivity.this,"Delete user successful."
									, Toast.LENGTH_LONG).show();			        	
					        	arrUser.remove(arg2);
					        	adapter.notifyDataSetChanged();				        	
				        	}
			        	}
			        }
			     });
			    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				});
			    builder.show();				
				return false;
			}	    	
		});
	}
	
	public void AddUser(View view)
	{
		Intent it_userInfo=new Intent(this, UserInfoActivity.class);
		startActivity(it_userInfo);
		finish();
	}
	
}
