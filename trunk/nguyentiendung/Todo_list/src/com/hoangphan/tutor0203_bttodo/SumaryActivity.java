package com.hoangphan.tutor0203_bttodo;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.EditText;

public class SumaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sumary);
		EditText txt1 = (EditText) findViewById(R.id.edtSum);
		txt1.setBackgroundResource(R.drawable.lined);
		txt1.setLineSpacing(2, 2);
		txt1.setTextColor(Color.GREEN);
		
		
		Bundle extras = getIntent().getExtras();
	    if (extras != null)

	    {
	       String save = extras.getString("Save");
	       txt1.setText(save+"");
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sumary, menu);
		return true;
	}

}
