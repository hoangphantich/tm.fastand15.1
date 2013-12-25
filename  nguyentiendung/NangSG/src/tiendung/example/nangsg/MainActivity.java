package tiendung.example.nangsg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {
			String arr[]={"Beer", "Soft-Drink","Juice"};
			TextView selections;
			EditText txtquan;
			EditText txttotal;
			Spinner spin;
			float price=0;
			
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				txtquan=(EditText) findViewById(R.id.txtquantity);
				txtquan.setText("1");
				txttotal=(EditText) findViewById(R.id.txtfee);
				txttotal.setText("0  VNĐ");
//xử lý nút bấm -
		        Button btn2=(Button) findViewById(R.id.button1);
		        btn2.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		        //txtquan=(EditText) findViewById(R.id.txtquantity);
		        String squan1 = txtquan.getText().toString();
		        int quan = Integer.parseInt(squan1);
		        if(quan>=1)
		        quan--;
		        txtquan.setText(quan+"");
		        }
		        });
//xử lý nút bấm calculate---------------------------------------------------				
				Button btn1=(Button) findViewById(R.id.btncal);
		        btn1.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		        //txtquan=(EditText) findViewById(R.id.txtquantity);
		        //txttotal=(EditText) findViewById(R.id.txtfee);
		        String squan2 = txtquan.getText().toString();
		        int soluong = Integer.parseInt(squan2);
		        float tong =   (float) 1.05*soluong*price;
		        txttotal.setText(tong+"  VNĐ");
		    }
		        });
//xử lý nút bấm +
		        Button btn3=(Button) findViewById(R.id.button2);
		        btn3.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		        //txtquan=(EditText) findViewById(R.id.txtquantity);
		        String squan3 = txtquan.getText().toString();
		        int quan = Integer.parseInt(squan3);
		        quan++;
		        txtquan.setText(quan+"");
		        }
		        });		        
				
//xử lý Spinner				
				
				
		        spin=(Spinner) findViewById(R.id.spinner1);
				ArrayAdapter<String> adapter=new ArrayAdapter<String>
				(
				 this,
				 android.R.layout.simple_spinner_item,
				 arr
				);
				adapter.setDropDownViewResource
					(android.R.layout.simple_list_item_single_choice);
				spin.setAdapter(adapter);
				spin.setOnItemSelectedListener(new MyProcessEvent());
			 }
			 //Class tạo sự kiện
			 private class MyProcessEvent implements
			 OnItemSelectedListener
			 {
			 //Khi có chọn lựa thì vào hàm này
			 public void onItemSelected(AdapterView<?> arg0,
			 View arg1,
			 int arg2,
			 long arg3) {
			 //arg2 là phần tử được chọn trong data source
			 //selection.setText(arr[arg2]);
			 switch(arg2){
			 case 0:
			 {
				 price=10;
				 break;
			 }
			 case 1:
			 {
				 price=20;
				 break;
			 }
			 case 2:
			 {
				 price=30;
				 break;
			 }
			 }
			 }
			 //Nếu không chọn gì cả
			 public void onNothingSelected(AdapterView<?> arg0) {
			 price=0;
			 }
			 }
			}
