package nguyentiendung.example.totalapp;
///////////For User Detail to Add new User or view an user/////////////////
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailActivity extends Activity {

	private Spinner spn;		//spinner status
	SQLiteDatabase database;	//database
	String tenfile="qluser.db";
	private EditText eduser;	//EditText User Name
	private EditText edpass;	//EditText Password
	private EditText edfullname;//EditText Full Name
	private EditText edemail;	//EditText Email
	private int position;		//Position of spinner status item
	private Button btnsave;
	private int _id =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		spn=(Spinner) findViewById(R.id.spnStatus);
		eduser = (EditText) findViewById(R.id.edtUsernam);
		edpass = (EditText) findViewById(R.id.edtPassword);
		edfullname = (EditText) findViewById(R.id.edtFullname);
		edemail = (EditText) findViewById(R.id.edtEmail);
		btnsave = (Button) findViewById(R.id.btnRemove);
		Button btnBack = (Button) findViewById(R.id.btnBacktolist);
		String arr[]= {"Active", "Inactive"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
		spn.setAdapter(adapter);
///////////////////////////get bundle from activity////////////////////		
		Bundle b = getIntent().getBundleExtra("ud");
////////////////if bundle exist then..../////////////////////////////////////////		
		if (b != null) {
			UserDetail ud = (UserDetail) b.getSerializable("userdetail");
			eduser.setText(ud.getUsername());
			edpass.setText(ud.getPassword());
			edfullname.setText(ud.getFullname());
			edemail.setText(ud.getEmail());
			if(ud.getStatus()) spn.setSelection(1);
			else spn.setSelection(0);
			_id=(Integer) ud.getId();
			btnsave.setText("Update");
		}
////////////////process for Button Save or Update ///////////////////////////////////////////////			
			btnsave.setOnClickListener(new View.OnClickListener() {
				


				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					database = openOrCreateDatabase(tenfile, MODE_PRIVATE, null);	// Open Database for insert
					ContentValues value = new ContentValues();
					String usname = eduser.getText().toString();
					String pass = edpass.getText().toString();
					value.put("username", usname);
					value.put("password", pass);
					value.put("fullname", edfullname.getText().toString());
					value.put("email", edemail.getText().toString());
					value.put("status", position);
				
					String selection = "username=? and password=?";
					Cursor cursor=database.query(true,"tblUser", null, 
							selection, 
							new String[] {usname,pass},
							null, null, "id", null);
					if(btnsave.getText().equals("Update"))
						database.update("tblUser", value, "id="+_id,null);
					else {
							if(cursor.getCount()<=0){								
							 database.insert("tblUser", null, value);
								database.close();
							   }
							else
							{
								AlertDialog.Builder b = new Builder(DetailActivity.this);
								b.setTitle("Warning Dialog");
								b.setMessage("Error! the user account exist!");
								b.setPositiveButton("OK", new OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										dialog.cancel();
									}
								});
								b.show();
							}
							}
				}
			});
///////////////////process for item spinner choosing///////////////////////////////			
			spn.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					position=arg2;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
	
/////////////////////////////process for Back to list Button///////////////////////////////////		
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(getApplicationContext(), ManagementActivity.class);
				setResult(ManagementActivity.UPDATE_SUCCESS, i1);
				startActivity(i1);
				finish();
			}
		});
	
	}


	
	
}
