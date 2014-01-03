package com.hoangphan.tutor0501_pref;

import java.util.HashSet;
import java.util.Set;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	protected static final String ARRAY_PRESIDENT_KEY = null;
	private EditText txtPref;
	private SeekBar skFont;
	private Button btnSave;
	private SharedPreferences prefs;
	
	public final String FONT_SIZE_KEY = "fontsize";
	public final String EDIT_TEXT_KEY = "preftext";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * press button, save size of text and text to preference
		 * 1-get textsize, text
		 * 2-get storage of prefrence
		 * 3-editor -> save
		 * 4-commit
		 */
	    txtPref = (EditText) findViewById(R.id.txtPref);
	    skFont = (SeekBar) findViewById(R.id.skSize);
	    btnSave = (Button) findViewById(R.id.btnSave);
	    
	    prefs = getPreferences(MODE_PRIVATE);
	    //if exist, get from FONT-SIZE_KEY, else not, default = 8
	    float size = prefs.getFloat(FONT_SIZE_KEY, 8);
	    //if exist, get from EDIT_TEXT_KEY, else not, default = "Hello"
	    String txt = prefs.getString(EDIT_TEXT_KEY, "Hello");
	    
	    //set into each view
	    skFont.setProgress((int) size);
	    txtPref.setText(txt);
	    
	    
	    skFont.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	        
	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	        }
	        
	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	        }
	        
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	          txtPref.setTextSize(progress);
	          Toast.makeText(getBaseContext(), "Font now are "+txtPref.getTextSize()+" and progess are " +progress, Toast.LENGTH_SHORT).show();
	        }
	      });	
	    
	    btnSave.setOnClickListener(new View.OnClickListener() {
	        
	        @Override
	        public void onClick(View v) {
	          SharedPreferences prefs = getPreferences(MODE_PRIVATE);
	          //editor for pref, same as bundle
	          Editor editor = prefs.edit();
	          editor.putFloat(FONT_SIZE_KEY, txtPref.getTextSize());
	          editor.putString(EDIT_TEXT_KEY, txtPref.getText().toString());
	          
	          Set<String> list = new HashSet<String>();
	          list.add("HCM");
	          list.add("VNG");
	          editor.putStringSet(ARRAY_PRESIDENT_KEY, list);
	          
	          //save pref
	          editor.commit();
	          Toast.makeText(getBaseContext(), "OK, "+txtPref.getText().toString()+" with  "+
	              txtPref.getTextSize()+"pt are saves", Toast.LENGTH_SHORT ).show();
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
