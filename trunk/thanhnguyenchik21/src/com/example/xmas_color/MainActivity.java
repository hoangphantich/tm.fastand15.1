package com.example.xmas_color;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
        TextView TV;
        Button RedID;
        Button GreenID;
        Button YellowID;
        RadioGroup radioGroup1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                RedID = (Button)findViewById(R.id.RedID);
                GreenID = (Button)findViewById(R.id.GreenID);
                YellowID = (Button)findViewById(R.id.YellowID);
                TV = (TextView)findViewById(R.id.TV);
                radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        RedID.setOnClickListener(new View.OnClickListener() {
                       
                        @Override
                        public void onClick(View arg0) {
                                // TODO Auto-generated method stub
                                TV.setBackgroundColor(Color.RED);
                        }
                });
        GreenID.setOnClickListener(new View.OnClickListener() {
                       
                        @Override
                        public void onClick(View arg0) {
                                // TODO Auto-generated method stub
                                TV.setBackgroundColor(Color.GREEN);
                        }
                });
        YellowID.setOnClickListener(new View.OnClickListener() {
               
                @Override
                public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        TV.setBackgroundColor(Color.YELLOW);
                }
        });
       
        radioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        int CheckID=radioGroup1.getCheckedRadioButtonId();
                        switch(CheckID)
                        {
                        case R.id.RedRa:
                                TV.setBackgroundColor(Color.RED);
                                break;
                        case R.id.GreenRa:
                                TV.setBackgroundColor(Color.GREEN);
                                break;
                        case R.id.YellowRa:
                                TV.setBackgroundColor(Color.YELLOW);
                                break;
                        }                      
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
