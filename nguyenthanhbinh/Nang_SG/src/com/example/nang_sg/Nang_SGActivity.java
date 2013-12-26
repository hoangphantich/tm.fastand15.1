package com.example.nang_sg;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.*;

public class Nang_SGActivity extends Activity {

	private Button PlusBtn,MinusBtn,CalculateBtn ;
	private TextView Quantity_tb,Tip_tb,Tatal_tb;
	
	private int BeerPrice=12000;
	private int JuicePrice=20000;
	private int SoftDringPrice=15000;
	private int Price;
	
	//Tạo một mảng dữ liệu giả
	 String arr[]={"Beer","Juice","Soft-dring"};
	 TextView txt1;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.activity_nang_sg);
	 
	 PlusBtn=(Button) findViewById(R.id.PlusBtn);
	 MinusBtn=(Button) findViewById(R.id.MinusBtn);
	 CalculateBtn=(Button) findViewById(R.id.CalculateBtn);
	 Quantity_tb =(TextView) findViewById(R.id.Quantity_tb);
	 Tip_tb =(TextView) findViewById(R.id.Tip_tb);
	 Tatal_tb =(TextView) findViewById(R.id.Tatal_tb);
			 
	 //Lấy đối tượng Spinner ra
	 Spinner spin=(Spinner) findViewById(R.id.spinner1);
	 //Gán Data source (arr) vào Adapter
	 ArrayAdapter<String> adapter=new ArrayAdapter<String>
	 (
	 this,
	 android.R.layout.simple_spinner_item,
	 arr
	 );
	 //phải gọi lệnh này để hiển thị danh sách cho Spinner
	 adapter.setDropDownViewResource
	 (android.R.layout.simple_list_item_single_choice);
	 //Thiết lập adapter cho Spinner
	 spin.setAdapter(adapter);
	 //thiết lập sự kiện chọn phần tử cho Spinner
	 spin.setOnItemSelectedListener(new MyProcessEvent());
	 
	 
	 PlusBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String S_Value = Quantity_tb.getText().toString();
				int Value = Integer.parseInt(S_Value);
				Value++;
				Quantity_tb.setText(Value+"");
			}
		});
	 
	 MinusBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String S_Value = Quantity_tb.getText().toString();
				int Value = Integer.parseInt(S_Value);
				if(Value>0)
					Value--;
					Quantity_tb.setText(Value+"");
			}
		});
	 CalculateBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String S_Value = Quantity_tb.getText().toString();
				int Value = Integer.parseInt(S_Value);
				int Value1=Value*(BeerPrice/10);
				Tip_tb.setText(Value1+"");
				Value=Value*(BeerPrice)+Value1;
				Tatal_tb.setText(Value+"");
			}
		});
	 
	 
	 }
	//Event Class
	 private class MyProcessEvent implements
	 OnItemSelectedListener
	 {
	 //Khi có chọn lựa thì vào hàm này
	 public void onItemSelected(AdapterView<?> arg0,
	 View arg1,
	 int arg2,
	 long arg3) {
		 switch(arg2){
			case 0:
				Price=BeerPrice;
			case 1:
				Price=JuicePrice;
			case 2:
				Price=SoftDringPrice;
			}
		 Quantity_tb.setText("0");
		 Tip_tb.setText("10");
		 Tatal_tb.setText("0");
	 }
	 //Nếu không chọn gì cả
	 public void onNothingSelected(AdapterView<?> arg0) {
	 //selection.setText("");
	 }
	 }
	}
