package nguyentiendung.intent_activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class FourActivity extends Activity {

	ListView lsvphone;
	String os;
	private String name;
	private String age;
	private String work;
	private String live;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four);
		Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        name = extras.getString("name");
        age = extras.getString("age");
        work = extras.getString("work");
        live = extras.getString("live");
        
		Button btnx = (Button) findViewById(R.id.btngomain4);
		// lay listview ra de hien thi		
		lsvphone = (ListView) findViewById(R.id.lsvphone);
		//khoi tao mang chua cac contacts
		ArrayList<Contact> osphone = new ArrayList<Contact>();
		osphone.add(new Contact("Apple ios",R.drawable.contact1));
		osphone.add(new Contact("Microsoft Windows Phone",R.drawable.contact));
		osphone.add(new Contact("Google Android",R.drawable.contact2));
		//noi mang voi listview bang MyAdapter
        MyAdapter adapter = new MyAdapter(this, lsvphone.getId(), osphone);		
		lsvphone.setAdapter(adapter);
		lsvphone.setOnItemClickListener(
				 new AdapterView.OnItemClickListener() {
				 public void onItemClick(AdapterView<?> arg0,
				 View arg1,
				 int arg2,
				 long arg3) {
				 //đối số arg2 là vị trí phần tử trong Data Source (arr)
				Contact li = (Contact) lsvphone.getItemAtPosition(arg2);
				os=(String)li.getName();				
				 }
				 });
//////////////////////////////////////////////////////
		btnx.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
	        	if(os==null){
	            AlertDialog.Builder b=new AlertDialog.Builder(FourActivity.this);
	        	b.setTitle("Question");
	        	b.setMessage("Bạn dùng điện thoại nào?");
	        	b.setPositiveButton("Ok", new DialogInterface. OnClickListener() {
	        	@Override
	        	public void onClick(DialogInterface dialog, int which)
	        	{
	        	dialog.cancel();
	        	}});
	        	
	        	b.create().show();
	        	}
	        	else {
	        Intent i1=new Intent(getApplicationContext(),First_Activity.class); // Hàm này  tạo Intent SubActivity1
	        //Intent i1=new Intent(); // Hàm này  tạo Intent SubActivity1
	        i1.putExtra("name", "Name: " +name);
	        i1.putExtra("age", "Age: " +age);
	        i1.putExtra("work", "Work at: " +work);
	        i1.putExtra("live", "Live at: " +live);
	        i1.putExtra("osphone", "Using Phone: " +os);
	        startActivity(i1); // Hàm này khởi tạo intent vừa tạo ở trên
	        finish();
	        	}
	   }
	        });
		
		
	}

	

}
