package sk.stuba.fei.feidroid.feidroidbeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.feidroid.feidroidbeta.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SecondActivity extends Activity {

	private String group = null;
	Context context;
	String value;
	ListView listView;
	static String kat[] = {
		"android.permission.ACCESS_CHECKIN_PROPERTIES",
		"android.permission.ACCESS_COARSE_LOCATION",
		"android.permission.ACCESS_FINE_LOCATION",
		"android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",
		"android.permission.ACCESS_MOCK_LOCATION",
		"android.permission.ACCESS_NETWORK_STATE",
		"android.permission.ACCESS_SURFACE_FLINGER",
		"android.permission.ACCESS_WIFI_STATE",
		"android.permission.ACCOUNT_MANAGER",
		"android.permission.ADD_VOICEMAIL",
		"android.permission.AUTHENTICATE_ACCOUNTS",
		"android.permission.BATTERY_STATS",
		"android.permission.BIND_ACCESSIBILITY_SERVICE",
		"android.permission.BIND_APPWIDGET",
		"android.permission.BIND_DEVICE_ADMIN",
		"android.permission.BIND_INPUT_METHOD",
		"android.permission.BIND_NFC_SERVICE",
		"android.permission.BIND_NOTIFICATION_LISTENER_SERVICE",
		"android.permission.BIND_PRINT_SERVICE",
		"android.permission.BIND_REMOTEVIEWS",
		"android.permission.BIND_TEXT_SERVICE",
		"android.permission.BIND_VPN_SERVICE",
		"android.permission.BIND_WALLPAPER",
		"android.permission.BLUETOOTH",
		"android.permission.BLUETOOTH_ADMIN",
		"android.permission.BLUETOOTH_PRIVILEGED",
		"android.permission.BRICK",
		"android.permission.BROADCAST_PACKAGE_REMOVED",
		"android.permission.BROADCAST_SMS",
		"android.permission.BROADCAST_STICKY",
		"android.permission.BROADCAST_WAP_PUSH",
		"android.permission.CALL_PHONE",
		"android.permission.CALL_PRIVILEGED",
		"android.permission.CAMERA",
		"android.permission.CAPTURE_AUDIO_OUTPUT",
		"android.permission.CAPTURE_SECURE_VIDEO_OUTPUT",
		"android.permission.CAPTURE_VIDEO_OUTPUT",
		"android.permission.CHANGE_COMPONENT_ENABLED_STATE",
		"android.permission.CHANGE_CONFIGURATION",
		"android.permission.CHANGE_NETWORK_STATE",
		"android.permission.CHANGE_WIFI_MULTICAST_STATE",
		"android.permission.CHANGE_WIFI_STATE",
		"android.permission.CLEAR_APP_CACHE",
		"android.permission.CLEAR_APP_USER_DATA",
		"android.permission.CONTROL_LOCATION_UPDATES",
		"android.permission.DELETE_CACHE_FILES",
		"android.permission.DELETE_PACKAGES",
		"android.permission.DEVICE_POWER",
		"android.permission.DIAGNOSTIC",
		"android.permission.DISABLE_KEYGUARD",
		"android.permission.DUMP",
		"android.permission.EXPAND_STATUS_BAR",
		"android.permission.FACTORY_TEST",
		"android.permission.FLASHLIGHT",
		"android.permission.FORCE_BACK",
		"android.permission.GET_ACCOUNTS",
		"android.permission.GET_PACKAGE_SIZE",
		"android.permission.GET_TASKS",
		"android.permission.GET_TOP_ACTIVITY_INFO",
		"android.permission.GLOBAL_SEARCH",
		"android.permission.HARDWARE_TEST",
		"android.permission.INJECT_EVENTS",
		"android.permission.INSTALL_LOCATION_PROVIDER",
		"android.permission.INSTALL_PACKAGES",
		"android.permission.INSTALL_SHORTCUT",
		"android.permission.INTERNAL_SYSTEM_WINDOW",
		"android.permission.INTERNET",
		"android.permission.KILL_BACKGROUND_PROCESSES",
		"android.permission.LOCATION_HARDWARE",
		"android.permission.MANAGE_ACCOUNTS",
		"android.permission.MANAGE_APP_TOKENS",
		"android.permission.MANAGE_DOCUMENTS",
		"android.permission.MASTER_CLEAR",
		"android.permission.MEDIA_CONTENT_CONTROL",
		"android.permission.MODIFY_AUDIO_SETTINGS",
		"android.permission.MODIFY_PHONE_STATE",
		"android.permission.MOUNT_FORMAT_FILESYSTEMS",
		"android.permission.MOUNT_UNMOUNT_FILESYSTEMS",
		"android.permission.NFC",
		"android.permission.PERSISTENT_ACTIVITY",
		"android.permission.PROCESS_OUTGOING_CALLS",
		"android.permission.READ_CALENDAR",
		"android.permission.READ_CALL_LOG",
		"android.permission.READ_CONTACTS",
		"android.permission.READ_EXTERNAL_STORAGE",
		"android.permission.READ_FRAME_BUFFER",
		"android.permission.READ_HISTORY_BOOKMARKS",
		"android.permission.READ_INPUT_STATE",
		"android.permission.READ_LOGS",
		"android.permission.READ_PHONE_STATE",
		"android.permission.READ_PROFILE",
		"android.permission.READ_SMS",
		"android.permission.READ_SOCIAL_STREAM",
		"android.permission.READ_SYNC_SETTINGS",
		"android.permission.READ_SYNC_STATS",
		"android.permission.READ_USER_DICTIONARY",
		"android.permission.REBOOT",
		"android.permission.RECEIVE_BOOT_COMPLETED",
		"android.permission.RECEIVE_MMS",
		"android.permission.RECEIVE_SMS",
		"android.permission.RECEIVE_WAP_PUSH",
		"android.permission.RECORD_AUDIO",
		"android.permission.REORDER_TASKS",
		"android.permission.RESTART_PACKAGES",
		"android.permission.SEND_RESPOND_VIA_MESSAGE",
		"android.permission.SEND_SMS",
		"android.permission.SET_ACTIVITY_WATCHER",
		"android.permission.SET_ALARM",
		"android.permission.SET_ALWAYS_FINISH",
		"android.permission.SET_ANIMATION_SCALE",
		"android.permission.SET_DEBUG_APP",
		"android.permission.SET_ORIENTATION",
		"android.permission.SET_POINTER_SPEED",
		"android.permission.SET_PREFERRED_APPLICATIONS",
		"android.permission.SET_PROCESS_LIMIT",
		"android.permission.SET_TIME",
		"android.permission.SET_TIME_ZONE",
		"android.permission.SET_WALLPAPER",
		"android.permission.SET_WALLPAPER_HINTS",
		"android.permission.SIGNAL_PERSISTENT_PROCESSES",
		"android.permission.STATUS_BAR",
		"android.permission.SUBSCRIBED_FEEDS_READ",
		"android.permission.SUBSCRIBED_FEEDS_WRITE",
		"android.permission.SYSTEM_ALERT_WINDOW",
		"android.permission.TRANSMIT_IR",
		"android.permission.UNINSTALL_SHORTCUT",
		"android.permission.UPDATE_DEVICE_STATS",
		"android.permission.USE_CREDENTIALS",
		"android.permission.USE_SIP",
		"android.permission.VIBRATE",
		"android.permission.WAKE_LOCK",
		"android.permission.WRITE_APN_SETTINGS",
		"android.permission.WRITE_CALENDAR",
		"android.permission.WRITE_CALL_LOG",
		"android.permission.WRITE_CONTACTS",
		"android.permission.WRITE_EXTERNAL_STORAGE",
		"android.permission.WRITE_GSERVICES",
		"android.permission.WRITE_HISTORY_BOOKMARKS",
		"android.permission.WRITE_PROFILE",
		"android.permission.WRITE_SECURE_SETTINGS",
		"android.permission.WRITE_SETTINGS",
		"android.permission.WRITE_SMS",
		"android.permission.WRITE_SOCIAL_STREAM",
		"android.permission.WRITE_SYNC_SETTINGS",
		"android.permission.WRITE_USER_DICTIONARY"		    				
};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		value = intent.getStringExtra("title"); //if it's a string you stored.
		
		
		setContentView(R.layout.app_list);
		 Toast.makeText(getApplicationContext(),
					value, Toast.LENGTH_SHORT).show();
		group = value;
		context = getApplicationContext();
		 xmlFile xml = new xmlFile();
		 //list
		 ListView listView=(ListView)findViewById(R.id.listViewZoznamApp);
		 if(value.compareTo("ostatn�")!=0)
			 listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_group,  xml.readApp(context,value)));	
		 else
			 listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_group,  xml.readAppOther(context)));	
		 
		 listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					int nr = ((TextView) view).getText().toString().indexOf(System.getProperty("line.separator"))+1;
					
					 setAppList(((TextView) view).getText().subSequence(nr, ((TextView) view).getText().length()));	
					 
				}
			});
	}
	
	public void setAppList(CharSequence title){		
		Intent myIntent = new Intent(SecondActivity.this, AppActivity.class);
		myIntent.putExtra("title",  title);
		myIntent.putExtra("group",  group);
		SecondActivity.this.startActivity(myIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if(value.compareTo("ostatn�")==0)
			getMenuInflater().inflate(R.menu.groupmenuostatne, menu);
		else
			getMenuInflater().inflate(R.menu.groupmenu, menu);
		return true;
	}
	
	Context cont;
	String appl;
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {		
		    xmlFile xml = new xmlFile();	 
		 
		    if(value.compareTo("ostatn�")==0){
		        if(item.getItemId() == R.id.item1){	        	
		        	List<String> kat = xml.readAppOther(context);
		        	final CharSequence[] choise = kat.toArray(new CharSequence[kat.size()]);	        	
		        
		        	    AlertDialog.Builder alert = new AlertDialog.Builder(this);
		        	    cont = this;
		        	    alert.setTitle("Aplik�cia:");
		        	    alert.setSingleChoiceItems(choise,-1, new DialogInterface.OnClickListener()
		        	    {
		        	        @Override
		        	        public void onClick(DialogInterface dialog, int which) 
		        	        {
		        	        	xmlFile xml = new xmlFile();
		        	        	appl = choise[which].toString();
		        	        	List<String> kat = xml.readGroup(context,true);
		    		        	final CharSequence[] choise = kat.toArray(new CharSequence[kat.size()]);
		        	        	AlertDialog.Builder alert = new AlertDialog.Builder(cont);
				        	    alert.setTitle("Kateg�ria:");
				        	    alert.setSingleChoiceItems(choise,-1, new DialogInterface.OnClickListener()
				        	    {
				        	        @Override
				        	        public void onClick(DialogInterface dialog, int which) 
				        	        {		        	        	
				        	        	
				        	        	try {
				        	        		xmlFile xml = new xmlFile();
				        	        		int nr = appl.toString().indexOf(System.getProperty("line.separator"))+1;	        					
				        	        	
			        						xml.addXMLTag(appl.substring(nr, appl.length()), "App", choise[which].toString(),context);					
			        						 ListView listView=(ListView)findViewById(R.id.listViewZoznamApp);
			        						listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readAppOther(context)));	
			        					} catch (IllegalArgumentException e) {
			        						// TODO Auto-generated catch block
			        						e.printStackTrace();
			        					} catch (IllegalStateException e) {
			        						// TODO Auto-generated catch block
			        						e.printStackTrace();
			        					} catch (IOException e) {
			        						// TODO Auto-generated catch block
			        						e.printStackTrace();
			        					}
				        	        	
				        	            dialog.dismiss();
				        	        }
				        	    });
				        	    alert.show();		      	        	
		        	        	
		        	            dialog.dismiss();
		        	        }
		        	    });
		        	    alert.show();
		        }
		    }
		    else{
		    	if(item.getItemId() == R.id.item1){	        	
		    		List<String> kat = xml.readApp(context, value);
		        	final CharSequence[] choise = kat.toArray(new CharSequence[kat.size()]);	        	
		        
		        	    AlertDialog.Builder alert = new AlertDialog.Builder(this);
		        	    alert.setTitle("Aplik�cia:");
		        	    alert.setSingleChoiceItems(choise,-1, new DialogInterface.OnClickListener()
		        	    {
		        	        @Override
		        	        public void onClick(DialogInterface dialog, int which) 
		        	        {
		        	        	 final xmlFile xml = new xmlFile();	
		        	        	try {
	        						xml.delXMLTag(choise[which].toString(), "delApp", null,context);					
	        						 ListView listView=(ListView)findViewById(R.id.listViewZoznamApp);
	        						 if(value.compareTo("ostatn�")!=0)
	        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readApp(context,value)));	
	        						 else
	        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readAppOther(context)));	
	        					} catch (IllegalArgumentException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					} catch (IllegalStateException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					} catch (IOException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					}
		        	          
		        	            dialog.dismiss();
		        	        }
		        	    });
		        	    alert.show();
		        }
		    	if(item.getItemId() == R.id.item2){	        	
		    		
		    		boolean write = false;
		    		List<String> katWL = xml.readWL(context, value);		        			    		
		        	final CharSequence[] choiseALL = kat;	
		        	List<String> katEdit = new ArrayList<String>();
		        	for(int i =0;i<choiseALL.length;i++){
		        		write = true;
		        		for(int j =0;j<katWL.size();j++){
			        		if(choiseALL[i].toString().substring(19).compareTo(katWL.get(j).toString())==0){	
			        			write = false;
			        			break;
			        		}		        		
			        	}
		        		if(write)
		        			katEdit.add(choiseALL[i].toString().substring(19));	
		        	}
		        	final CharSequence[] choise = katEdit.toArray(new CharSequence[katEdit.size()]);	
		        	    AlertDialog.Builder alert = new AlertDialog.Builder(this);
		        	    alert.setTitle("Povolenie:");
		        	    alert.setSingleChoiceItems(choise,-1, new DialogInterface.OnClickListener()
		        	    {
		        	        @Override
		        	        public void onClick(DialogInterface dialog, int which) 
		        	        {
		        	        	 final xmlFile xml = new xmlFile();	
		        	        	try {
		        	        		 xml.addXMLTag("android.permission."+choise[which].toString(), "AddWL", value,context);					
	        						 ListView listView=(ListView)findViewById(R.id.listViewZoznamApp);
	        						
	        						 if(value.compareTo("ostatn�")!=0)
	        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readApp(context,value)));	
	        						 else
	        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readAppOther(context)));	
	        					} catch (IllegalArgumentException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					} catch (IllegalStateException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					} catch (IOException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					}
		        	          
		        	            dialog.dismiss();
		        	        }
		        	    });
		        	    alert.show();
		        }
		    	if(item.getItemId() == R.id.item4){	        	
		    		
		    		boolean write = false;
		    		List<String> katWL = xml.readBL(context, value);		        			    		
		        	final CharSequence[] choiseALL = kat;	
		        	List<String> katEdit = new ArrayList<String>();
		        	for(int i =0;i<choiseALL.length;i++){
		        		write = true;
		        		for(int j =0;j<katWL.size();j++){
			        		if(choiseALL[i].toString().substring(19).compareTo(katWL.get(j).toString())==0){	
			        			write = false;
			        			break;
			        		}		        		
			        	}
		        		if(write)
		        			katEdit.add(choiseALL[i].toString().substring(19));		
		        	}
		        	final CharSequence[] choise = katEdit.toArray(new CharSequence[katEdit.size()]);	
		        	    AlertDialog.Builder alert = new AlertDialog.Builder(this);
		        	    alert.setTitle("Povolenie:");
		        	    alert.setSingleChoiceItems(choise,-1, new DialogInterface.OnClickListener()
		        	    {
		        	        @Override
		        	        public void onClick(DialogInterface dialog, int which) 
		        	        {
		        	        	 final xmlFile xml = new xmlFile();	
		        	        	try {
		        	        		 xml.addXMLTag("android.permission."+choise[which].toString(), "AddBL", value,context);					
	        						 ListView listView=(ListView)findViewById(R.id.listViewZoznamApp);
	        						
	        						 if(value.compareTo("ostatn�")!=0)
	        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readApp(context,value)));	
	        						 else
	        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readAppOther(context)));	
	        					} catch (IllegalArgumentException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					} catch (IllegalStateException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					} catch (IOException e) {
	        						// TODO Auto-generated catch block
	        						e.printStackTrace();
	        					}
		        	          
		        	            dialog.dismiss();
		        	        }
		        	    });
		        	    alert.show();
		        }
		    }
		    if(item.getItemId() == R.id.item3){	        	
		    	List<String> kat = xml.readWL(context, value);		    	
	        	final CharSequence[] choise = kat.toArray(new CharSequence[kat.size()]);	         	
	        
	        	    AlertDialog.Builder alert = new AlertDialog.Builder(this);
	        	    alert.setTitle("Povolenie:");
	        	    alert.setSingleChoiceItems(choise,-1, new DialogInterface.OnClickListener()
	        	    {
	        	        @Override
	        	        public void onClick(DialogInterface dialog, int which) 
	        	        {
	        	        	 final xmlFile xml = new xmlFile();	
	        	        	try {
	        	        		 xml.delXMLTag("android.permission."+choise[which].toString(), "delWL", null,context);					
        						 ListView listView=(ListView)findViewById(R.id.listViewZoznamApp);
        						
        						 if(value.compareTo("ostatn�")!=0)
        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readApp(context,value)));	
        						 else
        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readAppOther(context)));	
        					} catch (IllegalArgumentException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					} catch (IllegalStateException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					} catch (IOException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
	        	          
	        	            dialog.dismiss();
	        	        }
	        	    });
	        	    alert.show();
	        }
		    if(item.getItemId() == R.id.item5){	        	
	    		
		    	List<String> kat = xml.readBL(context, value);
	        	final CharSequence[] choise = kat.toArray(new CharSequence[kat.size()]);	        	
	        
	        	    AlertDialog.Builder alert = new AlertDialog.Builder(this);
	        	    alert.setTitle("Povolenie:");
	        	    alert.setSingleChoiceItems(choise,-1, new DialogInterface.OnClickListener()
	        	    {
	        	        @Override
	        	        public void onClick(DialogInterface dialog, int which) 
	        	        {
	        	        	 final xmlFile xml = new xmlFile();	
	        	        	try {
	        	        		xml.delXMLTag("android.permission."+choise[which].toString(), "delBL", null,context);							
        						 ListView listView=(ListView)findViewById(R.id.listViewZoznamApp);
        						
        						 if(value.compareTo("ostatn�")!=0)
        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readApp(context,value)));	
        						 else
        							 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group,  xml.readAppOther(context)));	
        					} catch (IllegalArgumentException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					} catch (IllegalStateException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					} catch (IOException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
	        	          
	        	            dialog.dismiss();
	        	        }
	        	    });
	        	    alert.show();
	        }
		    
	        
	 
	        return super.onOptionsItemSelected(item);
	    }

}
