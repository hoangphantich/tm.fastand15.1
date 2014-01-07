package nguyentiendung.example.totalapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingActivity extends Activity {

	private String prefname="temfile";  // filename cho pref
	private EditText fontsize;          // EditText for FontSize
	private CheckBox chkcookie;			// Checkbox for cookie
	private Spinner spnlang;			// Spinner choose language
	String arr[] = {"English","Vietnamese"};		//Array storage Languages
	public int pos;					// Storage the position of spinner
	private TextView txtlang;
	private TextView txtcookie;
	private TextView txtfonsize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		fontsize = (EditText) findViewById(R.id.edtpassword);	// find EditText Fontsize
		chkcookie = (CheckBox) findViewById(R.id.chkCookie);	// find CheckBox cookie
		spnlang = (Spinner) findViewById(R.id.spnlanguage);		// find Spinner Language
		txtlang = (TextView) findViewById(R.id.txtLanguage);		// find Spinner Language
		txtcookie = (TextView) findViewById(R.id.txtCookie);		// find Spinner Language
		txtfonsize = (TextView) findViewById(R.id.txtFontSize);		// find Spinner Language
		ArrayAdapter<String> adapter=new ArrayAdapter<String> 
		(
		 this,
		 android.R.layout.simple_spinner_item,				// join string array with spinner
		 arr
		);
		adapter.setDropDownViewResource
		 (android.R.layout.simple_list_item_single_choice);
		spnlang.setAdapter(adapter);
		
		spnlang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		
		

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				pos = arg2;								// get positon of selected item in spinner
				setui();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	protected void setui() {
		// TODO Auto-generated method stub
		switch (pos) {
		case 0:
			txtlang.setText("Language");
			txtcookie.setText("Cookies");
			txtfonsize.setText("Font Size");
			break;
		case 1:
			txtlang.setText("Ngôn ngữ");
			txtcookie.setText("Lưu Cookies");
			txtfonsize.setText("Cỡ chữ");
		default:
			
			break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		savingPreferences();		//save the Preferences
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		restoringPreferences();   // fetch the Preferences
	}

	public void savingPreferences()
	 {
		SharedPreferences pre=getSharedPreferences(prefname, MODE_PRIVATE);  //create the object SharedPreferences
	 SharedPreferences.Editor editor=pre.edit(); 		//Create the Editor for SharedPreferences
	 int size =Integer.parseInt(fontsize.getText().toString());			//get text from EditText Font Size
	 boolean bchk=chkcookie.isChecked();				//get status of CheckBox chkcookie
	 if(!bchk)											//check if chkcookie is checked
	 {
	 editor.clear();									//clear the editor
	 }
	 else
	 {
	 editor.putInt("size", size);					//put textsize to editor
	 editor.putBoolean("checked", bchk);				//put status of checkbox chkcookie
	 editor.putInt("position", pos);					//put position of selected item of spinner to editor
	 }
	 editor.commit();									//save editor to file
	 }
 public void restoringPreferences()					//retrieve data method
	 {
     SharedPreferences pre=getSharedPreferences(prefname,MODE_PRIVATE);
	 boolean bchk=pre.getBoolean("checked", false);		//get value of checkbox
	 if(bchk)											//if is checked
	 {
	 int size=pre.getInt("size", 8);				//retrieve text size
	 int position = pre.getInt("position", 0);			//retrieve position of spinner
	 fontsize.setText(size+"");							//set the size to EditText
	 spnlang.setSelection(position);					//set the item selected for spinner
	 }
	 chkcookie.setChecked(bchk);
	 }
	
}
