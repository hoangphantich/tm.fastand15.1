package com.hoangphan.tutor0201_helloworld;

import android.os.*;
import android.app.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
		
		TextView helloTxt;
		EditText nameEdit;

		public class NameListener implements View.OnKeyListener {
		
			public boolean onKey(View v, int keyCode, KeyEvent event){
				if(KeyEvent.ACTION_DOWN == event.getAction() && KeyEvent.KEYCODE_ENTER == keyCode){
					String name = nameEdit.getText().toString();
					nameEdit.setText("");//reset
					helloTxt.setText("Welcome to Android world, "+name+".");
					return true;
				} else {
					return false;
				}
			}			
		}


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        //create textview hello world, like swing
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        helloTxt = new TextView(this);
        nameEdit = new EditText(this);
        helloTxt.setText("Welcome to Android world");
        nameEdit.setHint("Enter your name");
        nameEdit.setLines(1);
        
        layout.addView(helloTxt);
        layout.addView(nameEdit);
        
        nameEdit.setOnKeyListener(new NameListener());
        setContentView(layout);
    }
}
