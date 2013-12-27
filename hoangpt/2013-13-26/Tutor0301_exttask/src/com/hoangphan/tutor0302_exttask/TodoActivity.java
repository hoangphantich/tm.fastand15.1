package com.hoangphan.tutor0203_mttodo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TodoActivity extends Activity {

	private EditText txtTask;
	private EditText txtHour;
	private EditText txtMinute;
	private ListView task;
	private ArrayList<String> listTask;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		
		/*Button btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addTask(v);
			}
		});*/
		
		//get ui
		txtTask = (EditText) findViewById(R.id.txtTask);
		txtHour = (EditText) findViewById(R.id.txtHour);
		txtMinute = (EditText) findViewById(R.id.txtMinute);
		
		task = (ListView) findViewById(R.id.tasks);
		listTask = new ArrayList<String>(); //generic, legacy instanceOf
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listTask);
		task.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo, menu);
		return true;
	}
	
	public void addTask(View view){
		//validate
		if(txtTask.getText().toString().equals("")){
			//display warning, buiild dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Warning");
			builder.setMessage("Task name need not blank")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.d("Button", "OK press");
						Toast.makeText(getBaseContext(), "OK Press", Toast.LENGTH_SHORT).show();
					}
				})
				.show();//chaining
		} else {
			//add list view
			String hour = txtHour.getText().toString();
			String minute = txtMinute.getText().toString();
			String task = txtTask.getText().toString();
			String taskLine = hour+":"+minute+" - "+task;
			listTask.add(taskLine);
			adapter.notifyDataSetChanged();
			
			//reset
			txtHour.setText("");
			txtMinute.setText("");
			txtTask.setText("");
		}
	}

}
