package pete.android.study.ui.activities;

//import your.application.packagename.R;
import pete.android.study.R;
import pete.android.study.adapters.AppInfoAdapter;
import pete.android.study.utils.Utilities;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
/**
 * Main Activity 
 * @author Peter Muska
 *
 */
public class MainActivity extends Activity {
    
	private ListView mListAppInfo;
	/**
	 * This function create an Adapter List and load list application
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout for the main screen
        setContentView(R.layout.activity_main);        
        
        //load list application
        mListAppInfo = (ListView)findViewById(R.id.lvApps);
        
        // create new adapter
        AppInfoAdapter adapter = new AppInfoAdapter(this, Utilities.getInstalledApplication(this), getPackageManager());
       
        // set adapter to list view
        mListAppInfo.setAdapter(adapter);
        
        
        // implement event when an item on list view is selected  
//        mListAppInfo.setOnItemClickListener(new OnItemClickListener() {
//        	@Override
//        	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//        		// get the list adapter
//        		AppInfoAdapter appInfoAdapter = (AppInfoAdapter)parent.getAdapter();
//        		// get selected item on the list
//        		ApplicationInfo appInfo = (ApplicationInfo)appInfoAdapter.getItem(pos);
//        		// launch the selected application
//        		Utilities.launchApp(parent.getContext(), getPackageManager(), appInfo.packageName);
//        	}
//		});
    }
}