package com.example.evn_bill;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {	
	EditText NameID;
	EditText QuanID;
	EditText TotalID;
	EditText VatID;
	EditText TotalInID;
	Button CalID;
	TextView selection;
	GridView GvID;
	
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
		GvID = (GridView) findViewById(R.id.GvID);
		
		CalID.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DataProcedd();
			}
		});
			
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
        VatID.setText((tong/10)+"");
        TotalInID.setText(tong*(1.1)+"");
        
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	// Assume it's known
  

}
