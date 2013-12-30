package nguyentiendung.intent_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Second_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);		 
    }

   public void goThree(View v){
	   EditText edtname = (EditText) findViewById(R.id.edtName);
	   EditText edtage = (EditText) findViewById(R.id.edtAge);
	   if(edtname.getText().toString().equals("")||edtage.getText().toString().equals("")){
			AlertDialog.Builder b=new AlertDialog.Builder(Second_Activity.this);
	       	b.setTitle("Warning");
	       	b.setMessage("Type your name and your age");
	       	b.setPositiveButton("Ok", new DialogInterface. OnClickListener() {
	       	
	       	public void onClick(DialogInterface dialog, int which)
	       	{
	       	dialog.cancel();
	       	}});
	       	
	       	b.create().show();
	       	
		   
	   }
	   else  { 
		   String name = edtname.getText().toString();
		   String age = edtage.getText().toString();
		   Intent i1 = new Intent("android.intent.action.THIRD");
		   i1.putExtra("name", name);
		   i1.putExtra("age", age);
		   startActivity(i1);
		   finish();
	   }
	   
   }
   public void goMain(View v){
	   Intent i1 = new Intent(getApplicationContext(),First_Activity.class);
	   startActivity(i1);
	   finish();
	   
   }


}
