package com.hoangphan.tutor0202_mxflower;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

  private int amount;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    final EditText flowerEdit = (EditText) findViewById(R.id.flowerEdit);
    final Button buyBtn = (Button) findViewById(R.id.buyBtn);
    final TextView orderTxt = (TextView) findViewById(R.id.orderTxt);
    amount = 0;
    
    buyBtn.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
       String flower = flowerEdit.getText().toString();
       if(!flower.equals("")){
         flowerEdit.setText(""); //reset
         
         //add to order
         String before = orderTxt.getText().toString();
         String after = before + "\n" + flower;
         orderTxt.setText(after);
         amount ++;
         
         //flash messenger
         Toast.makeText(getBaseContext(), flower+" add successfully", Toast.LENGTH_SHORT).show();
       }
      }
    });
    
    final Button checkoutBtn = (Button) findViewById(R.id.checkoutBtn);
    checkoutBtn.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        cal.add(Calendar.DATE, 3);
        Date expire = cal.getTime();
        
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String nowStr = formater.format(now);
        String expireStr = formater.format(expire);
        
        String msg = "Thank you for your order for flowers\n"+
            "Money you must pay is: "+amount*1000+"$\n"+
            "I have received your order at "+nowStr+".\n"+
            "If I have not get your money at "+expireStr+", your order will be canceled.\n"+
            "I love you, sir.";
        new AlertDialog.Builder(MainActivity.this)
          .setTitle("Receipt for flower")
          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
              System.out.println("OK");
            }
          })
          .setMessage(msg)
          .show();
          
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
