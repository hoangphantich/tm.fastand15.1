package nguyentiendung.intent_activity;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyAdapter extends ArrayAdapter <Contact> {
	private List<Contact> _listitem;
	private Context			_context;
	
	
	public MyAdapter(Context context, int resource, List <Contact> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this._context=context;
		this._listitem=objects;
	}
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        CustomList view = new CustomList(this._context);
	        view.setListItem(this._listitem.get(position));
	        return view;
	    }


}
