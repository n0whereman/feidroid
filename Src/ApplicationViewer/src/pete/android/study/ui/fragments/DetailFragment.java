package pete.android.study.ui.fragments;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pete.android.study.R;
import pete.android.study.ui.activities.AnalysisActivity;
import pete.android.study.ui.activities.AnalysisDetailActivity;
import pete.android.study.ui.activities.DetailActivity;
import pete.android.study.utils.Utilities;
import pete.android.study.utils.RetrieveData; 

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.pm.Signature;
import android.database.DataSetObserver;
import android.drm.DrmStore.Action;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
/**
 * On this fragment displays the available information about the application
 * @author Peter Muska
 *
 */
public class DetailFragment extends Fragment{

	public static final String TAG = "DetailFragment";
	public static final String APP_INFO = "app_info";
	long app_id;
	
	private PackageInfo mPackageInfo;
	
	//@InjectView(R.id.app_icon)
	ImageView mAppIcon;
	//@InjectView(R.id.app_name)
	TextView mAppName;
	//@InjectView(R.id.package_name)
	TextView mPackageName;
	
	//@InjectView(R.id.class_name)
	TextView mClassName;
	//@InjectView(R.id.process_name)
	TextView mProcessName;
	//@InjectView(R.id.version_name)
	TextView mVersionName;
	//@InjectView(R.id.version_code)
	TextView mVersionCode;
	
	//@InjectView(R.id.sdk_version)
	TextView mSdkVersion;
	//@InjectView(R.id.install_date)
	TextView mInstallDate;
	//@InjectView(R.id.last_update)
	TextView mLastUpdate;
	
	//@InjectView(R.id.source_dir)
	TextView mSourceDir;
	//@InjectView(R.id.data_dir)
	TextView mDataDir;
	//@InjectView(R.id.app_permissions)
	TextView mAppPermissions;
	//@InjectView(R.id.app_flags)
	TextView mAppFlags;
	
	TextView mAppCategories;
	
	//@InjectView(R.id.spinner_category)
	Spinner mySpinner;
	
	Button btnAnalysis;
			
	String choice;
	
	public static DetailFragment newInstance(Bundle bundle){
		DetailFragment fragment = new DetailFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle bundle = getArguments();
		if(bundle != null)
			mPackageInfo = bundle.getParcelable(APP_INFO);
	}
	
	/**
	 * View function of Detail Activity 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_detail, container, false);
		
		try
		{
			//ButterKnife.inject(this, view);
			mAppIcon = (ImageView)view.findViewById(R.id.app_icon);
			mAppName = (TextView)view.findViewById(R.id.app_name);
			mPackageName = (TextView)view.findViewById(R.id.package_name);
			mClassName= (TextView)view.findViewById(R.id.class_name);
			mProcessName = (TextView)view.findViewById(R.id.process_name);
			mVersionName = (TextView)view.findViewById(R.id.version_name);
			mVersionCode = (TextView)view.findViewById(R.id.version_code);
			mSdkVersion = (TextView)view.findViewById(R.id.sdk_version);
			mInstallDate = (TextView)view.findViewById(R.id.install_date);
			mLastUpdate = (TextView)view.findViewById(R.id.last_update);
			mSourceDir = (TextView)view.findViewById(R.id.source_dir);
			mDataDir = (TextView)view.findViewById(R.id.data_dir);
			mAppPermissions = (TextView)view.findViewById(R.id.app_permissions);
			mAppFlags = (TextView)view.findViewById(R.id.app_flags);
			mDataDir = (TextView)view.findViewById(R.id.data_dir);
			//mySpinner = (Spinner)view.findViewById(R.id.spinner_category);
			mAppCategories = (TextView)view.findViewById(R.id.app_categories);
			btnAnalysis = (Button)view.findViewById(R.id.AppAnalysis);
		}
		catch (Exception ex)
		{
			String msg = ex.getLocalizedMessage();
		}
				
		PackageManager pm = getActivity().getPackageManager();
		
		mAppName.setText(mPackageInfo.applicationInfo.loadLabel(pm));
		mAppIcon.setImageDrawable(mPackageInfo.applicationInfo.loadIcon(pm));
		mPackageName.setText(mPackageInfo.packageName);
		mClassName.setText(mPackageInfo.applicationInfo.className);
		mProcessName.setText(mPackageInfo.applicationInfo.processName);
		
		mVersionName.setText(mPackageInfo.versionName);
		mVersionCode.setText(String.valueOf(mPackageInfo.versionCode));
		mSdkVersion.setText(String.valueOf(mPackageInfo.applicationInfo.targetSdkVersion));
		
		String[] installUpdateTimes = getInstallUpdateTimes(mPackageInfo.packageName);
		mInstallDate.setText(installUpdateTimes[0]);		
		mLastUpdate.setText(installUpdateTimes[1]);
		
		mSourceDir.setText(mPackageInfo.applicationInfo.sourceDir);
		mDataDir.setText(mPackageInfo.applicationInfo.dataDir);
		
		app_id = 1;
		String name = mAppName.getText().toString();
		
		if(name.equals("Facebook") || name.equals("Twitter") || name.equals("Messenger")) app_id = 2;//Social
		else if(name.equals("VLC") || name.equals("Lime Theme")) app_id = 3;//Multimedia
			 else if(name.equals("Throne Rush"))app_id = 4;//Games
				  else if(name.equals("File Manager") || name.equals("ProxyDroid") || name.equals("SuperSU")) app_id = 5;//Tools
					   else app_id = -1;
		
		app_id = 5;
		
//	    List<String> list = new ArrayList<String>();
		StringBuilder categoryStringBuilder = new StringBuilder();
		final String notAvaiable = "N/A";
		if(app_id > 0)
		{
			try
			{
				//result = new RetrieveData().execute("http://thanos.feidroid.mobi:8080/FEIDroid-0.0.1-SNAPSHOT/api/application").get();
				String result = new RetrieveData(getActivity()).execute("https://thanos.feidroid.mobi:8443/FEIDroid/api/application/"+app_id+"/categories").get();

				if(result != null)
				{
					JSONArray appCategories = new JSONArray(result);
					int categoryCount = appCategories.length();
					JSONObject obj;
					for(int i=0; i<categoryCount; i++)
					{
						obj = appCategories.getJSONObject(i);
						//list.add(obj.optString("title"));
						categoryStringBuilder.append(obj.optString("title"));
						categoryStringBuilder.append(notAvaiable);
					}
				}
				else categoryStringBuilder.append(notAvaiable);
					
			}
			catch(JSONException e1){
				categoryStringBuilder.append(notAvaiable);
			}
			catch(ExecutionException e2){
				categoryStringBuilder.append(notAvaiable);
			}
			catch(InterruptedException e3){
				categoryStringBuilder.append(notAvaiable);
			}
		}
		else
		{
			//list.add(notAvaiable);
			categoryStringBuilder.append(notAvaiable);
		}
				
		mAppCategories.setText(categoryStringBuilder.toString());
//		adapter = new ArrayAdapter<String>(this.getActivity(),
//	            android.R.layout.simple_spinner_item, list);
//	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//	    mySpinner.setAdapter(adapter);
//		
//		mySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int position, long arg3) {
//				// TODO Auto-generated method stub
//				choice = arg0.getItemAtPosition(position).toString();	
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				
//			}
//			 
//		});
	
		//permissions
		if(mPackageInfo.requestedPermissions != null) {
			StringBuilder builder = new StringBuilder();
			int i = 0;
			for(String p : mPackageInfo.requestedPermissions) {
				
				if(p.startsWith("android")) {
					p = p.substring(p.lastIndexOf(".") + 1) ;
					builder.append(p).append("\n");
					
					mPackageInfo.requestedPermissions[i] = p;
				}
				i++;
			}
			
			mAppPermissions.setText(builder.toString());
		}
		
		//flags
		mAppFlags.setText(String.valueOf(mPackageInfo.applicationInfo.flags));
		
		btnAnalysis.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(getActivity(), AnalysisActivity.class);
        		intent.putExtra(AnalysisFragment.PERM, mPackageInfo.requestedPermissions);
        		intent.putExtra(AnalysisFragment.CATEGORY, choice);
        		intent.putExtra("APP_ID", app_id);
        		
        		startActivity(intent);            
        	}
        });
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	/**
	 * Action for Analysis Button
	 */
	@OnClick(R.id.AppAnalysis)
	public void onAnalisisClick(){
		Intent intent = new Intent(getActivity(), AnalysisActivity.class);
		intent.putExtra(AnalysisFragment.PERM, mPackageInfo.requestedPermissions);
		intent.putExtra(AnalysisFragment.CATEGORY, choice);
		intent.putExtra("APP_ID", app_id);
		
		startActivity(intent);
	}
	/**
	 * Function to get a date 
	 * @param pckName
	 * @return date - in format dd.mm.yy
	 */
	private String[] getInstallUpdateTimes(String pckName) {
		long installMillis = 0;
		long updateMillis = 0;
		try {
			installMillis = getActivity().getPackageManager().getPackageInfo(pckName, 0).firstInstallTime;
			updateMillis = getActivity().getPackageManager().getPackageInfo(pckName, 0).lastUpdateTime;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage().toString());
			//e.printStackTrace();
			return new String[]{"-", "-"};
		}
		
		return new String[]{ DateFormat.format("dd.MM.yyyy", new Date(installMillis)).toString(),
							DateFormat.format("dd.MM.yyyy", new Date(updateMillis)).toString()};
	}
}
