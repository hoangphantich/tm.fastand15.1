package nguyentiendung.example.totalapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ElectricAdapter extends ArrayAdapter<KindOfElectric> {
		public Context context;
		public ArrayList<KindOfElectric> itemlist;
		
		public ElectricAdapter(Context context,ArrayList<KindOfElectric> itemlist) {
			super(context,R.layout.consumer_custom_list, itemlist);
			this.context = context;
			this.itemlist = itemlist;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        // 2. Get rowView from inflater
		        View rowView = inflater.inflate(R.layout.consumer_custom_list, parent, false);

		        // 3. Get the two text view from the rowView
		        EditText txtnumber = (EditText) rowView.findViewById(R.id.edtcmtotal);
		        EditText txtmoney = (EditText) rowView.findViewById(R.id.edtcmcal);
		        TextView txtporpose = (TextView) rowView.findViewById(R.id.txtporpose);
		        
		        // 4. Set the text for textView
		        txtnumber.setText(""+itemlist.get(position).getNumber());
		        txtmoney.setText(""+itemlist.get(position).getMoney());
		        txtporpose.setText(""+itemlist.get(position).getPorpose());
		        // 5. retrn rowView
		        return rowView;
		}
		
		public String getNumber(){
			return null;
			
		}
}
