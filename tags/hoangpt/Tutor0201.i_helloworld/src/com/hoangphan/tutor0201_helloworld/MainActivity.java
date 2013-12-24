package com.hoangphan.tutor0201_helloworld;

import java.io.IOException;
import java.io.InputStream;

import android.os.*;
import android.app.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
		TextView helloTxt;
		EditText nameEdit;
    private LinearLayout layout;

		public class NameListener implements View.OnKeyListener {
		
			public boolean onKey(View v, int keyCode, KeyEvent event){
				if(KeyEvent.ACTION_DOWN == event.getAction() && KeyEvent.KEYCODE_ENTER == keyCode){
				  Context app = getBaseContext();
				  String name = nameEdit.getText().toString();
					
					nameEdit.setText("");//reset

					TextView sayText = new TextView(app);
	        sayText.setText("Nice to meet you, "+name+".");
	        layout.addView(sayText);

	        int x = 4, y =5;
	        int z = x+y;
	        TextView abilityText = new TextView(app);
	        abilityText.setText("I can:\n");
	        abilityText.append("- do math: "+x+"+"+y+"="+(z));
	        abilityText.append("\n");
	        
	        String s1 = "Com";
	        String s2 = "Pho";
	        String s = s1+s2;
	        abilityText.append("- do concatenate: "+s1+" and "+s2+" = "+s);
	        layout.addView(abilityText);
	        
	        TextView photoText = new TextView(app);
	        photoText.setText("My cool photo:");
	        layout.addView(photoText);
	        
	        ImageView photoImg = new ImageView(app);
	        InputStream is = null;
	        try {
            is = app.getAssets().open("rocker.jpg");
          } catch (IOException e) {
          }
	        Bitmap image = BitmapFactory.decodeStream(is);
	        photoImg.setImageBitmap(image);
	        photoImg.setScaleType(ImageView.ScaleType.FIT_CENTER);
	        layout.addView(photoImg);
	        
					return true;
				} else {
					return false;
				}
			}			
		}


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create textview hello world, like swing
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        helloTxt = new TextView(this);
        nameEdit = new EditText(this);
        helloTxt.setText("Welcome to Android world, myname is Minon_andr. What your name:");
        nameEdit.setHint("Enter your name");
        nameEdit.setLines(1);
        
        layout.addView(helloTxt);
        layout.addView(nameEdit);
        
        nameEdit.setOnKeyListener(new NameListener());
        setContentView(layout);
    }
}
