package com.project.babe_image;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.project.babe_image.Constants.Extra;
import com.project.babe_image.assist.FacebookEventObserver;


public class ShareActivity extends MenuActivity {

	private FacebookEventObserver facebookEventObserver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		findViewById(R.id.button_share_facebook).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				startFacebookActivity();
			}
		});
		facebookEventObserver = FacebookEventObserver.newInstance();
		
	}

	
	@Override
	public void onStart() {
		super.onStart();
		facebookEventObserver.registerListeners(this);
		
	}

	@Override
	protected void onStop() {
		super.onStop();
		facebookEventObserver.unregisterListeners();
	}

	private void startFacebookActivity() {
	
		Intent intent = new Intent();
		intent.setClass(this, FacebookActivity.class);
			 
		intent.putExtra(Extra.POST_MESSAGE, Constants.FACEBOOK_SHARE_MESSAGE);
		intent.putExtra(Extra.POST_LINK, Constants.FACEBOOK_SHARE_LINK);
		intent.putExtra(Extra.POST_LINK_NAME, Constants.FACEBOOK_SHARE_LINK_NAME);
		intent.putExtra(Extra.POST_LINK_DESCRIPTION, Constants.FACEBOOK_SHARE_LINK_DESCRIPTION);
		intent.putExtra(Extra.POST_PICTURE,Constants.FACEBOOK_SHARE_PICTURE);
		startActivity(intent);
	}
//	@SuppressWarnings("unused")
	private void onFacebookClick(View v)
	{
		Intent intent1 = new Intent();
		intent1.setClass(this, FacebookActivity.class);
		intent1.putExtra(Extra.POST_MESSAGE, Constants.FACEBOOK_SHARE_MESSAGE);
		intent1.putExtra(Extra.POST_LINK, Constants.FACEBOOK_SHARE_LINK);
		intent1.putExtra(Extra.POST_LINK_NAME, Constants.FACEBOOK_SHARE_LINK_NAME);
		intent1.putExtra(Extra.POST_LINK_DESCRIPTION, Constants.FACEBOOK_SHARE_LINK_DESCRIPTION);
		intent1.putExtra(Extra.POST_PICTURE, Constants.FACEBOOK_SHARE_PICTURE);
		startActivity(intent1);
	}
}
