package com.hoangphan.tutor0303_dog;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class DogActivity extends Activity {

  private static final int DLG_NORMAL = 0;
  private static final int DLG_WAITING = 1;
  private static final int DLG_DOWNLOAD = 2;
  
  private ImageView dog;
  private ProgressDialog pgr2;
  private SeekBar seekBar1;
 Button  btnDog; 
boolean advancing = true;

  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dog);
    seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
    btnDog = (Button)findViewById(R.id.btnDog);
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.dog, menu);
    return true;
  }
  
  public void showBrowser(View v){
	  //android.r.layout
    Intent i1 = new Intent("android.intent.action.VIEW");
    Intent i2 = new Intent(android.content.Intent.ACTION_VIEW);
    i2.setData(Uri.parse("http://vnexpress.net"));
    startActivity(i2);
  }
  
  @Override
  protected Dialog onCreateDialog(int dialogID) {
    
    switch (dialogID) {
    case DLG_NORMAL:
      return _createNormal();
    case DLG_WAITING:
      return _createWaiting();
    case DLG_DOWNLOAD:
    	return _createDownload();
    	//displayProgressBar();
    default:
      break;
    }
    
    return null;
  }

  int initial1 = 0;
  private Dialog _createDownload() {
	pgr2 = new ProgressDialog(this);  	
	pgr2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	pgr2.setMessage(getString(R.string.msg));
	pgr2.setTitle("Processing...");
	pgr2.setCancelable(false);
	pgr2.setProgress(0);	 
	pgr2.setMax(100);
	
	pgr2.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	    	pgr2.setProgress(0);	
	    	pgr2.dismiss();
	        dialog.dismiss();
	    }
	});
	return pgr2;
}

private Dialog _createWaiting() {
    //animation
    final ProgressDialog pgr1 = ProgressDialog.show(this, "Waiting", "We are retrieving database", true);
    
    new Thread(new Runnable() {
      
      @Override
      public void run() {
        try {
          Thread.sleep(5000);
          pgr1.dismiss();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }).start();
    return pgr1;
  }

  private Dialog _createNormal() {
    final String[] presidents = {"Hochiminh", "Vonguyengiap", "Vovankiet"};
    //chaining
    return new AlertDialog.Builder(this)
    .setTitle("Please choose president")
    .setMultiChoiceItems(presidents, new boolean[presidents.length], new DialogInterface.OnMultiChoiceClickListener() {
      
      @Override
      public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if(isChecked){ //if check display toaster
          String preName = presidents[which];
          Toast.makeText(getBaseContext(), "You have choose "+preName, Toast.LENGTH_SHORT).show();
        }
      }
    })
    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
      
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        
      }
    })
    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
    	  
        
      }
    })
    .setNeutralButton("Yes", new DialogInterface.OnClickListener() {
      
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        
      }
    })
    .create();
    }

  /**
   * implicit
   * @param v
   */
  public void showNormal(View v){
    showDialog(DLG_NORMAL);
  }
  
  public void showWaiting(View v){
    showDialog(DLG_WAITING);
  }
  public void showDownloading(View v){
    showDialog(DLG_DOWNLOAD);
    new Thread(new Runnable() {
		
		@Override
		public void run() {
			for (int i = 0; i < pgr2.getMax(); i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pgr2.incrementProgressBy(pgr2.getMax()/15);
			}
			pgr2.dismiss();
		}
	}).start();
   
  }
  
   int i = 0, count;
private TextView textView;
private Timer timer;
  public void showDog(View v){
	 
	  seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if(fromUser) count = progress;
			// TODO Auto-generated method stub
			
		}
	});
	
    dog = (ImageView) findViewById(R.id.dogImg);
    dog.setImageBitmap(BitmapFactory.decodeFile("/sdcard/Download/dog/T0.gif"));
    if (timer ==null)
    {
     timer = new Timer();
     btnDog.setText("Pause Dog");
    timer.scheduleAtFixedRate(new TimerTask() {

        @Override
        public void run() {
            _timerMethod();
        }
    }, 0,1000);
    }
    else
    {
    	timer.cancel();
    	timer=null;
    	btnDog.setText("Play Dog");
    }
  }
  
  
  private void _timerMethod() {
	  textView = (TextView)findViewById(R.id.textView);
	  
    runOnUiThread(new Runnable() {    	
    	

	@Override
      public void run() {
	
            int j = i%14;
            String pathImg = "/sdcard/Download/dog/T"+j+".gif";
            dog.setImageBitmap(BitmapFactory.decodeFile(pathImg));
            i++;
            //onStopTrackingTouch(i);
            if (i>13)
            	i=0;
          seekBar1.setProgress(i);
          textView.setText(i+"");      
		
	}
    });      
  }
  
 

}
