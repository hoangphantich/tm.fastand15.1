package com.nguyentiendung.mediaplayer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

public class Game_Caro extends Activity {
	private Game game1;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game1 = new Game(this);
        setContentView(game1);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_caro, menu);
		
		menu.add("Go to Favourite");
menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
Intent goi2=new Intent(Game_Caro.this, VidduActivity.class);
				
				startActivity(goi2);
				finish();
				return false;
			}
		});
		return true;
	}
	
	  @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if ((keyCode == KeyEvent.KEYCODE_BACK)) {


	       	 finish();
	       	 

	            return true;
	        } else if ((keyCode == KeyEvent.KEYCODE_MENU)) {
	        	finish();
	            return true;
	        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
	          
	            return true;
	        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
	            
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }

}
