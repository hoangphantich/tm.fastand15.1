package com.hoangphan.tutor0201_helloandroid;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
		TextView helloLbl;
		EditText nameTxt;
		
		/**
		* inner class
		*/
		class NameListener implements View.OnKeyListener {
        	public boolean onKey(View view, int keyCode, KeyEvent event){
        		//if enterkey, display text on say hello
        		if(KeyEvent.ACTION_DOWN == event.getAction() && KeyEvent.KEYCODE_ENTER == keyCode){
        			String name = nameTxt.getText().toString();
        			helloLbl.setText("Nice to meet you, "+name+".");//say
        			nameTxt.setText(""); //reset
        			return true;
        		} else {
	        		return false;
        		}
        	}
		}

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("Hello Android World");
        
        //init layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        //add component, using code to build ui
        helloLbl = new TextView(this);
        helloLbl.setText("Welcome to Android World");
        layout.addView(helloLbl);
        
        nameTxt = new EditText(this);
        nameTxt.setHint("Enter your name");
        nameTxt.setLines(1);
        layout.addView(nameTxt);
        
        nameTxt.setOnKeyListener(new NameListener());
        setContentView(layout);
    }
}
