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
	
	int flags = PackageManager.GET_META_DATA | 
            PackageManager.GET_SHARED_LIBRARY_FILES |   
            PackageManager.GET_UNINSTALLED_PACKAGES | 
            PackageManager.GET_PERMISSIONS |
            PackageManager.GET_PROVIDERS |
            PackageManager.GET_SIGNATURES | 
            PackageManager.GET_ACTIVITIES;

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
		 List<PackageInfo> packs = getPackageManager().getInstalledPackages(flags);
	     PostData sender = new PostData(getApplicationContext());
		 for(int i=0;i<packs.size();i++) {
		     PackageInfo p = packs.get(i);
		     
		     if (p.applicationInfo.sourceDir.startsWith("/data/app/")) 
		     {
		    	sender.setPckgInfo(p);
		      	SendData(p, sender);
		     }
		 }
	}
	
	public void postChanges(){
		 List<PackageInfo> packs = getPackageManager().getInstalledPackages(flags);
	     PostData sender = new PostData(getApplicationContext());
		 for(int i=0;i<packs.size();i++) {
		     PackageInfo p = packs.get(i);
		     
		     if (p.applicationInfo.sourceDir.startsWith("/data/app/")) {//only non-system apps
		     	if(changedPackage.contains(p.packageName))
		     	{
		     		sender.setPckgInfo(p);
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
			
       		
    		int appId = Utilities.GetAppIdFromDatabase(this, name);
    		
    		if(appId == -1) return;
    		
    		url = "https://thanos.feidroid.mobi:8443/FEIDroid/api/application/" + appId + "/permissions";
    		sender = new PostData(sender);
    		sender.execute(url, "$send_permissions");

    		int s = appId + 2;
		}
		catch(Exception ex)
		{
			String s = ex.getMessage();			
		}
	}
		
}
