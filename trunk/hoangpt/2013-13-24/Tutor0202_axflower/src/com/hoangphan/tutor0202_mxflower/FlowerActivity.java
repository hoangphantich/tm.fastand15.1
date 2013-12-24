package com.hoangphan.tutor0202_mxflower;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FlowerActivity extends Activity {

  //global variable
  private EditText flowerTxt;
  private Button buyBtn;
  private TextView list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flower);
    
    //get componentui
    flowerTxt = (EditText) findViewById(R.id.flowerTxt);
    buyBtn = (Button) findViewById(R.id.buyBtn);
    list = (TextView) findViewById(R.id.list);
    
    //add listener (event)
    buyBtn.setOnClickListener(new View.OnClickListener() {
      //anonymous class
      @Override
      public void onClick(View v) {
        String flower = flowerTxt.getText().toString();
        
        //validation
        if (!flower.equals("")) {
          //reset flowertext
          flowerTxt.setText("");
          //add to list
          String beforeList = list.getText().toString();
          String afterList = beforeList+"\n"+flower; //mokup
          list.setText(afterList);
        }
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.flower, menu);
    return true;
  }

}
