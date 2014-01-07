package com.hkgiau.sqlite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.hkgiau.sqlite.R;

public class OptionActivity extends ClassActivity implements OnClickListener{
	
	private SharedPreferences pre;	
	private CheckBox ch_savepass;
	private RadioButton rd_english;
	private RadioButton rd_vietnamese;
	private Spinner cb_data;
	
	private int savepass;
	private int language;	
	private int data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		
		final String[] arrData = {"EVN Server", "Local"};
		//get ui
		ch_savepass=(CheckBox)findViewById(R.id.ch_savepass);
		rd_english=(RadioButton)findViewById(R.id.rd_english);
		rd_vietnamese=(RadioButton)findViewById(R.id.rd_vietnamese);		
		cb_data = (Spinner)findViewById(R.id.cb_data);	
		
		//set data spinner
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_dropdown_item, arrData);
		cb_data.setAdapter(adapter);		
				
//		Toast.makeText(OptionActivity.this,			
//		              String.valueOf(),
//					Toast.LENGTH_SHORT).show();
		
		//load data
		pre=getSharedPreferences(DATA_NAME, MODE_PRIVATE);
		savepass=pre.getInt(OPTION_SAVEPASS, 0);
		language=pre.getInt(OPTION_LANGUAGE, 0);
		data =pre.getInt(OPTION_DATA, 0);
		
		//set data
		if(savepass==1)
			ch_savepass.setChecked(true);
		else
			ch_savepass.setChecked(false);
		if(language==0)
			rd_english.setChecked(true);
		else
			rd_vietnamese.setChecked(true);
		if(data==1)
			cb_data.setSelection(1);
		else
			cb_data.setSelection(0);
		
		//add listener
		ch_savepass.setOnClickListener(this);
		rd_english.setOnClickListener(this);
		rd_vietnamese.setOnClickListener(this);
		cb_data.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(cb_data.getSelectedItemPosition()==0)
					Toast.makeText(OptionActivity.this,"You select EVN Server",Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(OptionActivity.this,"You select local",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.getItem(0).setEnabled(false);
		menu.getItem(2).setEnabled(false);
		return true;
	}
	public void Save(View view)
	{		
		SharedPreferences.Editor editor=pre.edit();
		savepass=(ch_savepass.isChecked())?1:0;		
		language=(rd_english.isChecked())?0:1;
		data=cb_data.getSelectedItemPosition();
		
		editor.putInt(OPTION_SAVEPASS, savepass);
		editor.putInt(OPTION_LANGUAGE, language);
		editor.putInt(OPTION_DATA, data);
		editor.commit();	
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ch_savepass:
			if(ch_savepass.isChecked())
				Toast.makeText(OptionActivity.this,"You select save pass",Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(OptionActivity.this,"You don't select save pass",Toast.LENGTH_SHORT).show();
			break;
		case R.id.rd_english:			
				Toast.makeText(OptionActivity.this,"You select English",Toast.LENGTH_SHORT).show();
				break;
		case R.id.rd_vietnamese:	
				Toast.makeText(OptionActivity.this,"You select Vietnamese",Toast.LENGTH_SHORT).show();
			break;			
		default:
			break;
		}
	}
}
