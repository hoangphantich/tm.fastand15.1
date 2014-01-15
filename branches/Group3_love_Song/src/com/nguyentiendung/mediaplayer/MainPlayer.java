package com.nguyentiendung.mediaplayer;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem; 
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


import com.nguyentiendung.loader.MusicCatalogLoader;
import com.nguyentiendung.loader.MusicCatalogLoader.SongItem;
import com.nguyentiendung.menu.IconContextMenu;
import com.nguyentiendung.online.PlayOnlineActivity;
import com.nguyentiendung.playlist.DragSortListView;
import com.nguyentiendung.playlist.NowPlayingListAdapter;
import com.nguyentiendung.playlist.PlaylistManager;
import com.nguyentiendung.playlist.PlaylistManager.RepeatMode;
import com.nguyentiendung.service.MusicService;
import com.nguyentiendung.service.MusicService.MediaPlayerState;
import com.nguyentiendung.utils.Util;


public class MainPlayer extends Activity {
	private static final String TAG = "com.nguyentiendung.mediaplayer_MainPlayer";

	private static final String PREF_NAME = "MAINPLAYER";
	private static final String SETTING_SHUFFLE = "SHUFFLE";
	private static final String SETTING_REPEAT = "REPEAT";

	private static final int CONTEXT_MENU_ID = 1;

	private TextView playing_Title;
	private TextView playing_Artist;
	private TextView currentPlayingPosition;
	private TextView currentDuration;

	private PlaylistManager mPlaylistMgr;
	private MusicCatalogLoader mMusicLoader;

	private ImageButton bt_Play;
	private ImageButton bt_Next;
	private ImageButton bt_Previous;
	private ImageButton bt_ShuffleToggle;
	private ImageButton bt_RepeatToggle;

	private SeekBar songProgressBar;
	private boolean songProgressBarIsBeingTouch;

	private MusicServiceStatusReceiver mStatusReceiver;

	private DragSortListView playlistView;
	private NowPlayingListAdapter adapter_playlistView;

	private IconContextMenu iconContextMenu;
	
	private int pos =0;
	 final Context context = this;	 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate called");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// get playlist either from system or music service (if existed)
		retrieveData();

		// Textview playing display
		playing_Title = (TextView) findViewById(R.id.playing_Title);
		playing_Artist = (TextView) findViewById(R.id.playing_Artist);
		currentPlayingPosition = (TextView) findViewById(R.id.currentSongTime);
		currentDuration = (TextView) findViewById(R.id.totalSongDuration);

		// Song progress bar
		songProgressBar = (SeekBar) findViewById(R.id.songSeekBar);
		songProgressBar.setMax(100);
		songProgressBarIsBeingTouch = false;
		songProgressBar.setOnSeekBarChangeListener(songProgressBar_changeListener);

		playlistView = (DragSortListView) findViewById(R.id.playlist);
		adapter_playlistView = new NowPlayingListAdapter(this, mPlaylistMgr.getArrayList());
		playlistView.setAdapter(adapter_playlistView);
		// Set listener for drag drop action
		playlistView.setDropListener(onDragdropItemInPlaylist);
		playlistView.setRemoveListener(onRemoveItemInPlaylist);

		// assign item click listener
		playlistView.setOnItemClickListener(playlist_itemClick);

		// assign listener for button
		bt_Previous = (ImageButton) findViewById(R.id.bt_Previous);
		bt_Previous.setOnClickListener(bt_Previous_click);

		bt_Play = (ImageButton) findViewById(R.id.bt_Play);
		bt_Play.setOnClickListener(bt_Play_click);

		bt_Next = (ImageButton) findViewById(R.id.bt_Next);
		bt_Next.setOnClickListener(bt_Next_click);

		bt_ShuffleToggle = (ImageButton) findViewById(R.id.bt_ShuffleToggle);
		bt_ShuffleToggle.setOnClickListener(bt_ShuffleToggle_Click);

		bt_RepeatToggle = (ImageButton) findViewById(R.id.bt_RepeatToggle);
		bt_RepeatToggle.setOnClickListener(bt_RepeatToggle_Click);

		
		Resources res = getResources();   
        //init the menu
        iconContextMenu = new IconContextMenu(this, CONTEXT_MENU_ID);
        iconContextMenu.addItem(res, "Play", R.drawable.ic_1, 1);
        iconContextMenu.addItem(res, "Add to Favourite", R.drawable.ic_2, 2);
        iconContextMenu.addItem(res, "Delete", R.drawable.ic_3, 3);
        iconContextMenu.addItem(res, "Information", R.drawable.ic_4, 4);
        
 
		
		
		// restore preferences
		restorePreferences();
	}

	@Override
	protected void onStart() {
		Log.i(TAG, "onStart called");

		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "onResume called");

		// register music service status reciever
		mStatusReceiver = new MusicServiceStatusReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(MusicService.STATUS_BC_NOW_PLAYING);
		filter.addAction(MusicService.STATUS_BC_PLAYTIME);
		filter.addAction(MusicService.STATUS_BC_ALL);
		filter.addAction(MusicService.STATUS_BC_PLAYMODE);
		filter.addAction(MusicService.STATUS_BC_NOWPLAYING_PLAYLIST);
		registerReceiver(mStatusReceiver, filter);
		
		// request status
		Intent i = new Intent();
		i.setAction(MusicService.REQUEST_STATUS);
		startService(i);

		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, "onPause called");

		unregisterReceiver(mStatusReceiver);
		savePreferences();
		
		if (MusicService.getState() != MediaPlayerState.Playing) {
			Intent i = new Intent();
			i.setClass(this, MusicService.class);
			stopService(i);
		}

		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "onStop called");

		finish();
		
		super.onStop();
	}

	@Override
	public void onBackPressed() {
		// Setup service to show playing song if activity is destroyed
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy called");

		super.onDestroy();
	}

	/**
	 * Restore preferences
	 */
	private void restorePreferences() {
		SharedPreferences sp = getSharedPreferences(PREF_NAME, 0);

		// shuffle mode
		setShuffle(sp.getBoolean(SETTING_SHUFFLE, false), false);

		// repeat mode
		String rp = sp.getString(SETTING_REPEAT, RepeatMode.NoRepeat.toString());
		setRepeatMode(RepeatMode.valueOf(rp), false);
	}

	/**
	 * Save the preferences
	 */
	private void savePreferences() {
		// Save preference
		SharedPreferences sp = getSharedPreferences(PREF_NAME, 0);
		SharedPreferences.Editor ed = sp.edit();

		ed.putBoolean(SETTING_SHUFFLE, mPlaylistMgr.isShuffle());
		ed.putString(SETTING_REPEAT, mPlaylistMgr.getRepeatMode().toString());
		ed.commit();
	}

	// Click listener của nút Next
	public OnClickListener bt_Next_click = new OnClickListener() {

		public void onClick(View v) {
			Intent i = new Intent();
			i.setAction(MusicService.ACTION_NEXT);
			startService(i);
		}
	};

	// Click listener của nút play
	public OnClickListener bt_Play_click = new OnClickListener() {

		public void onClick(View v) {
			Intent i = new Intent();
			i.setAction(MusicService.ACTION_TOGGLE_PLAYBACK);
			startService(i);
		}
	};

	// Click listener của nút previous
	public OnClickListener bt_Previous_click = new OnClickListener() {

		public void onClick(View v) {
			Intent i = new Intent();
			i.setAction(MusicService.ACTION_PREVIOUS);
			startService(i);
		}
	};

	// click listener of shuffletoggle button
	public OnClickListener bt_ShuffleToggle_Click = new OnClickListener() {

		public void onClick(View v) {
			boolean s = mPlaylistMgr.isShuffle();
			setShuffle(!s, true);
		}
	};

	// click listener of repeattoggle
	public OnClickListener bt_RepeatToggle_Click = new OnClickListener() {

		public void onClick(View v) {
			PlaylistManager.RepeatMode rp = mPlaylistMgr.getRepeatMode();

			if (rp == RepeatMode.NoRepeat)
				setRepeatMode(RepeatMode.RepeatAll, true);
			else if (rp == RepeatMode.RepeatAll)
				setRepeatMode(RepeatMode.RepeatOne, true);
			else
				setRepeatMode(RepeatMode.NoRepeat, true);
		}
	};

	/**
	 * Set repeat mode for mainplayer, update UI also
	 * 
	 * @param rp
	 * @param needtoast
	 *            if true, toast will be shown
	 */
	private void setRepeatMode(RepeatMode rp, boolean needtoast) {
		if (mPlaylistMgr == null)
			return;
		mPlaylistMgr.setRepeatMode(rp);

		if (rp == RepeatMode.RepeatAll) {
			if (needtoast)
				Toast.makeText(getApplicationContext(), R.string.info_repeat_all, Toast.LENGTH_SHORT).show();
			bt_RepeatToggle.setImageResource(R.drawable.repeat_active);

		} else if (rp == RepeatMode.RepeatOne) {
			if (needtoast)
				Toast.makeText(getApplicationContext(), R.string.info_repeat_one, Toast.LENGTH_SHORT).show();
			bt_RepeatToggle.setImageResource(R.drawable.repeat_active_one);

		} else {
			if (needtoast)
				Toast.makeText(getApplicationContext(), R.string.info_repeat_none, Toast.LENGTH_SHORT).show();
			bt_RepeatToggle.setImageResource(R.drawable.repeat_disable);
		}
	}

	/**
	 * set shuffle mode for mainplayer, update also the UI
	 * 
	 * @param s
	 *            Shuffle
	 * @param needtoast
	 *            if true, toast will be shown
	 */
	private void setShuffle(boolean s, boolean needtoast) {
		if (mPlaylistMgr == null)
			return;
		mPlaylistMgr.setShuffle(s);
		if (s) {
			if (needtoast)
				Toast.makeText(getApplicationContext(), R.string.info_shuffle_on, Toast.LENGTH_SHORT).show();
			bt_ShuffleToggle.setImageResource(R.drawable.shuffle_active);
		} else {
			if (needtoast)
				Toast.makeText(getApplicationContext(), R.string.info_shuffle_off, Toast.LENGTH_SHORT).show();
			bt_ShuffleToggle.setImageResource(R.drawable.shuffle_disable);
		}
	}

	// Seekbar change listener
	private OnSeekBarChangeListener songProgressBar_changeListener = new OnSeekBarChangeListener() {

		public void onStartTrackingTouch(SeekBar seekBar) {
			songProgressBarIsBeingTouch = true;
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			songProgressBarIsBeingTouch = false;

			Intent i = new Intent();
			i.setAction(MusicService.ACTION_SEEK);

			Bundle b = new Bundle();
			b.putInt(MusicService.BKEY_PERCENTAGE, seekBar.getProgress());

			i.putExtras(b);
			startService(i);
		}

		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		}
	};

	public void changeSelectedSong(SongItem si) {
		adapter_playlistView.setSelectedSongItem(si);
		playlistView.invalidateViews();
	}

	private DragSortListView.DropListener onDragdropItemInPlaylist = new DragSortListView.DropListener() {
		public void drop(int from, int to) {
			SongItem item = (SongItem) adapter_playlistView.getItem(from);

			adapter_playlistView.remove(item);
			adapter_playlistView.insert(item, to);
			
			adapter_playlistView.notifyDataSetChanged();

			Bundle b = new Bundle();
			b.putInt(MusicService.BKEY_REORDER_FROM, from);
			b.putInt(MusicService.BKEY_REORDER_TO, to);

			Intent i = new Intent();
			i.setAction(MusicService.ACTION_REORDER_PLAYLIST);
			i.putExtras(b);
			startService(i);
		}
	};

	private DragSortListView.RemoveListener onRemoveItemInPlaylist = new DragSortListView.RemoveListener() {

		public void remove(int which) {
			SongItem item = (SongItem) adapter_playlistView.getItem(which);

			adapter_playlistView.remove(item);

			adapter_playlistView.notifyDataSetChanged();

			Bundle b = new Bundle();
			b.putInt(MusicService.BKEY_REORDER_FROM, which);
			b.putInt(MusicService.BKEY_REORDER_TO, -1);

			Intent i = new Intent();
			i.setAction(MusicService.ACTION_REORDER_PLAYLIST);
			i.putExtras(b);
			startService(i);
		}
	};

	// Item Click listener của playlist
	public OnItemClickListener playlist_itemClick = new OnItemClickListener() {
		private int ypos =0;
		private int xpos =0;

		public void onItemClick(final AdapterView<?> parent, View view, final int pos, long id) {
				
			int[] location = new int[2];
			view.getLocationOnScreen(location);
			// xpos = location[0];
			 ypos  = location[1] ;
			 iconContextMenu.createMenu("Choose Action",xpos ,ypos).show();
		       //set onclick listener for context menu
		        iconContextMenu.setOnClickListener(new IconContextMenu.IconContextMenuOnClickListener() {
					@Override
					public void onClick(int menuId) {
						switch(menuId) {
						case 1:
					///////////play item when click/////////////////////////////////////////////			
							SongItem si = (SongItem) parent.getItemAtPosition(pos);
							Singleton singleton = Singleton.getInstance();
							Toast.makeText(getApplicationContext(), ""+si.dataStream, Toast.LENGTH_SHORT).show();
							singleton.setCurrentSongItem(si);
							Intent i = new Intent();
							i.setAction(MusicService.ACTION_PLAY_SPECIFIC_SONG);
							startService(i);
							
							break;
						case 2:
							Toast.makeText(getApplicationContext(), "You've clicked 2, "+pos, Toast.LENGTH_SHORT).show();
							break;
						case 3:
							SongItem si1 = (SongItem) parent.getItemAtPosition(pos);
							String filetodel = si1.dataStream;
									File delfile = new File(filetodel);
									Boolean xoa =delfile.delete();
									Toast.makeText(getApplicationContext(), "ket qua"+xoa, Toast.LENGTH_SHORT).show();
									adapter_playlistView.remove(si1);
									adapter_playlistView.notifyDataSetChanged();
									sendBroadcast(new Intent(Intent.ACTION_DELETE, Uri.fromFile(delfile)));
									
							break;
						case 4:
							
							SongItem si2 = (SongItem) parent.getItemAtPosition(pos);
							 MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
						        metaRetriever.setDataSource(""+si2.dataStream);
						       
						        String out = "";
						        // get mp3 info
						        out += "Ten bai hat: "+ metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
						        out += "\n";
						        out += "Tac gia: "+metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
						        out += "\n";
						        out += "Album: "+metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
						        out += "\n";
						        out += "Chat luong: "+ Integer.parseInt(metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE))/1000+"Kbps";
						        out += "\n";
						        // convert duration to minute:seconds
						        String duration = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
						        long dur = Integer.parseInt(duration);
						        String seconds = String.valueOf(dur % 60);
						        String minutes = String.valueOf(dur / 60000);
						        out += "Thoi gian: [ " + minutes + "m" + seconds + "s ]\n";        
						       
						        // close object
						        metaRetriever.release();
						        // display output
						       
							//Toast.makeText(getApplicationContext(), "You've clicked 4, "+pos, Toast.LENGTH_SHORT).show();
							
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
							alertDialogBuilder.setTitle( "About Song");
							alertDialogBuilder
							.setMessage(out)
							.setCancelable(true)
							.setPositiveButton("Cancel",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, close
									// current activity
									
									dialog.cancel();
								}
							  });
						
							AlertDialog alertDialog = alertDialogBuilder.create();
							alertDialog.show();
							break;
						}
					}
				});

		}
	};

	// Nhận thông tin từ service
	// Nhận intent từ service music
	private class MusicServiceStatusReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Bundle b = intent.getExtras();

			Singleton singleton = Singleton.getInstance();

			if (action.equals(MusicService.STATUS_BC_ALL)) {
				// play-mode buttons
				if (mPlaylistMgr != null) {
					setShuffle(mPlaylistMgr.isShuffle(), false);
					setRepeatMode(mPlaylistMgr.getRepeatMode(), false);
				}
			}

			// Kiểm tra action để thể hiện tên bài hát
			if (action.equals(MusicService.STATUS_BC_NOW_PLAYING) || action.equals(MusicService.STATUS_BC_ALL)) {
				// SongItem si = (SongItem) b.get(MusicService.BKEY_PLAYSONG);
				SongItem si = singleton.getCurrentSongItem();
				changeSelectedSong(si);
				if (si != null) {
					playing_Title.setText(si.title);
					playing_Artist.setText(si.artist);
					songProgressBar.setEnabled(true);
					// mActionbar.setTitle(si.mTitle);
					// mActionbar.setSubtitle(si.mArtist);
				} else {
					playing_Title.setText("");
					playing_Artist.setText("");
					currentPlayingPosition.setText("");
					currentDuration.setText("");
					songProgressBar.setEnabled(false);
				}
			}

			// Update the playing time
			if (action.equals(MusicService.STATUS_BC_PLAYTIME) || action.equals(MusicService.STATUS_BC_ALL)) {
				// cap nhat seek bar
				int pos = b.getInt(MusicService.BKEY_CURSONG_POSITION);
				int len = b.getInt(MusicService.BKEY_CURSONG_DURATION);
				currentPlayingPosition.setText(Util.milliSecondsToTimer(pos));
				currentDuration.setText(Util.milliSecondsToTimer(len));
				if (!songProgressBarIsBeingTouch)
					songProgressBar.setProgress(Util.getProgressPercentage(pos, len));
			}

			// Update state of play/pause button
			MediaPlayerState mState = (MediaPlayerState) b.getSerializable(MusicService.BKEY_STATE);
			if (mState == MediaPlayerState.Paused || mState == MediaPlayerState.Stopped) {
				bt_Play.setImageResource(R.drawable.av_play);
			} else if (mState == MediaPlayerState.Playing) {
				bt_Play.setImageResource(R.drawable.av_pause);
			}
		}

	}

	/**
	 * 
	 * @return true if newly created, false if data retrieved from music service
	 * 
	 */
	private boolean retrieveData() {
		if (MusicService.getPlaylistManager() == null) {
			mMusicLoader = new MusicCatalogLoader(this);
			ArrayList<SongItem> listSongs = mMusicLoader.getSongsList();

			// Create the playlistManager
			/*if (mPlaylistMgr != null) {
				mPlaylistMgr.setArrayList(listSongs);
			} else {*/
				mPlaylistMgr = new PlaylistManager(listSongs);
		//	}

			sendPlaylistToMusicService();
			return true;

		}

		mPlaylistMgr = MusicService.getPlaylistManager();
		return false;
	}

	private void sendPlaylistToMusicService() {
		// If no playlist then return
		if (mPlaylistMgr == null)
			return;

		Singleton s = Singleton.getInstance();

		s.setPlaylistManager(mPlaylistMgr);
		s.setNowPlayingList(mPlaylistMgr.getArrayList());

		Intent i = new Intent();
		i.setAction(MusicService.SUPPLY_PLAYLIST);
		startService(i);
	}
public void goPlaylistItemClick(View v){
    PopupMenu popup = new PopupMenu(MainPlayer.this, v);
    popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
     public boolean onMenuItemClick(MenuItem item) {
      Toast.makeText(MainPlayer.this,
        "You Clicked : " + item.getTitle(),
        Toast.LENGTH_SHORT).show();
      return true;
     }
    });
 
    popup.show();
  
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub
	getMenuInflater().inflate(R.menu.activity_main, menu);
	
	
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	switch (item.getItemId() ){
	case R.id.menu_settings:
		Intent i = new Intent();
		i.setClass(this, MusicService.class);
		stopService(i);
		Intent online = new Intent(getApplicationContext(), PlayOnlineActivity.class);
		startActivity(online);
		break;
	case R.id.gotofavourite:

			Intent gui1=new Intent(getApplicationContext(), VidduActivity.class);
			startActivity(gui1);
		break;
	case R.id.gotogame:

		Intent gui2=new Intent(getApplicationContext(), Game_Caro.class);
		startActivity(gui2);
	break;
	case R.id.about:
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle( "About Author");
		alertDialogBuilder
		.setMessage("Nguyễn Hữu Minh\nNguyễn Tiến Dũng\nLớp Lập trình Android\nCông ty TechMatter")
		.setCancelable(true)
		.setPositiveButton("Cancel",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, close
				// current activity
				
				dialog.cancel();
			}
		  });
	
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	break;
	case R.id.exit:
		Intent i2 = new Intent();
		i2.setClass(this, MusicService.class);
		stopService(i2);
		super.onDestroy();
		finish();
	break;
	default:
		break;
	}
	return super.onOptionsItemSelected(item);
}


}
