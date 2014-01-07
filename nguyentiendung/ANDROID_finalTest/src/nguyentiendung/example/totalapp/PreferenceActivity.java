package nguyentiendung.example.totalapp;
////////////////////for Option menu////////////////////////////
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PreferenceActivity extends Activity {
    public	CheckBox chkSave;
	RadioGroup rg1;
	private Spinner spn;
	String arr[] = {"Local Server","EVN Server"};
	public String prefname = "savefile";
	public int lang=0;
	public int server = 0;
	public boolean check;
	private TextView txtlang;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference);
		rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
		chkSave = (CheckBox) findViewById(R.id.checkBox1);
		spn = (Spinner) findViewById(R.id.spnServer);
		txtlang = (TextView) findViewById(R.id.txtPorpose);
		
/////////////proccessing RadioGroup//////////////////////////////////////////////////				
		rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {	
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				int id = rg1.getCheckedRadioButtonId();
				viewtoast(id);
			}
		});
////////////////////processing CheckBox///////////////////////////////////////////////////
		chkSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					showtoast(true);
				}
				else showtoast(false);
			}
		});
////////////////////////process Spinner//////////////////////////////////////////////////////////
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
		spn.setAdapter(adapter);	
		spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				server=arg2;
				viewlang(server);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		restoringPreferences();
}	

	protected void viewlang(int server2) {
		// TODO Auto-generated method stub
		if(server2==1)
		Toast.makeText(this, "you choose EVN Server", Toast.LENGTH_SHORT).show();
		else Toast.makeText(this, "you choose Local Server", Toast.LENGTH_SHORT).show();
	}

	
	protected void viewtoast(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case R.id.radio0:
		    Toast.makeText(this, "you choose English language", Toast.LENGTH_SHORT).show();
		    lang=0;
		    txtlang.setText("Language:");
			break;
		case R.id.radio1:
			Toast.makeText(this, "Bạn chọn Tiếng Việt", Toast.LENGTH_SHORT).show();
			lang=1;
			 txtlang.setText("Chọn ngôn ngữ");
		default:
			//Toast.makeText(this, "you need choose a language"+id, Toast.LENGTH_SHORT).show();
			break;
		}
	}	
	protected void showtoast(Boolean ischeck) {
		// TODO Auto-generated method stub
		if(ischeck)
		Toast.makeText(this, "you choose save password",Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "you choose don't save password",Toast.LENGTH_SHORT).show();
	}

	
	
	
	
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		savingPreferences();
	}

	public void savingPreferences()
	 {
	 Boolean status = chkSave.isChecked();
	 SharedPreferences pre=getSharedPreferences(prefname ,  MODE_WORLD_WRITEABLE);  //create the object SharedPreferences
	 SharedPreferences.Editor editor=pre.edit(); 		//Create the Editor for SharedPreferences
	 editor.putInt("lang", lang);						//put language to editor
	 editor.putInt("server", server);					//put server to editor
	 editor.putBoolean("status",status);			//put checkbox password status to editor
	 editor.commit();									//save editor to file
	 }
	
	
	
	 public void restoringPreferences()					//retrieve data method
	 {
	SharedPreferences pre=getSharedPreferences(prefname,MODE_PRIVATE);
	 boolean status=pre.getBoolean("status", false);		//get value of checkbox
	 int language = pre.getInt("lang", 0);
	 int ser = pre.getInt("server", 0);
	 if(status) chkSave.setChecked(true);									//if is checked
	 else chkSave.setChecked(false);
	 spn.setSelection(ser);
	 RadioButton rd0 = (RadioButton) findViewById(R.id.radio0);
	 RadioButton rd1 = (RadioButton) findViewById(R.id.radio1);
	 if(language==0)
	 rd0.setChecked(true);
	 else  rd1.setChecked(true);
	 
	 }
}
