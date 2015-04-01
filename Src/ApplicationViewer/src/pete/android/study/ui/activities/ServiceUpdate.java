package pete.android.study.ui.activities;


import java.util.List;

import pete.android.study.utils.PostData;
import pete.android.study.utils.Utilities;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
		        PostData sender = new PostData(getApplicationContext());
		        
		        if (p.applicationInfo.sourceDir.startsWith("/data/app/")) 
		        {
		        	SendData(p, sender);
		        }
		    }
	}
	
	public void postChanges(){
		 List<PackageInfo> packs = getPackageManager().getInstalledPackages(PackageManager.GET_SIGNATURES | PackageManager.GET_ACTIVITIES);
		    for(int i=0;i<packs.size();i++) {
		        PackageInfo p = packs.get(i);
		        PostData sender = new PostData(getApplicationContext());
		        
		        if (p.applicationInfo.sourceDir.startsWith("/data/app/")) {//only non-system apps
		        	if(changedPackage.contains(p.packageName))
		        	{
		        		SendData(p, sender);
		        	}
		        }
		    }
	}
	
	private void SendData(PackageInfo pckage, PostData sender)
	{
		try
		{
			String url = "https://thanos.feidroid.mobi:8443/FEIDroid/api/application";
			String name = pckage.applicationInfo.loadLabel(getPackageManager()).toString();
			String version = pckage.versionName;
			String description;
			try
			{
				description = pckage.applicationInfo.loadDescription(getPackageManager()).toString();
			}
			catch (Exception e)
			{
				description = "";
			}
			String packageName = pckage.packageName;
			String sha1hash = Utilities.GetFingerprint(pckage);		
			
			sender.execute(url, name, packageName, version, sha1hash, description);
		}
		catch(Exception ex)
		{
			String s = ex.getMessage();			
		}
	}
		
}
