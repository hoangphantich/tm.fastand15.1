package com.nguyentiendung.online;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyentiendung.loader.MusicCatalogLoader.SongItem;
import com.nguyentiendung.mediaplayer.R;

public class MyAdapter extends ArrayAdapter<SongItem> {
	public Context context;
	public ArrayList<com.nguyentiendung.loader.MusicCatalogLoader.SongItem> itemarraylist;
	private SongItem selectedSong = null;
	public MyAdapter(Context context,ArrayList<SongItem> itemarraylist) {
		super(context,R.layout.activity_play_online,itemarraylist);
		this.context = context;
		this.itemarraylist = itemarraylist;
		
		if (android.os.Build.VERSION.SDK_INT > 8) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}   
	}
	public void setSelectedSong(SongItem si) {
		selectedSong = si;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 2. Get rowView from inflater
        View rowview = inflater.inflate(R.layout.custom_list_item, parent, false);
       // 3. Get the two text view from the rowView
        TextView title = (TextView) rowview.findViewById(R.id.txttitle);
        TextView artist = (TextView) rowview.findViewById(R.id.txtartist);
        ImageView album = (ImageView) rowview.findViewById(R.id.imgalbum);
        ImageView play = (ImageView) rowview.findViewById(R.id.imgplay);
        // 4. Set all element to layout
        title.setText(itemarraylist.get(position).getTitle());
        artist.setText(itemarraylist.get(position).getArtist());
        
        String link = itemarraylist.get(position).getAlbum();       
 	   Drawable drw =LoadImageFromWebOperations(link);
 	   album.setImageDrawable(drw);
 	   album.setBackgroundResource(R.drawable.rounded_view);
 	  if(selectedSong!=null&&selectedSong.id==itemarraylist.get(position).getId())
 	    play.setVisibility(View.VISIBLE);
 	  else  play.setVisibility(View.INVISIBLE);
        return rowview;
	}
	public Drawable LoadImageFromWebOperations(String strPhotoUrl) 
    {
        try
        {
        InputStream is = (InputStream) new URL(strPhotoUrl).getContent();
        Drawable d = Drawable.createFromStream(is, "src name");
        return d;
        }catch (Exception e) {
        System.out.println("Exc="+e);
        return null;
        }
    }

}
