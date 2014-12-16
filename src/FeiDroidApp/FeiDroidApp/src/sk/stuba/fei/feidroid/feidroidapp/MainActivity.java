package sk.stuba.fei.feidroid.feidroidapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity 
{
	ListView apps;
	ArrayAdapter<String> listAdapter ;  
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);
        
        String url = "http://danny-n.webpark.cz/files/test.json";
        //connect(url);
        new RetrieveFeedTask().execute(url);
        
        
        apps = (ListView)findViewById(R.id.listView1);   
        
		// Create ArrayAdapter using the planet list.  
		listAdapter = new ArrayAdapter<String>(this, R.layout.listview_item, new ArrayList<String>());  		
		
		boolean getSysPackages = true;
		PackageManager pm = getPackageManager();
		//List<PackageInfo> packages = pm.getInstalledPackages(0);
		List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS | PackageManager.GET_PROVIDERS);
	    for(PackageInfo p : packages) 
	    {
	        if ((!getSysPackages) && (p.versionName == null)) {
	            continue ;
	        }
	        String appname = p.applicationInfo.loadLabel(pm).toString();
	        listAdapter.add(appname);
	    }
		
		// Set the ArrayAdapter as the ListView's adapter.  
		apps.setAdapter( listAdapter );    
	
		apps.setOnItemClickListener(new OnItemClickListener() {
           
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) 
			{				
				Intent intent = new Intent();	
				intent.setClass(getApplicationContext(), AppDetails.class);
				Bundle b = new Bundle();
				b.putString("app_name", listAdapter.getItem(position).toString());
				b.putInt("app_num", position);
				intent.putExtras(b);
				startActivity(intent);

			}
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void connect(String url)
    {
        HttpClient httpclient = new DefaultHttpClient();

        // Prepare a request object
        HttpGet httpget = new HttpGet(url); 

        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            // Examine the response status
            int code = response.getStatusLine().getStatusCode();

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to worry about connection release
            
            if (entity != null) 
            {
                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                // now you have the string representation of the HTML request
                instream.close();
            }


        } 
        catch (Exception e) 
        {
        	String m = e.getMessage();
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

class RetrieveFeedTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... urls) 
    {
    	HttpClient httpclient = new DefaultHttpClient();

        // Prepare a request object
    	String url = "http://danny-n.webpark.cz/files/test.json";
        HttpGet httpget = new HttpGet(url); 

        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            // Examine the response status
            int code = response.getStatusLine().getStatusCode();

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to worry about connection release
            
            if (entity != null) 
            {
                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                // now you have the string representation of the HTML request
                instream.close();
                
                return result;
            }

        } 
        catch (Exception e) 
        {
        	String m = e.getMessage();
        	
        	return m;
        }
		return "SOMETHING";
    	
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