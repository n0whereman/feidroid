package pete.android.study.ui.activities;


import java.util.List;

import pete.android.study.utils.PostData;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.widget.Toast;

public class ServiceUpdate extends Service{	
	
	public static String POST_WHAT = "";
	public static String changedPackage = "";

	@Override
	public IBinder onBind(Intent intent) {		
		return null;
	}

	@Override
	public void onCreate() {		
		super.onCreate();
		Toast.makeText(getApplicationContext(), "FeiDroid is posting Data..", Toast.LENGTH_LONG).show();
		if(POST_WHAT.equals("all_apps")){
			postAllApps();
		}
		if(POST_WHAT.equals("changes")){
			postChanges();
		}
        this.stopSelf();
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_LONG).show();
		super.onDestroy();
	}
	
	public void postAllApps(){
		 List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
		    for(int i=0;i<packs.size();i++) {
		        PackageInfo p = packs.get(i);
		        
		        if (p.applicationInfo.sourceDir.startsWith("/data/app/")) {
		        	String appname = p.applicationInfo.loadLabel(getPackageManager()).toString();		        
		        	//String pname = p.packageName;
		        	String versionName = p.versionName;
		        	//int versionCode = p.versionCode;
		        	
		        	new PostData().execute(appname,versionName);
		        }
		    }
	}
	
	public void postChanges(){
		 List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
		    for(int i=0;i<packs.size();i++) {
		        PackageInfo p = packs.get(i);
		        
		        if (p.applicationInfo.sourceDir.startsWith("/data/app/")) {//only non-system apps
		        	if(changedPackage.contains(p.packageName)){
		        		String appname = p.applicationInfo.loadLabel(getPackageManager()).toString();		        
		        		//String pname = p.packageName;
		        		String versionName = p.versionName;
		        		//int versionCode = p.versionCode;
		    		Toast.makeText(getApplicationContext(), appname + " " + versionName, Toast.LENGTH_LONG).show();
		        	new PostData().execute(appname,versionName);
		        	}
		        }
		    }
	}
		
}
