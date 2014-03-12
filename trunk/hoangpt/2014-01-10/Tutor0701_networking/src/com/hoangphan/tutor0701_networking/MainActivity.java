package com.hoangphan.tutor0701_networking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	protected static final String TAG = null;
	Bitmap bm;
	private ImageView img;
	
	 Boolean result;
	 FileOutputStream fos;
	private Button btnOpen;
	private Button btnRss;

	private EditText ed_http;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_http = (EditText)findViewById(R.id.ed_http);
		// ---Button view---
		 btnOpen = (Button) findViewById(R.id.Button01);		
		btnOpen.setOnClickListener(new View.OnClickListener() {
			

			private String imgUri;

			public void onClick(View v) {
				if (android.os.Build.VERSION.SDK_INT > 8) {
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);
				}

				//String imgUri = "http://m.f1.img.vnecdn.net/2013/12/02/article-2515619-19C21CAA000005-7820-7176-1385941527.jpg";
				imgUri = ed_http.getText().toString().trim();
				Bitmap image = downloadImage(imgUri);
				//new BackgroundTask().execute(imgUri);
				img = (ImageView) findViewById(R.id.img);
				img.setImageBitmap(image);
				
			}
		});
		
	/// READ IMAGE FROM IMAGEVIEW SAVE TO SDCARD
		btnRss = (Button) findViewById(R.id.Button02);
		btnRss.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
			              Environment.DIRECTORY_PICTURES), "MyImage");
		    	// create intent with ACTION_IMAGE_CAPTURE action
		    	if (!mediaStorageDir.exists()) {
		    	    if (!mediaStorageDir.mkdirs()) {
		    	      Log.e(TAG, "Failed to create storage directory.");
		    	      return;
		    	    }
		    	  }
		    	
				img.setDrawingCacheEnabled(true);
			//	bm = img.getDrawingCache();
				BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
				bm = drawable.getBitmap();
				 java.util.Date date= new java.util.Date();
				 String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				                         .format(date.getTime());
				 String  tenfile= mediaStorageDir.getPath().toString()+File.separator.toString()+timeStamp+".jpg";
				    
                try {
                    fos = new FileOutputStream(tenfile);
                    result=bm.compress(CompressFormat.JPEG, 75, fos);

                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
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

    private String downloadText(String URL) {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = openHttpConnection(URL);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "";
        }
        
        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];          
        try {
            while ((charRead = isr.read(inputBuffer))>0)
            {                    
                //---convert the chars to a String---
                String readString = 
                    String.copyValueOf(inputBuffer, 0, charRead);                    
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }    
        return str;        
    }	
	
	private Bitmap downloadImage(String imgUri) {
		// connect to hosting
		Bitmap image = null;
		try {
			InputStream in = openHttpConnection(imgUri);
			image = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e) {
			Toast.makeText(this, "Cant get image", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}

		// put content to stream
		return image;
	}

	private InputStream openHttpConnection(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");
		try {
			// connect
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}
		return in;
	}
	
	  private class BackgroundTask extends AsyncTask<String, Void, Bitmap> {
		    protected Bitmap doInBackground(String... url) {
		      // ---download an image---
		      Bitmap bitmap = downloadImage(url[0]);
		      try {
		        Thread.sleep(1000);
		      } catch (InterruptedException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
		      return bitmap;
		    }

		    protected void onPostExecute(Bitmap bitmap) {
		      ImageView img = (ImageView) findViewById(R.id.img);
		      img.setImageBitmap(bitmap);
		    }
		  }
}
