package com.hoangphan.tutor0202_mxflower;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    final EditText flowerEdit = (EditText) findViewById(R.id.flowerEdit);
    final Button buyBtn = (Button) findViewById(R.id.buyBtn);
    final TextView orderTxt = (TextView) findViewById(R.id.orderTxt);
    
    buyBtn.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
       String flower = flowerEdit.getText().toString();
       flowerEdit.setText("");
       
       //add to order
       String before = orderTxt.getText().toString();
       String after = before + "\n" + flower;
       orderTxt.setText(after);
       
       //flash messenger
       Toast.makeText(getBaseContext(), flower+" add successfully", Toast.LENGTH_SHORT).show();
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
