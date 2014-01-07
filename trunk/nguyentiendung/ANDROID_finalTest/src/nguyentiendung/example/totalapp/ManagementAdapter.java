package nguyentiendung.example.totalapp;
////////////////////Adapter to connect Listview of User Management with UserDetail Class///////////////////
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ManagementAdapter extends ArrayAdapter<UserManagementClass> {
	public Context context;
	public ArrayList<UserManagementClass> itemarray;
	public ManagementAdapter(Context context,ArrayList<UserManagementClass> itemarray) {
		super(context,R.layout.user_management_custom_layout, itemarray);
		this.context = context;
		this.itemarray = itemarray;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.user_management_custom_layout, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelname = (TextView) rowView.findViewById(R.id.txtusername);
        TextView labelemail = (TextView) rowView.findViewById(R.id.txtemail);
        // 4. Set the text for textView
        labelname.setText(itemarray.get(position).getUsername());
        labelemail.setText(itemarray.get(position).getEmail());

        // 5. retrn rowView
        return rowView;
	}
	

}
