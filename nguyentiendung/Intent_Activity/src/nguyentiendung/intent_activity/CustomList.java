/**
 * 
 */
package nguyentiendung.intent_activity;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author GODKING
 *
 */
public class CustomList extends LinearLayout {

	/**
	 * @param context
	 */
	public TextView name;
	public ImageView photo;
	public CustomList(Context context) {
		super(context);
		//doc tap tin xml
		 LayoutInflater linflater = (LayoutInflater) ((FourActivity)context).getSystemService(Service.LAYOUT_INFLATER_SERVICE);
	        linflater.inflate(R.layout.custom_list, this);
	        // dua cac gia tri trong xml file vao customview
	        this.name=(TextView) findViewById(R.id.txtname);
	        this.photo=(ImageView) findViewById(R.id.imgcontact);
	        //
		}
	// dua data vao trong phan tu CustomView
	public void setListItem(Contact item){
		this.name.setText(item.name);
		this.photo.setImageResource(item.photo);
	}
	

}
