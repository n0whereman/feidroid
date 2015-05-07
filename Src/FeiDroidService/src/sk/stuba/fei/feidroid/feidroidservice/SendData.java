package sk.stuba.fei.feidroid.feidroidservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class SendData extends AsyncTask<String, Void, String> {

	protected String doInBackground(String... urls) 
    {
		// Prepare a request object
    	//String url = "http://danny-n.webpark.cz/files/test.json";
    	String url = urls[0];

        try {
        	HttpPost request = new HttpPost(url);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");

			StringEntity entity = new StringEntity(urls[1]);
			request.setEntity(entity);

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);

			int responseCode = response.getStatusLine().getStatusCode();

			if (responseCode == 200) {
				InputStream ips = response.getEntity().getContent();
				BufferedReader buf = new BufferedReader(new InputStreamReader(
						ips, "UTF-8"));
				StringBuilder sb = new StringBuilder();
				String s;
				while (true) {
					s = buf.readLine();
					if (s == null || s.length() == 0)
						break;
					sb.append(s);

				}
				buf.close();
				ips.close();
				
				return sb.toString();
			}
			
			return "An error occured : " + responseCode;

        } 
        catch (Exception e) 
        {
        	String m = e.getMessage();
        	
        	return m;
        }    	
    }

    private static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
