package sk.stuba.fei.feidroid.feidroidservice;

import java.util.concurrent.ExecutionException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		String url = "http://thanos.feidroid.mobi:8080/FEIDroid-0.0.1-SNAPSHOT/api/application";
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
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		String url = "http://thanos.feidroid.mobi:8080/FEIDroid-0.0.1-SNAPSHOT/api/application";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("Danny", result);
		
		return START_STICKY;
	}
}
