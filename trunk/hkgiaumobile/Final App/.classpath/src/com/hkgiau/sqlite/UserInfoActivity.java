package com.hkgiau.sqlite;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UserInfoActivity extends ClassActivity {
	private DBAdapter db;
	
	private EditText txt_username;
	private EditText txt_pass;
	private EditText txt_fullname;
	private EditText txt_email;
	private Spinner sp_status;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		String[] arrStatus = {"Active", "Inactive"};
		
		//getui
		txt_username=(EditText)findViewById(R.id.txt_username);
		txt_pass=(EditText)findViewById(R.id.txt_pass);
		txt_fullname=(EditText)findViewById(R.id.txt_fullname);
		txt_email=(EditText)findViewById(R.id.txt_email);
		sp_status=(Spinner)findViewById(R.id.sp_status);
		
		//set data spinner
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_dropdown_item,arrStatus);
		sp_status.setAdapter(adapter);			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.getItem(2).setEnabled(false);
		return true;
	}
	public void BackUser(View view)
	{
		Intent it_user=new Intent(this, UserActivity.class);
		startActivity(it_user);
		finish();
	}
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void SaveData(View view)
	{
		//save database
		db = new DBAdapter(this);
		//create user if haven't		
		String username = txt_username.getText().toString();
		String pass = txt_pass.getText().toString();
		String fullname = txt_fullname.getText().toString();
		String email = txt_email.getText().toString();
		int status = (sp_status.getSelectedItemPosition()==0)?1:0;
		
		if(username.isEmpty())
		{
			Toast.makeText(UserInfoActivity.this, "Username is not empty",
					Toast.LENGTH_LONG).show();
		}
		else if(pass.isEmpty())
		{
			Toast.makeText(UserInfoActivity.this, "Password is not empty",
					Toast.LENGTH_LONG).show();
		}
		else if(fullname.isEmpty())
		{
			Toast.makeText(UserInfoActivity.this, "Fullname is not empty",
					Toast.LENGTH_LONG).show();
		}
		else if(email.isEmpty())
		{
			Toast.makeText(UserInfoActivity.this, "Email is not empty",
					Toast.LENGTH_LONG).show();
		}
		else
		{
			int checkuser = db.getUser(username);
			if(checkuser==0)
			{
				Toast.makeText(UserInfoActivity.this, "Add user successful",
						Toast.LENGTH_LONG).show();
				db.createUser(username, pass, fullname, email, status, 0);
				txt_username.setText("");
				txt_pass.setText("");
				txt_fullname.setText("");
				txt_email.setText("");
			}
			else
				Toast.makeText(UserInfoActivity.this, "Username is exist, please choose another name.",
						Toast.LENGTH_LONG).show();
		}
	}

}
