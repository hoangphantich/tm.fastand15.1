package com.example.batung;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Currency;

public class MainActivity extends Activity {
	private EditText noidung;
	private EditText gio;
	private EditText phut;
	private TextView danhsach, hienthi;
	private ListView danhsach1;
	
	ArrayList<String>arrList=null;
	ArrayAdapter<String> adapter=null;
	 
	private Button luu;
	final Context context = this;
	int canhbao;
	private String chuoicanhbao="";
	int xoa;
	int dem;
	private int so, so1, so2;
	private String luuchuoi;
	private int[] luugio={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		noidung=(EditText)findViewById(R.id.noidung);
		gio=(EditText)findViewById(R.id.gio);
		phut=(EditText)findViewById(R.id.phut);
		danhsach1=(ListView)findViewById(R.id.danhsach1);
		luu=(Button)findViewById(R.id.luu);
		hienthi=(TextView)findViewById(R.id.hienthi);
		arrList=new ArrayList<String>();
		 //2. Gán Data Source (ArrayList object) vào ArrayAdapter
		 adapter=new ArrayAdapter<String>
		 (this,
		 android.R.layout.simple_list_item_1,
		 arrList);
		 //3. gán Adapter vào ListView
		 danhsach1.setAdapter(adapter);
		 
		luu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (phut.getText().toString().equals(""))
				{
					chuoicanhbao="Chi Tung chua nhap phut";
				}
				if (gio.getText().toString().equals(""))
				{
					chuoicanhbao="Chi Tung chua nhap gio";
				}

				if (noidung.getText().toString().equals(""))
				{
					chuoicanhbao="Chi Tung chua nhap noi dung";
				}
				
				if (!chuoicanhbao.equals(""))
				{
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		 		// set title
					alertDialogBuilder.setTitle(chuoicanhbao);
					alertDialogBuilder
					.setMessage("Click OK de nhap bo sung")
					.setCancelable(false)
					//.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					//	public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
					//		MainActivity.this.finish();
					//	}
					//  })
					.setNegativeButton("OK",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							dialog.cancel();
						}

					});
					AlertDialog alertDialog = alertDialogBuilder.create();
					 
					// show it
					alertDialog.show();
					chuoicanhbao="";
					
				}
				else
				{
					arrList.add(gio.getText().toString()+":"+phut.getText().toString()+" - "+noidung.getText().toString()+"");
					luugio[dem]=Integer.parseInt(gio.getText().toString())*60+Integer.parseInt(phut.getText().toString());
					dem=dem+1;
					if (dem>1)
					{
					
						for (int i=0;i<dem-1;i++)
						{
							
							if (luugio[dem-1]<luugio[i])
							{
								luuchuoi=arrList.get(i);
								arrList.set(i, arrList.get(dem-1));
								arrList.set(dem-1, luuchuoi);
								
							}
						}
						
					}
					
				}
				 adapter.notifyDataSetChanged();
				 
			}
		});
		
		danhsach1.setOnItemClickListener(new AdapterView
				 .OnItemClickListener() {
				 public void onItemClick(
				 AdapterView<?> arg0,View arg1,
				 int arg2,long arg3) {
				 
				 hienthi.setText(arrList.get(arg2));
				 }
				 });
		
		danhsach1.setOnItemLongClickListener(new AdapterView
				 .OnItemLongClickListener() {
				 @Override
				 public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				 int arg2, long arg3) {
				xoa=arg2;
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				 		// set title
							//alertDialogBuilder.setTitle(chuoicanhbao);
							alertDialogBuilder
							.setMessage("CHI TUNG MUON XOA KHONG?")
							.setCancelable(false)
							.setPositiveButton("YES",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, close
									// current activity
							//		MainActivity.this.finish();
									 arrList.remove(xoa);//xóa phần tử thứ arg2
									 adapter.notifyDataSetChanged();
									 xoa=0;
									dem=dem-1;
								}
							  })
							.setNegativeButton("NO",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, just close
									// the dialog box and do nothing
									dialog.cancel();
								}

							});
							AlertDialog alertDialog = alertDialogBuilder.create();
							 
							// show it
							alertDialog.show();
				
				 return false;
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
