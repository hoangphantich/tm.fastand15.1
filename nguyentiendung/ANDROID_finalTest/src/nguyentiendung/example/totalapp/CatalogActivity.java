package nguyentiendung.example.totalapp;
////////////for Electric Catalog///////////////
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CatalogActivity extends Activity {
	public int positions;
	private ListView lst;
	private String prefname="pref";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);
		ArrayList<ElectricCatalog> arr = new ArrayList<ElectricCatalog>();
		arr.add(new ElectricCatalog("For Personal Consume", "1.000 VNĐ"));
		arr.add(new ElectricCatalog("For Office Consume", "2.000 VNĐ"));
		arr.add(new ElectricCatalog("For Product Consume", "500 VNĐ"));
		MyAdapter adapter = new MyAdapter(this, arr);
		lst = (ListView) findViewById(R.id.lsvElictricCatalog);
		//adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
		lst.setAdapter(adapter);
		lst.setOnItemClickListener(new OnItemClickListener() {

			

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//positions=arg2;
				/*Toast.makeText(getApplicationContext(), "jdaksfjk", Toast.LENGTH_SHORT).show();
				Intent i2 = new Intent(getApplicationContext(), CartActivity.class);
				i2.putExtra("position", arg2);
				startActivity(i2);
				finish();*/
			}
			
		});
	}

public void goExec(View v){
     positions = lst.getPositionForView((View) v.getParent());
	Intent i= new Intent(getApplicationContext(), CartActivity.class);
	i.putExtra("position", positions);
	startActivity(i);
	finish();
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.catalog, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.itemOption:
			Intent i1 = new Intent(getApplicationContext(), PreferenceActivity.class);
			startActivity(i1);
			break;
		case R.id.itemExit:
			finish();
			break;
		case R.id.signout:
			SharedPreferences pre=getSharedPreferences(prefname, MODE_PRIVATE);  //create the object SharedPreferences
			SharedPreferences.Editor editor=pre.edit(); 		//Create the Editor for SharedPreferences
			editor.clear();
			editor.commit();
			Intent i = new Intent(getApplicationContext(), TotalActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.itemGocheckout:
			Intent i2 = new Intent(getApplicationContext(), CartActivity.class);
			startActivity(i2);
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
		
	
  }
	
	

}



