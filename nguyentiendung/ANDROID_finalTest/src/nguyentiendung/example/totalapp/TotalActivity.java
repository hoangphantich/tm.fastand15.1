package nguyentiendung.example.totalapp;
//////////////////////////////Main Activity for login////////////////////////////
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TotalActivity extends Activity {

	private EditText username;
	private EditText password;
	public String prefname = "savefile";
	private SQLiteDatabase database;
	private String tenfile="qluser.db";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total);
		
		username = (EditText) findViewById(R.id.edtuser);
		password = (EditText) findViewById(R.id.edtpassword);
		restoringPreferences();
	}

public void goLogin(View v){
	String nameuser = username.getText().toString();
	String pass =password.getText().toString();
	savingPreferences();
	database = openOrCreateDatabase(tenfile, MODE_PRIVATE, null);	// Open Database for insert
	String selection = "username=? and password=?";
	Cursor cursor=database.query(true,"tblUser", null, 
			selection, 
			new String[] {nameuser,pass},
			null, null, "id", null);
	if(nameuser.equals("admin")&&pass.equals("admin")){
		Intent i1 = new Intent(getApplicationContext(), ManagementActivity.class);
		startActivity(i1);
		finish();
	}
	else if(cursor.getCount()<1){
		AlertDialog.Builder b = new Builder(TotalActivity.this);
		b.setTitle("Warning Dialog");
		b.setMessage("Error! the user account doesn't exist!");
		b.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		b.show();
	}
	else
	{
		Intent i1 = new Intent(getApplicationContext(), CatalogActivity.class);
		startActivity(i1);
		finish();
	}
		
}

public void savingPreferences()
{

SharedPreferences pre=getSharedPreferences(prefname ,  MODE_WORLD_WRITEABLE);  //create the object SharedPreferences
SharedPreferences.Editor editor=pre.edit(); 		//Create the Editor for SharedPreferences
String usernameis = (String) username.getText().toString();
String passwordis = (String) password.getText().toString();
editor.putString("username", usernameis);						//put language to editor
editor.putString("password", passwordis);					//put server to editor
editor.commit();									//save editor to file
}

public void restoringPreferences()					//retrieve data method
{
SharedPreferences pre=getSharedPreferences(prefname,MODE_WORLD_WRITEABLE);
boolean bchk=pre.getBoolean("status", false);		//get value of checkbox
	if(bchk)											//if is checked
			{
		String uname = pre.getString("username", "");
		String pass = pre.getString("password", "");
		username.setText(uname);
		password.setText(pass);
			}
	else {
		username.setText("");
		password.setText("");
	}
	}
}
