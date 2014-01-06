package com.hoangphan.tutor0503_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText txtString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//get ui
	    txtString = (EditText) findViewById(R.id.txtStr);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * view declare
	 * @param v
	 */
	public void onSave(View v){
		//init file
	    File sdcard = Environment.getExternalStorageDirectory();
	    File directory = new File(sdcard.getAbsolutePath() + "/myfiles");
	    directory.mkdirs();
	    File file = new File(directory, "test.txt");
	    
	    FileOutputStream out;
		try {
			//init writer, bridge writer --> outputstream
			out = new FileOutputStream(file, true); //append to last
		    OutputStreamWriter writer = new OutputStreamWriter(out);
		    
		  //write to file
		    String str = txtString.getText().toString();
		    writer.write(str);
		    writer.flush();
		    writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onLoad(View v){
		//init file exist in system
	    File sdcard = Environment.getExternalStorageDirectory();
	    File file = new File(sdcard.getAbsoluteFile()+"/myfiles/test.txt");
	    
	    try {
			//inputstream reader
			FileReader reader = new FileReader(file);
			char[] buffer = new char[100];
			int charRead = 0;
			String strRead = "";
			
			//while read to eof -1, (using flag)
			while ((charRead = reader.read(buffer)) > 0) {
				String strTmp = new String(buffer);
		        strRead += strTmp;
			};
			
			//read --> pop to input ui
			txtString.setText(strRead);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
