package com.hoangphan.tutor0203_bttodo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

  private EditText txtWork;
  private EditText txtHour;
  private EditText txtMinute;
  private ListView list;
  private ArrayList<String> listWorks;
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    txtWork = (EditText) findViewById(R.id.txtWork);
    txtHour = (EditText) findViewById(R.id.txtHour);
    txtMinute = (EditText) findViewById(R.id.txtMinute);
    
    //build list view
    list = (ListView) findViewById(R.id.list);
    listWorks = new ArrayList<String>();
    
    //need adapter for input data and layout
    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listWorks);
    list.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
  
  public void onAddWork(View v){
    //validate
    if(txtWork.getText().toString().equals("")){
      AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
      builder.setTitle("Error input");
      builder.setMessage("Not blank for either ...");
      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          System.out.println("OK click");
        }
      });
      builder.show();
    } else {
      String str = txtHour.getText().toString()+":"+txtMinute.getText().toString()
          +" - "+txtWork.getText().toString();
      listWorks.add(str);
      Log.d("add", str);
      adapter.notifyDataSetChanged();
      
      //reset form
      txtWork.setText("");
      txtHour.setText("");
      txtMinute.setText("");
    }
    
  }

}
