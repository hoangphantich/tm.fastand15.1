package com.nguyentiendung.online;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
public class JSONParser extends Activity{
    static InputStream is = null;
    static String json = "";
    // constructor
    public JSONParser() {
    }
    public static String getJSONFromUrl(String url) {    //method này trả về một chuỗi JSON từ một URL
    	
        // Making HTTP request
    	String result = "";
        try {
        	HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            is = httpResponse.getEntity().getContent();
            if(is != null)
            result= convertInputStreamToString(is);	
            else result = "";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;
     
            inputStream.close();
            return result;
     
        }

}