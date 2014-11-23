package sk.stuba.fei.feidroid.feidroidapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
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
		
		String[] permissions_values = new String[] { "INTERNET", "WRITE_EXTERNAL_STORAGE", "ACCES_FINE_LOCATION", "READ_PHONE_STATE" };    
		ArrayList<String> planetList = new ArrayList<String>();  
		planetList.addAll( Arrays.asList(permissions_values) );  
		
		// Create ArrayAdapter using the planet list.  
		listAdapter = new ArrayAdapter<String>(this, R.layout.listview_item, planetList);
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
