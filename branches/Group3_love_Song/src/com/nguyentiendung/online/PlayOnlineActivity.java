package com.nguyentiendung.online;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyentiendung.loader.MusicCatalogLoader.SongItem;
import com.nguyentiendung.mediaplayer.MainPlayer;
import com.nguyentiendung.mediaplayer.R;

public class PlayOnlineActivity extends Activity {


	private TextView tv;
	private String ketqua="";
	ArrayList<SongItem> arr;
	private MyAdapter list;
	 MediaPlayer player;
	private EditText search;;
	String urlexec="";
	//private boolean isPlaying = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_online); 
		arr = new ArrayList<SongItem>();
		//arr.add(new SongItem(1,"khong co gi ca", "erewre","images/khoimy.jpg","asdfjakl "));
		
		list = new MyAdapter(this, arr);
		ListView lst = (ListView) findViewById(R.id.lstplaylist);
		lst.setAdapter(list);
		search = (EditText)findViewById(R.id.editText1);
		search.setText("");
		ImageButton btnsearch = (ImageButton)findViewById(R.id.btnsearch);
		btnsearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
				gotosearch();
			}
		});
		search.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if(KeyEvent.ACTION_DOWN == event.getAction() && KeyEvent.KEYCODE_ENTER == keyCode)
				{
					gotosearch();
					return true;
				}
				else
				return false;
				
			}
		});

		player= new MediaPlayer();
	    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
	   lst.setOnItemClickListener(new OnItemClickListener() {

		   

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
			view.setVisibility(View.VISIBLE);
		    player.stop();
			SongItem si = (SongItem) parent.getItemAtPosition(position);
			list.setSelectedSong(si);
			list.notifyDataSetChanged();
			//Singleton singleton = Singleton.getInstance();
			//Toast.makeText(getApplicationContext(), ""+si.dataStream, Toast.LENGTH_SHORT).show();
			
			
		    try {
				player.setDataSource(si.dataStream);
				 player.prepare();
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		    player.start();

		
		}
	});
	}
	 protected void gotosearch() {
		// TODO Auto-generated method stub
		list.clear();
		urlexec= "http://192.168.1.100/truyvan.php?name=";
		String text = search.getText().toString().replace(" ", "+");
		
		urlexec=urlexec+text;
		//Toast.makeText(getApplicationContext(), ""+urlexec, Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute(urlexec);
		search.setText("");
	}
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {

			@Override
	        protected String doInBackground(String... urls) {
	 
	            return JSONParser.getJSONFromUrl(urls[0]);
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	            //Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
	            //etResponse.setText(result);
	            String path = "http://192.168.1.100/"	;  
	            TextView inra = (TextView)findViewById(R.id.txtjson);
	            inra.setText("");
	            if (result!=null)
	            try {
					JSONArray jarr = new JSONArray(result);
					for(int i=0;i<jarr.length();i++){
						JSONObject jobj = jarr.getJSONObject(i);
						arr.add(new SongItem(jobj.getInt("id"), jobj.getString("name"), jobj.getString("actor"), path+jobj.getString("image"), path+jobj.getString("url")));
					}
						list.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					inra.setText("Không tìm thấy kết quả nào");
				}
	            else {
	            	
	            	inra.setText("Không tìm thấy kết quả nào");
	            }
	            
	        }

	 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
			getMenuInflater().inflate(R.menu.main, menu);
			
			
			return true;
		}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.goback:
			player.stop();
			Intent i = new Intent(getApplicationContext(), MainPlayer.class);
			startActivity(i);
			super.onDestroy();
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	


		
}