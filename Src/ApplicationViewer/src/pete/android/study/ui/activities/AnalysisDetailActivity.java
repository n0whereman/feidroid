package pete.android.study.ui.activities;

import pete.android.study.R;
import pete.android.study.adapters.PermisionsAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class AnalysisDetailActivity extends Activity {
    
	private String[] green_perm;
	private String[] yellow_perm;
	private String[] orange_perm;
	private String[] red_perm;
	private String[] permissions;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout for the main screen
        setContentView(R.layout.analysis_detail);    
        Intent intent = getIntent();
        
        float score = intent.getFloatExtra("riskScore", -1.0f);
        int scoreLvl = (int)(score / 20) + 1;
        int btnId = intent.getIntExtra("clickedBtnId", -1);
        TextView txt = (TextView)findViewById(R.id.textView111);
        
        // permissions by color from xml files
        green_perm = getApplicationContext().getResources().getStringArray(R.array.green_permissions);
     	yellow_perm = getApplicationContext().getResources().getStringArray(R.array.yellow_permissions);
     	orange_perm = getApplicationContext().getResources().getStringArray(R.array.orange_permissions);
     	red_perm = getApplicationContext().getResources().getStringArray(R.array.red_permissions);
        
        switch(btnId)
        {
	        case R.id.btnThreadLvl1:
	        	SetAppropiateContent(scoreLvl, 1, getApplicationContext());
	        	break;
	        case R.id.btnThreadLvl2:
	        	SetAppropiateContent(scoreLvl, 2, getApplicationContext());
	        	break;
	        case R.id.btnThreadLvl3:
	        	SetAppropiateContent(scoreLvl, 3, getApplicationContext());
	        	break;
	        case R.id.btnThreadLvl4:
	        	SetAppropiateContent(scoreLvl, 4, getApplicationContext());
	        	break;
	        case R.id.btnThreadLvl5:
	        	SetAppropiateContent(scoreLvl, 5, getApplicationContext());
	        	break;
	    	default:
	    		txt.setText("An error occured");
	    		break;
        }        
    }
	
	private void SetAppropiateContent(int scoreLvl, int btnLvl, Context context)
	{
		TextView txt = (TextView)findViewById(R.id.textView111);
		if(scoreLvl == btnLvl)
		{
			txt.setText("LVL" + btnLvl + " thread descritption\n List of presmissions:");		
			try 
			{
				PackageManager pm = getPackageManager();
				PackageInfo packageInfo = pm.getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_PERMISSIONS);
				//Get Permissions
				permissions = packageInfo.requestedPermissions;
			} 
			catch (NameNotFoundException e) {
			    e.printStackTrace();
			} 			

			PermisionsAdapter adapter = new PermisionsAdapter(context,
					permissions,
					green_perm,
					yellow_perm,
					orange_perm,
					red_perm);

			ExpandableListView mListPermisions = (ExpandableListView)findViewById(R.id.list_permisions);
			mListPermisions.setVisibility(View.VISIBLE);
			mListPermisions.setAdapter(adapter);
		}
		else
		{	 			
			txt.setText("LVL" + btnLvl + " thread descritption");		
		}
	}
}

