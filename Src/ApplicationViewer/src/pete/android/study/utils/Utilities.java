package pete.android.study.utils;

import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.widget.Toast;
//import java.util.ArrayList;

public class Utilities {
	
	/**
	 * Get all installed application on device and return a list
	 * @param	c	Context of application
	 * @return	list of installed applications
	 */
	public static List<PackageInfo> getInstalledApplication(Context c) {
		
		//return c.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
		//return c.getPackageManager().getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		int Flags = PackageManager.GET_META_DATA | 
	            PackageManager.GET_SHARED_LIBRARY_FILES |   
	            PackageManager.GET_UNINSTALLED_PACKAGES | 
	            PackageManager.GET_PERMISSIONS |
	            PackageManager.GET_PROVIDERS;

		//List<ApplicationInfo> list = c.getPackageManager().getInstalledApplications(Flags);
		List<PackageInfo> list = c.getPackageManager().getInstalledPackages(Flags);
		
		//String StartString = "com.sony";
		 
		for (int n=0; n<list.size(); n++) 
		{
			final int appFlags = list.get(n).applicationInfo.flags;
			//final String pkgName = list.get(n).packageName; 
	          if((appFlags & ApplicationInfo.FLAG_SYSTEM) != 0
	        	|| (appFlags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) 
	          {
	        	  //IS A SYSTEM APP or APP WAS INSTALL AS AN UPDATE TO A BUILD-IN SYSTEM APP
	        	  list.remove(list.get(n));
	        	  n--;
	          }	          
	            
//	          if (pkgName.startsWith(StartString) == true)
//	          {
//	        	  list.remove(list.get(n));
//	        	  n--;
//	          } 
	      }     
		
		return list;      
	}
	
	/**
	 * Launch an application
	 * @param	c	Context of application
	 * @param	pm	the related package manager of the context
	 * @param	pkgName	Name of the package to run
	 */
	
	public static boolean launchApp(Context c, PackageManager pm, String pkgName) {
		// query the intent for lauching 
		Intent intent = pm.getLaunchIntentForPackage(pkgName);
		// if intent is available
		if(intent != null) {
			try {
				// launch application
				c.startActivity(intent);
				// if succeed
				return true;
			
			// if fail
			} catch(ActivityNotFoundException ex) {
				// quick message notification
				Toast toast = Toast.makeText(c, "Application Not Found", Toast.LENGTH_LONG);
				// display message
				toast.show();
			}
		}
		// by default, fail to launch
		return false;
	}
	
}
