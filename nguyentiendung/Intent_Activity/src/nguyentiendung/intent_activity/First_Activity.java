package nguyentiendung.intent_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class First_Activity extends Activity {
	private static final int REQUEST_CODE = 0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_);		
	}
	public void goTest(View v){
		Intent i2 = new Intent("android.intent.action.SECOND");
        startActivity(i2);		
	}
	public void goSave(View v){
		Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String phone = extras.getString("osphone");
        String name = extras.getString("name");
        String age = extras.getString("age");
        String work = extras.getString("work");
        String live = extras.getString("live");
        String info ="";
        if (name != null) {
        	info+=name+"\n";
        }
        if (age != null) {
        	info+=age+"\n";
        }
        if (work != null) {
        	info+=work+"\n";
        }
        if (live != null) {
        	info+=live+"\n";
        }
        if (phone != null) {
            info+=phone;
        }
      TextView txtinfo = (TextView) findViewById(R.id.txtView1) ;
      txtinfo.setText(info);
		
	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if(resultCode == RESULT_OK) {
		 TextView txt1 = (TextView) findViewById(R.id.txtView1);
		 String osphone = data.getStringExtra("osphone");
		 txt1.setText("/n ke ke"+osphone);
		 }
		 
	   
}
}
