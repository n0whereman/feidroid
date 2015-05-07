package sk.stuba.fei.feidroid.feidroidbeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.feidroid.feidroidbeta.R;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AppActivity extends Activity {


	private List<String> toStringList = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		String value = intent.getStringExtra("title"); //if it's a string you stored.
		String group = intent.getStringExtra("group");		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perm_list);
		 Toast.makeText(getApplicationContext(),
					value, Toast.LENGTH_SHORT).show();
		 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perm_list);
		xmlFile xml = new xmlFile();
	 
			 
			 //perm
			 PackageManager pm = getPackageManager();
		try {
			 PackageInfo packageInfo = pm.getPackageInfo(value, PackageManager.GET_PERMISSIONS);
			//Get Permissions
			    String[] requestedPermissions = packageInfo.requestedPermissions;

			    toStringList = new ArrayList<String>();				
			  
			    int list = 0;
			    if(requestedPermissions != null) {
			       for (int i = 0; i < requestedPermissions.length; i++) {	
			    	   list = xml.getList(getApplicationContext(),requestedPermissions[i],group);
			          toStringList.add(list+requestedPermissions[i]);
			       }
			 }
		} catch (NameNotFoundException e) {
		    e.printStackTrace();
		}    
			    
			MyAdapter adapter = new MyAdapter(this, toStringList);
			
			 ListView listView=(ListView)findViewById(R.id.listViewZoznamPerm);
			/* int[] colors = {0, 0xFFFF0000, 0}; // red for the example
			 listView.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
			 listView.setDividerHeight(1);
			*/
			 // listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_perm, toStringList));	
			 listView.setAdapter(adapter);	
				
	}	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.appmenu, menu);
		
	
		
		return true;
	}	

	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {		
		 
		 final xmlFile xml = new xmlFile();	
	        if(item.getItemId() == R.id.item1){
	        	//Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.calculator2");
	        	//startActivity(LaunchIntent);
	        	 startActivity(new Intent("android.settings.APP_OPS_SETTINGS"));
	        }	 
	        return super.onOptionsItemSelected(item);
	    }

}
