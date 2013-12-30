package com.hoangphan.tutor0303_dog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyBrowserActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mybrowser);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my_browser, menu);
    return true;
  }

}
