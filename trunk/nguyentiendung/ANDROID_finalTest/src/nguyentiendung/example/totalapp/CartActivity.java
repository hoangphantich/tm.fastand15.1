package nguyentiendung.example.totalapp;
///for Cart for Customer
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CartActivity extends Activity {

	ArrayList<KindOfElectric> arr;
	ElectricAdapter adapter;
	ListView lstcart;
	int position =0;
	private int name;
	private String prefname="pref";
	EditText edtcon;
	EditText edtVat;
	private EditText edtpay;
	EditText edtconsumename;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		final Button btnprint = (Button) findViewById(R.id.btnPrint);
		edtpay = (EditText)findViewById(R.id.edtTotal);					//EditText Pay
		edtcon = (EditText) findViewById(R.id.edtconsume); 				//EditText Total
		edtVat = (EditText) findViewById(R.id.edtVAT);					//EditText Vat
		edtconsumename = (EditText) findViewById(R.id.editConsum);
		final Button btnbak = (Button) findViewById(R.id.button1);
		lstcart = (ListView) findViewById(R.id.lstcart);
		arr = new ArrayList<KindOfElectric>();
		//arr.add(new KindOfElectric("50","100","for personal \n consume in\n month 12"));
        adapter= new ElectricAdapter(this, arr);
		lstcart.setAdapter(adapter);
	    restoringPreferences();
		Bundle extras = getIntent().getExtras();
        if (extras == null) {
            
        }
        else { name = extras.getInt("position");
        if(!isExist(name)){
        	arr.add(new KindOfElectric(name,0,getMoney(name,0),getPorpose(name)));
        }}
        adapter.notifyDataSetChanged();
        refresh();
///////////////////////Back Button//////////////////////////////////////		
		btnbak.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(),CatalogActivity.class);
				startActivity(in);
				finish();
			}
		});

////////////////////////Print Invoice Button////////////////////////////////////		
		btnprint.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//edtconsumename.setText("");
				
				if(edtconsumename.getText().toString().equals("")||edtpay.getText().toString().equals("")) {
					AlertDialog.Builder b = new Builder(CartActivity.this);
					b.setTitle("Alert");
					b.setMessage("Consume Name is blank");
					b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
					b.show();
				}
				else {
					final ProgressDialog pd = new ProgressDialog(CartActivity.this);
					pd.setTitle("Printing Invoice");
					pd.setMessage("Please wait...");
					pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					pd.setCancelable(true);
					pd.setProgress(5);
					pd.show();
					printf();
				    new Thread(new Runnable() {
						
						@Override
						public void run() {
							for (int i = 0; i < 15; i++) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								pd.incrementProgressBy(100/15);
							}
							pd.dismiss();
						
						}
					}).start();
					
				}
			}
		});
		
		
	}


	
	public void printf(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		Date date = new Date();
		String datetime = (String) dateFormat.format(date);
		String tenfile = edtconsumename.getText().toString()+datetime+".txt";
	    File sdcard = Environment.getExternalStorageDirectory();
	    File directory = new File(sdcard.getAbsolutePath() + "/Tien-Dung");
	    directory.mkdirs();
	    File file = new File(directory, tenfile);
	    FileOutputStream out;
	    String str = "  "+edtconsumename.getText().toString()+"\n need pay " + edtpay.getText().toString();
		try {
			out = new FileOutputStream(file, true); //append to last
		    OutputStreamWriter writer = new OutputStreamWriter(out);
		    writer.write(str);
		    writer.flush();
		    writer.close();
		    Toast.makeText(getApplicationContext(), "write file successfull", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "Can't write file", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	
	
	
////////////////////godelete////////////////////////////////////////
	public void godelete(View v){
		int position1 = lstcart.getPositionForView((View) v.getParent());
		arr.remove(position1);
		
		adapter.notifyDataSetChanged();
		refresh();
	}
	
	public void gopr(View v)	{
		
		v.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				//position = lstcart.getPositionForView((View) v.getParent());
				if(KeyEvent.KEYCODE_ENTER==keyCode){
					position = lstcart.getPositionForView((View) v.getParent()); //get position of EditText
					EditText edt1 = (EditText)v;						//get Edittext
					String text = edt1.getText().toString();			//get Text of EditText
					int mon = Integer.parseInt(text);				//convert Text of EditText to integer equal number of electric
					arr.get(position).setNumber(mon);				//set value for KindOfElectric
					int id = arr.get(position).getId();				//get Id of element that chosen
					arr.get(position).setMoney(getMoney(id,mon));	//set the money property for KindOfElectric
					//adapter.notifyDataSetChanged();					//update listview
					refresh();
				}
				return false;
			}
		});
		
	}
	
public void refresh(){
	DecimalFormat df = new DecimalFormat("#,###.#");
	float totalmoney=0.0f;
	for(int k = 0 ; k<lstcart.getCount();k++){
		totalmoney+= Float.parseFloat(getMoney(arr.get(k).getId(),arr.get(k).getNumber()));
	}
	edtcon.setText(""+df.format(totalmoney)+"vnd");
	edtVat.setText("10%");
	edtpay.setText(""+df.format(totalmoney*1.1f)+"vnd");
	adapter.notifyDataSetChanged();	
}
@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		savingPreferences();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		restoringPreferences();
	}


/////////////////////save Preferences////////////////////////////////////	
public void savingPreferences()
		{
				SharedPreferences pre=getSharedPreferences(prefname, MODE_PRIVATE);  //create the object SharedPreferences
				SharedPreferences.Editor editor=pre.edit(); 		//Create the Editor for SharedPreferences
				int size =lstcart.getCount();		//get text from EditText Font Size
				editor.putInt("size", size);
				
				for(int i=0;i<size;i++){
					editor.putInt("id"+i, arr.get(i).getId());
					editor.putInt("number"+i, arr.get(i).getNumber());
				}
				editor.commit();									//save editor to file
		}
////////////////////restoring preference///////////////////////////////////////
public void restoringPreferences()					//retrieve data method
		{
				SharedPreferences pre=getSharedPreferences(prefname,MODE_PRIVATE);
				
				int size=pre.getInt("size", 0);				//retrieve text size
				for(int i=0;i<size;i++){
					int id = pre.getInt("id"+i, 0);
					int number = pre.getInt("number"+i, 0);
					if(!isExist(id)){	
					arr.add(new KindOfElectric(id, number, getMoney(id,number) , getPorpose(id)));
						}
				}
				adapter.notifyDataSetChanged();
		}
public String getMoney(int id,int number){
	String value="";
	switch (id) {
	case 0:
		value=""+number*1000;
		break;
	case 1:
		value=""+number*2000;
		break;
	case 2:
		value=""+number*500;
		break;
	default:
		break;
	}
	return value;
}
public String getPorpose(int id){
	String value="";
	switch (id) {
	case 0:
		value="For Personal\n Consume in\n month 12";
		break;
	case 1:
		value="For Office\n Consume in\n month 12";
		break;
	case 2:
		value="For Product\n Consume in\n month 12";
		break;
	default:
		break;
	}
	return value;
}

public boolean isExist(int id){
	for (int a=0;a<lstcart.getCount();a++)
		if(arr.get(a).getId()==id) return true;
	
	return false;
}
}
