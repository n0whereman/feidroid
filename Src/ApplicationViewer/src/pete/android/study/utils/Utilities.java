package pete.android.study.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.content.pm.Signature;
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
	            PackageManager.GET_PROVIDERS |
	            PackageManager.GET_SIGNATURES;

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
	
	public static String GetFingerprint(PackageInfo packageInfo)
	{
        Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();

        InputStream input = new ByteArrayInputStream(cert);

        CertificateFactory cf = null;
        try 
        {
        	cf = CertificateFactory.getInstance("X509");
        } 
        catch (CertificateException e) 
        {
        	e.printStackTrace();
        	return e.getMessage();
        }
        X509Certificate c = null;
        try 
        {
        	c = (X509Certificate) cf.generateCertificate(input);
        } 
        catch (CertificateException e) 
        {
        	e.printStackTrace();
        	return e.getMessage();
        }


        try 
        {        	
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(c.getEncoded());
            
            byte[] publicKey = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<publicKey.length;i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]);
                if(appendString.length()==1)hexString.append("0");
                hexString.append(appendString);
                if(i < publicKey.length -1 )hexString.append(':');
                }

            return hexString.toString().toUpperCase();

        } 
        catch (Exception e1) 
        {
            e1.printStackTrace();
            return e1.getMessage();
        } 
	}
	
	public static List<String> ClearPermissionsNames(List<String> permNames)
	{
		List<String> cleared = new ArrayList<String>();
		
		for(String perm : permNames)
		{
			cleared.add(perm.replace("android.permission.", ""));
		}
		
		return cleared;
	}
	
	public static int GetAppIdFromDatabase(Context context, String appName)
	{
		try
		{
			String result = new RetrieveData(context).execute("https://thanos.feidroid.mobi:8443/FEIDroid/api/application/find?name=" + appName).get();
			JSONArray apps = new JSONArray(result);
			if(apps.length() > 0)
			{
				JSONObject obj = apps.getJSONObject(0);
				return Integer.parseInt(obj.optString("id"));
			}
		}
		catch(Exception ex)
		{
		}
		
		return -1;
	}
}
