package nguyentiendung.example.totalapp;
//////////////////////Adapter for connect ListView of Electric Catalog with ElectricCatalog Class////////////
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<ElectricCatalog> {
		public Context context;
		public ArrayList<ElectricCatalog> itemlist;
		public MyAdapter(Context context,ArrayList<ElectricCatalog> itemlist) {
			super(context,R.layout.electric_catalog, itemlist);
			this.context = context;
			this.itemlist = itemlist;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 1. Create inflater
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 2. Get rowView from inflater
            View rowView = inflater.inflate(R.layout.electric_catalog, parent, false);
 
            // 3. Get the two text view from the rowView
            TextView labelname = (TextView) rowView.findViewById(R.id.txtPorpose);
            TextView labelprice = (TextView) rowView.findViewById(R.id.txtPrice);
            // 4. Set the text for textView
            labelname.setText(itemlist.get(position).getName());
            labelprice.setText(itemlist.get(position).getPrice());
 
            // 5. retrn rowView
            return rowView;
		}
		
}
