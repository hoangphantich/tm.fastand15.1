package tiendung.example.christ_color;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tvw;
	RadioGroup gr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvw = (TextView) findViewById(R.id.textView1);
        Button btn1=(Button) findViewById(R.id.btnred);
        Button btn2=(Button) findViewById(R.id.btngreen);
        Button btn3=(Button) findViewById(R.id.btnyellow);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            tvw.setBackgroundColor(Color.RED);
        }
            });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            tvw.setBackgroundColor(Color.GREEN);
        }
            });
        btn3.setOnClickListener(new View.OnClickListener() {
        public void onClick(View arg0) {
        tvw.setBackgroundColor(Color.YELLOW);
    }
        });
        gr1 = (RadioGroup) findViewById(R.id.radioGroup1);               
        gr1.setOnCheckedChangeListener(new OnCheckedChangeListener() 
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            	checkedId = gr1.getCheckedRadioButtonId();
                switch(checkedId)
                {
                case R.id.radio0:{
                    tvw.setBackgroundColor(Color.RED);
                	break;
                }
                case R.id.radio01:{
                	tvw.setBackgroundColor(Color.GREEN);
                	break;
                }
                case R.id.radio2:{
                	tvw.setBackgroundColor(Color.YELLOW);
                	break;
                }
                // checkedId is the RadioButton selected
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
