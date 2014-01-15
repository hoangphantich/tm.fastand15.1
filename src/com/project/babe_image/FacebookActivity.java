package com.project.babe_image;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.socialsharing.common.AuthListener;
import com.nostra13.socialsharing.facebook.FacebookFacade;
import com.project.babe_image.Constants.Extra;
import com.project.babe_image.assist.FacebookEventObserver;

public class FacebookActivity extends Activity {

	private static int RESULT_LOAD_IMAGE = 1;

	protected static final String TAG = null;
	private FacebookFacade facebook;
	private FacebookEventObserver facebookEventObserver;
	private EditText messageView;
	private String link;
	private String linkName;
	private String linkDescription;
	private String picture;
	private Map<String, String> actionsMap;
	private ImageView fb_thumb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facebook);
		facebook = new FacebookFacade(this, Constants.FACEBOOK_APP_ID);
		facebookEventObserver = FacebookEventObserver.newInstance();		
		messageView = (EditText) findViewById(R.id.message);
		findViewById(R.id.button_post_image);
		fb_thumb = (ImageView)findViewById(R.id.fb_thumb);
		Button postImageButton = (Button) findViewById(R.id.button_post_image);
		Button btnOpenFolder = (Button)findViewById(R.id.btnOpenFolder);
		Intent j = getIntent();
		String file_name = j.getStringExtra("linkface");
//		if (!link.equals(""))
//		{
			Toast.makeText(getApplicationContext(), "o day chua", Toast.LENGTH_SHORT).show();
			fb_thumb.setImageBitmap(BitmapFactory.decodeFile(file_name));
//		}
//		else
//		{
			Bundle bundle = j.getExtras();
			if (bundle != null) {
			//	Extra.
				String message = bundle.getString(Extra.POST_MESSAGE);
				link = bundle.getString(Extra.POST_LINK);
				linkName = bundle.getString(Extra.POST_LINK_NAME);  // link nam
				linkDescription = bundle.getString(Extra.POST_LINK_DESCRIPTION); // link dd
				picture = bundle.getString(Extra.POST_PICTURE);
				actionsMap = new HashMap<String, String>();
				actionsMap.put(Constants.FACEBOOK_SHARE_ACTION_NAME, Constants.FACEBOOK_SHARE_ACTION_LINK);
				//messageView.setText(message);
			}
			
//		}
//		
		
			
		 messageView.setOnTouchListener(new View.OnTouchListener() {

		            public boolean onTouch(View view, MotionEvent event) {
		                // TODO Auto-generated method stub
		                if (view.getId() ==R.id.message) {
		                    view.getParent().requestDisallowInterceptTouchEvent(true);
		                    switch (event.getAction()&MotionEvent.ACTION_MASK){
		                    case MotionEvent.ACTION_UP:
		                        view.getParent().requestDisallowInterceptTouchEvent(false);
		                        break;
		                    }
		                }
		                return false;
		            }
		        });
		
			////////////////////
		 btnOpenFolder.setOnClickListener(new OnClickListener() {

                public void onClick(View arg0) {

                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });

		postImageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (facebook.isAuthorized()) {
					publishImage();
					messageView.setText("");
					fb_thumb.setImageBitmap(BitmapFactory.decodeFile(""));
				//	finish();
				} 
				else 
				{
					// Start authentication dialog and publish image after successful authentication
					facebook.authorize(new AuthListener() {
							@Override
							public void onAuthSucceed() {
									publishImage();
									messageView.setText("");
									fb_thumb.setImageBitmap(BitmapFactory.decodeFile(""));
									
									
								//	finish();
							}
							@Override
							public void onAuthFail(String error) { 
								// Do noting
							}
					});
				}
			}
		});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/*
	 *   call menu
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {
		   	case R.id.item_capture:
		  		Intent  capture = new Intent(this, OpenCamActivity.class);
		  		startActivity(capture);
		  		finish();
		  	// callback capture
			  return true;
		  	case R.id.item_download:
		  		Intent  download = new Intent(this, DownloadActivity.class);
		  		startActivity(download);
		    	finish();
		      return true;
		    case R.id.item_shownormal:
		    	Intent  shownormal = new Intent(this, Main_Activity.class);
		  		startActivity(shownormal);
		    	// callback function here
		    	finish();
		    	return true;    	
		    case R.id.item_exit:
		    	// callback function here
		    		finish();
		      return true;
		      
		    default:
		      return super.onOptionsItemSelected(item);
		  }
	}	
	
	private void publishImage() {
		Bitmap bitmap = ((BitmapDrawable)fb_thumb.getDrawable()).getBitmap();		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] bitmapdata = stream.toByteArray();
		facebook.publishImage(bitmapdata, messageView.getText().toString());

	}
//
	@Override
	public void onStart() {
		super.onStart();
		facebookEventObserver.registerListeners(this);
		if (!facebook.isAuthorized()) {
			facebook.authorize();
		}
	}

	@Override
	public void onStop() {
		facebookEventObserver.unregisterListeners();
		super.onStop();
	}

		@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		
		    if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
		        Uri selectedImage = data.getData();
		        String[] filePathColumn = { MediaStore.Images.Media.DATA };
		
		        Cursor cursor = getContentResolver().query(selectedImage,
		                filePathColumn, null, null, null);
		        cursor.moveToFirst();
		
		        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		        String picturePath = cursor.getString(columnIndex);
		        cursor.close();
		
		       // ImageView imageView = (ImageView) findViewById(R.id.imgView);
		        fb_thumb.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		    }
		}
}
