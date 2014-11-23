package sk.stuba.fei.feidroid.feidroidapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {

	ListView apps;
	ArrayAdapter<String> listAdapter ;  
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);
        
        apps = (ListView)findViewById(R.id.listView1);
        
        String[] planets = new String[] { "Facebook", "Twitter", "VLC", "OrBot",  
                "Throne Rush", "Calculator C++", "FileManager", "Messenger"};    
		ArrayList<String> planetList = new ArrayList<String>();  
		planetList.addAll( Arrays.asList(planets) );  
		
		// Create ArrayAdapter using the planet list.  
		listAdapter = new ArrayAdapter<String>(this, R.layout.listview_item, planetList);  
		
		// Add more planets. If you passed a String[] instead of a List<String>   
		// into the ArrayAdapter constructor, you must not add more items.   
		// Otherwise an exception will occur.  
		//listAdapter.add( "Ceres" );  
		//listAdapter.add( "Pluto" );  
		//listAdapter.add( "Haumea" );  
		//listAdapter.add( "Makemake" );  
		//listAdapter.add( "Eris" );  
		
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
}
