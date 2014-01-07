package nguyentiendung.example.totalapp;
/////////////////////////For Administrator to manage User//////////////
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ManagementActivity extends Activity {
	public static final int UPDATE_SUCCESS = 8;
	private static final int UPDATE_REQUEST = 0;
	SQLiteDatabase database;
	private String tenfile="qluser.db";
	private ListView lstviewuser;
	ArrayList<UserManagementClass> arr;
	ManagementAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management);
////////////////////CREATE DATABASE AND TABLE////////////////////////////////////////////////		
		database=openOrCreateDatabase(tenfile, MODE_PRIVATE, null);
		String statement = "CREATE  TABLE tblUser("+
							"id INTEGER PRIMARY KEY  NOT NULL ,"+
							"username TEXT,"+
							"password TEXT,"+
							"fullname TEXT, "+
							"email TEXT, " 
							+"status BOOL)";		
		if(!isTableExists(database,"tblUser"))
		database.execSQL(statement);
/////////////////////////SHOW ALL USER FROM DATABASE TO LISTVIEW///////////////		
		lstviewuser = (ListView) findViewById(R.id.lstManagement);
		arr = new ArrayList<UserManagementClass>();
		updateUI();
		adapter = new ManagementAdapter(this, arr);
		lstviewuser.setAdapter(adapter);
/////////////////////////process when user click on an item//////////////////////		
		lstviewuser.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				query(arg2);
			}
		});
//////////////////////delete user if long click on item on listview/////////		
		lstviewuser.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				final UserManagementClass data = (UserManagementClass) arr.get(arg2); 			// get the longclicked item
				final int id= data.getId();														//get id Field of item
				final int pos = arg2;
				AlertDialog.Builder b = new Builder(ManagementActivity.this);
				b.setTitle("Warning Dialog");
				b.setMessage("Do you want to delete user"+data.getUsername());
				b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						int n=database.delete("tblUser", "id=?", new String[]{""+id});				//delete the row with id=id value
						if(n>0)
						{
							//Toast.makeText(this, "Remove ok", Toast.LENGTH_LONG).show();
							arr.remove(pos);
							adapter.notifyDataSetChanged();
						}
						else
						{
							//Toast.makeText(this, "Remove not ok", Toast.LENGTH_LONG).show();
						}
					}
				});
			b.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			b.show();	
				
				return false;
			}
			
		});
		//database.close();
///////////////////////////process for listview item click/////////////////////////
		
//////////////////////////process for button Add User///////////////////////////////
		Button btnadd = (Button) findViewById(R.id.btnRemove);
		btnadd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(getApplicationContext(), DetailActivity.class);
				startActivity(i1);
			}
		});
	}
///////////////////////////////////////check table if it's exist//////////////////////////
	public boolean isTableExists(SQLiteDatabase database, String tableName) {
		Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
		if(cursor!=null) {
			if(cursor.getCount()>0) {
				cursor.close();
				return true;
			}
			cursor.close();
		}
		return false;
	}
	
	
//////////////////////////////////UPDATE UI//////////////////////////////////////////////
public void updateUI()
{
	//SQLiteDatabase database=openOrCreateDatabase(tenfile, SQLiteDatabase.CREATE_IF_NECESSARY, null);
	if(database!=null)
	{
		Cursor cursor=database.query("tblUser", null, null, null, null, null, null);
		/*startManagingCursor(cursor);
		UserManagementClass header=new UserManagementClass();
		header.setUsername(cursor.getColumnName(1));
		header.setEmail(cursor.getColumnName(4));
		arr.add(header);*/
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			UserManagementClass data=new UserManagementClass();
			data.setId(cursor.getInt(0));
			data.setUsername(cursor.getString(1));
			data.setEmail(cursor.getString(4));
			arr.add(data);
			cursor.moveToNext();
		}
		cursor.close();
	}
}	
	
	
	////////////////////////////////////////query the specific user from database//////////////////////////
	public void query(int position)
	{
		//SQLiteDatabase database=openOrCreateDatabase(tenfile, SQLiteDatabase.CREATE_IF_NECESSARY, null);
		if(database!=null)
		{
			UserManagementClass dataquery = (UserManagementClass) arr.get(position);
			String selection = "username=? and email=?";
			//String selectionArgs = "";
			
			Cursor cursor=database.query(true,"tblUser", null, 
											selection, 
											new String[] {dataquery.getUsername(),dataquery.getEmail()},
											null, null, "id", null);
			    cursor.moveToFirst();
				Bundle b = new Bundle();
				UserDetail ud = new UserDetail();
				ud.setId(cursor.getInt(0));
				ud.setUsername(cursor.getString(1));
				ud.setPassword(cursor.getString(2));
				ud.setFullname(cursor.getString(3));
				ud.setEmail(cursor.getString(4));
				ud.setStatus((cursor.getInt(5)==1));
				b.putSerializable("userdetail", ud);		//userdetail is Serializable
				Intent i3 = new Intent(getApplicationContext(), DetailActivity.class);
				i3.putExtra("ud",b);   //ud is bundle
				cursor.close();
				startActivityForResult(i3,UPDATE_REQUEST);
				finish();
		}
		
	
}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode==UPDATE_SUCCESS){
			Bundle b = new Bundle();
			UserDetail ud1 = (UserDetail) b.getSerializable("userdetail");
			Toast.makeText(getApplicationContext(), ""+ud1.getFullname(), Toast.LENGTH_SHORT).show();
			
		}
	}



}
