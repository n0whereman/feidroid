package pete.android.study.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class PostData extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
    	try {
        	URL url = new URL("http://thanos.feidroid.mobi:8080/FEIDroid-0.0.1-SNAPSHOT/api/application");
        	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        	conn.setDoOutput(true);
        	conn.setRequestMethod("POST");
        	conn.setRequestProperty("Content-Type", "application/json");
        	
        	JSONObject jo = new JSONObject();
        	jo.put("name", params[0]);
        	jo.put("version", params[1]);
        	
        	String input = jo.toString();

        	OutputStream os = conn.getOutputStream();
        	os.write(input.getBytes());
        	os.flush();

        	if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
        		throw new RuntimeException("Failed : HTTP error code : "
        				+ conn.getResponseCode());
        	}

        	BufferedReader br = new BufferedReader(new InputStreamReader(
        			(conn.getInputStream())));

        	String output;
        	System.out.println("Output from Server .... \n");
        	
        	while ((output = br.readLine()) != null) {
        		System.out.println(output);
        	}

        		conn.disconnect();

        	} catch (MalformedURLException e) {

        		e.printStackTrace();

        	} catch (IOException e) {

        		e.printStackTrace();

        	} catch (JSONException e) {
				e.printStackTrace();
			}  
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
    }

}