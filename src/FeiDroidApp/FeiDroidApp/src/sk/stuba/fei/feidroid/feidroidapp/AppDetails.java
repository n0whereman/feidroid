package sk.stuba.fei.feidroid.feidroidapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AppDetails extends Activity {

	TextView appName, securityRisk;
	ListView permissions;
	ArrayAdapter<String> listAdapter ; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_details);
		
		appName = (TextView) findViewById(R.id.textView2);
		securityRisk = (TextView) findViewById(R.id.textView1);
		permissions = (ListView) findViewById(R.id.listView1);
		
		Bundle b = getIntent().getExtras();
		appName.setText(b.getString("app_name"));
		int app_num = b.getInt("app_num");
		
		String sec_level = "UNDETERMINED";
		securityRisk.setBackgroundResource(R.color.yellow);
		switch(app_num)
		{
			case 0:
				sec_level = "Security Risk";
				securityRisk.setBackgroundResource(R.color.yellow);
				break;
			case 1:
				sec_level = "Security Risk";
				securityRisk.setBackgroundResource(R.color.yellow);
				break;
			case 2:
				sec_level = "Safe";
				securityRisk.setBackgroundResource(R.color.green);
				break;
			case 3:
				sec_level = "Suspicious app";
				securityRisk.setBackgroundResource(R.color.orange);
				break;
			case 4:
				sec_level = "Security Risk";
				securityRisk.setBackgroundResource(R.color.yellow);
				break;
			case 5:
				sec_level = "Safe";
				securityRisk.setBackgroundResource(R.color.green);
				break;
			case 6:
				sec_level = "Safe";
				securityRisk.setBackgroundResource(R.color.green);
				break;
			case 7:
				sec_level = "Dangerous app";
				securityRisk.setBackgroundResource(R.color.red);
				break;				
		}

		securityRisk.setText(sec_level);
		
		listAdapter = new ArrayAdapter<String>(this, R.layout.listview_item, new ArrayList<String>());  
		
		boolean getSysPackages = true;
		PackageManager pm = getPackageManager();
		List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS | PackageManager.GET_PROVIDERS);
	    for(PackageInfo p : packages) {
	        if ((!getSysPackages) && (p.versionName == null)) {
	            continue ;
	        }
	        
	        if( p.applicationInfo.loadLabel(pm).toString().equalsIgnoreCase(b.getString("app_name")))
	        {
		        String[] requestedPermissions = p.requestedPermissions;
		        if(requestedPermissions != null) 
		        {
		        	listAdapter.addAll(requestedPermissions);
		        }
	        }
	    }
		 
		
		// Create ArrayAdapter using the planet list.  
		permissions.setAdapter(listAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_details, menu);
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
}
