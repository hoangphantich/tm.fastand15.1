package com.hkgiau.sqlite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.hkgiau.sqlite.R;

public class MainActivity extends ClassActivity {		
	private SharedPreferences pre;
	private DBAdapter db;
	
	private EditText txt_username;
	private EditText txt_pass;
	private String username;
	private String pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//if save password, don't need login
		db = new DBAdapter(this);
		pre=getSharedPreferences(DATA_NAME, MODE_PRIVATE);
		if(pre.getInt(OPTION_SAVEPASS, 0)==1)
		{
			username=pre.getString(OPTION_USERNAME, "");
			pass=pre.getString(OPTION_PASS, "");
			CheckUser(username, pass);
		}
		else
		{
			//getui
			txt_username=(EditText)findViewById(R.id.txt_username);
			txt_pass=(EditText)findViewById(R.id.txt_pass);			
		}
		
		//create user admin if haven't				
		int checkuser = db.getUser("admin");
		if(checkuser==0)
		{
			db.createUser("admin", "admin","Admin","admin@gmail.com",1,1);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//login
	public void Login(View view)
	{		
		username=txt_username.getText().toString();
		pass=txt_pass.getText().toString();
		CheckUser(username, pass);
	}
	public void CheckUser(String username, String pass)
	{
		int checkuser = db.getUser(username,pass);
		if(checkuser==1)
		{
			Toast.makeText(MainActivity.this, "Login successful!",
					Toast.LENGTH_LONG).show();
			int permit=db.getPermit(username,pass);
			if (permit==1)
			{
				Intent it=new Intent(this,UserActivity.class);
				startActivity(it);
			}
			else
			{
				Intent it=new Intent(this,CatalogActivity.class);
				startActivity(it);
			}
			SharedPreferences.Editor editor=pre.edit();			
			editor.putString(OPTION_USERNAME, username);
			editor.putString(OPTION_PASS, pass);
			editor.commit();	
			finish();
		}
		else
			Toast.makeText(MainActivity.this, "Wrong username or password!",
				Toast.LENGTH_LONG).show();
	}
}
