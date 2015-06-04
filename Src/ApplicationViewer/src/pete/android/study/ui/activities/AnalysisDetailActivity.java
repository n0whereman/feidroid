package pete.android.study.ui.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import pete.android.study.R;
import pete.android.study.adapters.PermisionsAdapter;
import pete.android.study.ui.fragments.AnalysisFragment;
import pete.android.study.utils.RetrieveData;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class AnalysisDetailActivity extends Activity {
    
	public static final String APP_INFO = "app_info";
	
	private String[] green_perm;
	private String[] yellow_perm;
	private String[] orange_perm;
	private String[] red_perm;
	private String[] permissions;	
	
	private int appId;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout for the main screen
        setContentView(R.layout.analysis_detail);    
		
		// arguments
        Intent intent = getIntent();
        
        float score = intent.getFloatExtra("riskScore", -1.0f);
        int scoreLvl = (int)(score / 20) + 1;
        if(scoreLvl > 5) scoreLvl = 5;
        
        int btnId = intent.getIntExtra("clickedBtnId", -1);
        appId = intent.getIntExtra("appId", 1);
		permissions = intent.getStringArrayExtra(AnalysisFragment.PERM);
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
		String result = "", r = "";
		try
		{
			result = new RetrieveData(context).execute("https://thanos.feidroid.mobi:8443/FEIDroid/api/application/" + appId + "/analyze").get();
			//TODO check for null			
			JSONObject myJson = new JSONObject(result);
			// use myJson as needed, for example 
			result = myJson.optString("descriptions");

			JSONArray a = new JSONArray(result);
			r = a.get(0).toString();
		}
		catch(Exception e)
		{
			int x = 3;
		}
				
		if(scoreLvl == btnLvl)
		{
			if(result == "" || r == "")
			{
				txt.setText("LVL" + btnLvl + " threat descritption\n\nList of presmissions:");
			}
			else
			{
				//r = "High similarity to a malware";
				txt.setText(r + "\n\nList of presmissions:");
			}
			
			green_perm = context.getResources().getStringArray(R.array.green_permissions);
			yellow_perm = context.getResources().getStringArray(R.array.yellow_permissions);
			orange_perm = context.getResources().getStringArray(R.array.orange_permissions);
			red_perm = context.getResources().getStringArray(R.array.red_permissions);

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
			txt.setText("LVL" + btnLvl + " threat descritption\n");
		}
	}
}

