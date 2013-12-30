package nguyentiendung.intent_activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ThirdActivity extends Activity {
	
	String name;
	String age;
	int workpos=0;
	int livepos=0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		//lay name va age tu activity truoc
		Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        name = extras.getString("name");
        age = extras.getString("age");
       
		final String arr[]=getResources().getStringArray(R.array.provinceses);
		final String arr1[]=getResources().getStringArray(R.array.companies);
		Spinner spnwork = (Spinner) findViewById(R.id.spnWork);
		Spinner spnpro	 = (Spinner) findViewById(R.id.spnLive);
		ArrayAdapter<String> adapterpro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
		ArrayAdapter<String> adapterwork = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr1);
		spnwork.setAdapter(adapterwork);
		spnpro.setAdapter(adapterpro);
		adapterpro.setDropDownViewResource
		 (android.R.layout.simple_spinner_dropdown_item);
		adapterwork.setDropDownViewResource
		 (android.R.layout.simple_spinner_dropdown_item);
		spnwork.setOnItemSelectedListener(new MyProcess1());
		spnpro.setOnItemSelectedListener(new MyProcess());
		Button btngomain = (Button) findViewById(R.id.btngomain4);
		Button btngo4 = (Button) findViewById(R.id.btngo4);
// su kien click cho nut go main
		btngomain.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				finish(); 
				
			}
			
		});
	//// su kien click cho nut Go 4	
		btngo4.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
	        	String work = arr1[workpos];
	        	String live = arr[livepos];
	        	 Intent i2=new Intent(getApplicationContext(), FourActivity.class); // Hàm này  tạo Intent SubActivity1
	        	 i2.putExtra("name", name);
	        	 i2.putExtra("age", age);
	        	 i2.putExtra("work", work);
	             i2.putExtra("live", live); 
	        	 startActivity(i2); // Hàm này khởi tạo intent vừa tạo ở trên
	             finish();
	        }

	            });
		
	}
	
		private class MyProcess1 implements OnItemSelectedListener {
			
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				workpos=arg2;
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		}
		private class MyProcess implements OnItemSelectedListener {
			
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				livepos=arg2;
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		}		
public void goMain(View v){
	   Intent i1 = new Intent(getApplicationContext(),First_Activity.class);
	   startActivity(i1);
	   finish();
	   
}
}