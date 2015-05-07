package sk.stuba.fei.feidroid.feidroidservice;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//startService(new Intent(this, MainService.class));
		
		String url = "http://thanos.feidroid.mobi:8080/FEIDroid-0.0.1-SNAPSHOT/api/application:";
		String payload = "{“title”: ”TestApp”, “description”: “TestApp from Service”, “version”: ”1.0.0”}";
		String result = "NULL";
		try 
		{
			result = new SendData().execute(url, payload).get();
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ExecutionException e) {
			// TODO Auto-generated catch blocka
			e.printStackTrace();
		}
		Log.i("Danny", result);
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
