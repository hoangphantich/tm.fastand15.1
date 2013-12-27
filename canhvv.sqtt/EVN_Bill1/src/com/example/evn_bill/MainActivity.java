package com.example.evn_bill;

import java.text.DecimalFormat;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {	
	EditText NameID;
	EditText QuanID;
	EditText TotalID;
	EditText VatID;
	EditText TotalInID;
	Button CalID;
	TextView selection;
	GridView GvID;
	Button PrintID;
	String msg;
	//Button btnStartProgress;
	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();
 
	private long fileSize = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NameID = (EditText)findViewById(R.id.NameID);
		QuanID = (EditText)findViewById(R.id.QuanID);
		TotalID = (EditText)findViewById(R.id.TotalID);
		VatID = (EditText)findViewById(R.id.VatID);
		TotalInID = (EditText)findViewById(R.id.TotalInID);
		CalID = (Button) findViewById(R.id.CalID);
		PrintID = (Button) findViewById(R.id.PrintID);
		GvID = (GridView) findViewById(R.id.GvID);
		
		addListenerOnButton();
		CalID.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DataProcedd();
				
			}
		});
			
 }
	
	public void addListenerOnButton() {
		 
		PrintID = (Button) findViewById(R.id.PrintID);
		PrintID.setOnClickListener( new View.OnClickListener() {
			
			 		   @SuppressWarnings("deprecation")
					@Override
		   public void onClick(View v) {
 
			// prepare for a progress bar dialog
			progressBar = new ProgressDialog(v.getContext());
			progressBar.setCancelable(true);
			progressBar.setMessage("File downloading ...");
			progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressBar.setProgress(0);
			progressBar.setMax(100);
			progressBar.setButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.d("Button","OK Press");
					Toast.makeText(getButtonContext(), "OK Press", Toast.LENGTH_SHORT);				
					
				}

				private Context getButtonContext() {
					// TODO Auto-generated method stub
					return null;
				}
			});
			//progressBar.set
			progressBar.show();
 
			//reset progress bar status
			progressBarStatus = 0;
 
			//reset filesize
			fileSize = 0;
 
			new Thread(new Runnable() {
			  public void run() {
				while (progressBarStatus < 100) {
 
				  // process some tasks
				  progressBarStatus = doSomeTasks();
 
				  // your computer is too fast, sleep 1 second
				  try {
					Thread.sleep(1000);
				  } catch (InterruptedException e) {
					e.printStackTrace();
				  }
 
				  // Update the progress bar
				  progressBarHandler.post(new Runnable() {
					public void run() {
					  progressBar.setProgress(progressBarStatus);
					}
				  });
				}
 
				// ok, file is downloaded,
				if (progressBarStatus >= 100) {
 
					// sleep 2 seconds, so that you can see the 100%
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// close the progress bar dialog
					progressBar.dismiss();
				}
			  }
		       }).start();
 
	           }
 
                });
 
        }
 
	// file download simulator... a really simple
	public int doSomeTasks() {
 
		while (fileSize <= 1000000) {
 
			fileSize++;
 
			if (fileSize == 100000) {
				return 10;
			} else if (fileSize == 200000) {
				return 20;
			} else if (fileSize == 300000) {
				return 30;
			}
			// ...add your own
 
		}
 
		return 100;
 
	}

private void DataProcedd()
{
	
	final GridView grid = (GridView) findViewById(R.id.GvID);       
        ArrayList<String> mylist = new ArrayList<String>();
        String Qual = QuanID.getText().toString();
        int Qua = Integer.parseInt(Qual);
        int muc=100;
        int gia = 1000;
        int tong = 0;
       while ((Qua-muc)>=0)
        {
        	
        	String X1 = Integer.toString(muc);
        	mylist.add(X1);
        	String X2 = Integer.toString(gia);
        	mylist.add(X2);
        	 int tong1 = (muc)*gia;
        	 tong +=tong1;
        	String X3 = Integer.toString((muc)*gia);
        	mylist.add(X3);
        	Qua = Qua - muc;
        	muc = 50;
        	gia = gia+500;        	
        }
       if (Qua>0)
       {
    	   
    	   String X1 = Integer.toString(Qua);
	       	mylist.add(X1);
	       	String X2 = Integer.toString(gia);
	       	mylist.add(X2);
	       	 int tong1 = (Qua)*gia;
	       	 tong +=tong1;
	       	String X3 = Integer.toString((Qua)*gia);
	       	mylist.add(X3);
       }
        
        ArrayAdapter<String>da=new ArrayAdapter<String>
        (
        this,
        android.R.layout.simple_list_item_1,
        mylist
        );
        //gán Datasource vào GridView
        grid.setAdapter(da);
        TotalID.setText(tong+"");
        DecimalFormat dfc = new DecimalFormat("#.00");
		String totalx = dfc.format(tong/10);		
        VatID.setText(totalx);
        totalx = dfc.format(tong*(1.1));
        TotalInID.setText(totalx);
   
}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	// Assume it's known
  

}
