package sk.stuba.fei.feidroid.feidroidbeta;

import sk.stuba.fei.feidroid.feidroidbeta.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GroupActivity extends Activity {

	private String group = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		String value = intent.getStringExtra("title"); //if it's a string you stored.
				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_list);
		 Toast.makeText(getApplicationContext(),
					value, Toast.LENGTH_SHORT).show();
		group = value;
		 xmlFile xml = new xmlFile();
		 //list
		 ListView listView=(ListView)findViewById(R.id.listViewZoznamApp);
		 if(value.compareTo("ostatné")!=0)
			 listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_group,  xml.readApp(getApplicationContext(),value)));	
		 else
			 listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_group,  xml.readAppOther(getApplicationContext())));	
		 
		 listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					 setAppList(((TextView) view).getText());	
					 
				}
			});
	}
	
	public void setAppList(CharSequence title){		
		Intent myIntent = new Intent(GroupActivity.this, AppActivity.class);
		myIntent.putExtra("title",  title);
		myIntent.putExtra("group",  group);
		GroupActivity.this.startActivity(myIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
