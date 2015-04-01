package pete.android.study.ui.fragments;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pete.android.study.R;
import pete.android.study.adapters.AppInfoAdapter;
import pete.android.study.adapters.PermisionsAdapter;
import pete.android.study.ui.activities.AnalysisDetailActivity;
import pete.android.study.utils.RetrieveData;
import pete.android.study.utils.Utilities;
import android.Manifest.permission;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * In this fragment is calculate risk of application 
 * @author Peter Muska
 *
 */
public class AnalysisFragment extends Fragment{

	public static final String PERM = "perm";
	public static final String CATEGORY = "category";
	
	private String[] permissions;
	private String category;
	private String[] green_perm;
	private String[] yellow_perm;
	private String[] orange_perm;
	private String[] red_perm;
	
	private String[] game_perm;
	private String[] communication_perm;
	private String[] entertainment_perm;
	private String[] finance_perm;
	private String[] health_perm;
	private String[] lifestyle_perm;
	private String[] multimedia_perm;
	private String[] productivity_perm;
	private String[] shopping_perm;
	private String[] social_perm;
	private String[] sports_perm;
	private String[] themes_perm;
	private String[] tools_perm;
	private String[] travel_perm;

	
	private enum Ctgr{
		Games, Communication, Entertainment, Finance, Health, 
		Lifestyle, Multimedia, Productivity, Shopping, Social, 
		Sports, Themes, Tools, Travel;
	};
	
	TextView mResultScore;
	TextView mUnsafePerm;
	
	Button btnThreadLvl1, btnThreadLvl2, btnThreadLvl3, btnThreadLvl4, btnThreadLvl5;
//	
	private ProgressBar mProgress;
	private int mProgressStatus;
	private long app_id;
	private float riskScore = 0.0f;
//
	ExpandableListView mListPermisions;
	
	/**
	 * Creating of new fragment object
	 */
	public static AnalysisFragment newInstance(Bundle args){
		AnalysisFragment fragment = new AnalysisFragment();
		fragment.setArguments(args);
		return fragment; 
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle arg = getArguments();
		
		// arguments
		permissions = arg.getStringArray(PERM);
		category = arg.getString(CATEGORY);
		app_id = arg.getLong("APP_ID");
		
		// permissions by color from xml files
		green_perm = getActivity().getResources().getStringArray(R.array.green_permissions);
		yellow_perm = getActivity().getResources().getStringArray(R.array.yellow_permissions);
		orange_perm = getActivity().getResources().getStringArray(R.array.orange_permissions);
		red_perm = getActivity().getResources().getStringArray(R.array.red_permissions);
		
		// unsafe permissions by category from xml files
		game_perm = getActivity().getResources().getStringArray(R.array.games_permissions);
		communication_perm = getActivity().getResources().getStringArray(R.array.communication_permissions);
		entertainment_perm = getActivity().getResources().getStringArray(R.array.entertainment_permissions);
		finance_perm = getActivity().getResources().getStringArray(R.array.finance_permissions);
		health_perm = getActivity().getResources().getStringArray(R.array.health_permissions);
		lifestyle_perm = getActivity().getResources().getStringArray(R.array.lifestyle_permissions);
		multimedia_perm = getActivity().getResources().getStringArray(R.array.multimedia_permissions);
		productivity_perm = getActivity().getResources().getStringArray(R.array.productivity_permissions);
		shopping_perm = getActivity().getResources().getStringArray(R.array.shopping_permissions);
		social_perm = getActivity().getResources().getStringArray(R.array.social_permissions);
		sports_perm = getActivity().getResources().getStringArray(R.array.sports_permissions);
		themes_perm = getActivity().getResources().getStringArray(R.array.themes_permissions);
		tools_perm = getActivity().getResources().getStringArray(R.array.tools_permissions);
		travel_perm = getActivity().getResources().getStringArray(R.array.travel_permissions);
		
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
	}
/**
 * View function of Analysis Activity
 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_analysis2, container, false);
		
		//ButterKnife.inject(this, view);
		
		//mcategory.setText(category);
		
		//initBtns
		
		btnThreadLvl1 = (Button) view.findViewById(R.id.btnThreadLvl1);
		btnThreadLvl2 = (Button) view.findViewById(R.id.btnThreadLvl2);
		btnThreadLvl3 = (Button) view.findViewById(R.id.btnThreadLvl3);
		btnThreadLvl4 = (Button) view.findViewById(R.id.btnThreadLvl4);
		btnThreadLvl5 = (Button) view.findViewById(R.id.btnThreadLvl5);
		
		ThreatButtonListenter listener = new ThreatButtonListenter();
		btnThreadLvl1.setOnClickListener(listener);
		btnThreadLvl1.setAlpha(0.2f);
		btnThreadLvl2.setOnClickListener(listener);
		btnThreadLvl2.setAlpha(0.2f);
		btnThreadLvl3.setOnClickListener(listener);
		btnThreadLvl3.setAlpha(0.2f);
		btnThreadLvl4.setOnClickListener(listener);
		btnThreadLvl4.setAlpha(0.2f);
		btnThreadLvl5.setOnClickListener(listener);
		btnThreadLvl5.setAlpha(0.2f);
		//btnThreadLvl5.setBackgroundColor(Color.RED);
		
		//btnThreadLvl1.setAlpha(0.2f);
		
		float score1 = 0;
		float score2 = 0;
		float total_score = 0;
		
		String result;
		try
		{
			//result = new RetrieveData().execute("http://thanos.feidroid.mobi:8080/FEIDroid-0.0.1-SNAPSHOT/api/application").get();
			if(app_id == -1) app_id = 1;
			result = new RetrieveData(getActivity()).execute("http://thanos.feidroid.mobi:8080/FEIDroid/api/application/" + app_id + "/analyze").get();
			//TODO check for null			
			JSONObject myJson = new JSONObject(result);
			// use myJson as needed, for example 
			result = myJson.optString("score");
			
			BigDecimal bd = new BigDecimal(result);
		    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		    riskScore = bd.floatValue();
		    
		    //mResultScore = (TextView)view.findViewById(R.id.result_score);
		    //mResultScore.setText(bd.toEngineeringString()+" %");
			
			//mProgress = (ProgressBar) view.findViewById(R.id.progress_bar);
			//mProgress.setProgress((int)total_score);
		    
			return view;
		}
		catch(JSONException e1)
		{
			result = e1.getMessage();
		}
		catch(ExecutionException e2)
		{
			result = e2.getMessage();
		}
		catch(InterruptedException e3)
		{
			result = e3.getMessage();
		}
		
		
		//permissions
		
		//Ctgr ctgr = Ctgr.valueOf(category);
		Random r = new Random();
		int c = r.nextInt(Ctgr.values().length - 1);
		Ctgr ctgr = Ctgr.values()[c];
		
		if(permissions != null)
		{			
			int poc = permissions.length;
			// vseobecne - score podla farebnych skupin
			for(String p : permissions) 
			{		
				//green
				for(String s: green_perm)
				{
					int i = s.indexOf(p);
					if(i>=0)
					{
						score1+=0.5;
					}
				}
				// yellow
				for(String s: yellow_perm)
				{
					int i = s.indexOf(p);
					if(i>=0)
					{
						score1+=1;
					}
				}
				//orange
				for(String s: orange_perm)
				{
					int i = s.indexOf(p);
					if(i>=0)
					{
						score1+=2;
					}
				}
				//red
				for(String s: red_perm)
				{
					int i = s.indexOf(p);
					if(i>=0)
					{
						score1+=5;
					}
				}		
			}
			//score = (score/(poc*4))*100;
			
			// score podla konkretnej kategorie
			
			StringBuilder builder = new StringBuilder();
			builder.append("\n");

			switch(ctgr)
			{
			case Games:
				for(String p : permissions) 
				{
					for(String s: game_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=4.25;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
							
				break;
				
			case Communication:
				for(String p : permissions) 
				{
					for(String s: communication_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=2.4;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
							
				break;
				
			case Entertainment:
				for(String p : permissions) 
				{
					for(String s: entertainment_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=5.5;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Finance:
				for(String p : permissions) 
				{
					for(String s: finance_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=3.25;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Health:
				for(String p : permissions) 
				{
					for(String s: health_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=3.75;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Lifestyle:
				for(String p : permissions) 
				{
					for(String s: lifestyle_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=1.75;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Multimedia:
				for(String p : permissions) 
				{
					for(String s: multimedia_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=2;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Productivity:
				for(String p : permissions) 
				{
					for(String s: productivity_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=2.75;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Shopping:
				for(String p : permissions) 
				{
					for(String s: shopping_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=3.5;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Social:
				for(String p : permissions) 
				{
					for(String s: social_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=1.5;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Sports:
				for(String p : permissions) 
				{
					for(String s: sports_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=3;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Themes:
				for(String p : permissions) 
				{
					for(String s: themes_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=4;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Tools:
				for(String p : permissions) 
				{
					for(String s: tools_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=1;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			case Travel:
				for(String p : permissions) 
				{
					for(String s: travel_perm)
					{	
						int i = s.indexOf(p);
						if(i>=0)
						{
							builder.append(p).append("\n");
							score2+=3;
						}
					}
				}
				//mUnsafePerm = (TextView)view.findViewById(R.id.suspicious_permissions);
				//mUnsafePerm.setText(builder.toString());
				break;
				
			default:
				break;
				
			}
			
			score1 = (score1/(poc*5))*100;
			score2 = (score2/(poc*5))*100;
			
			total_score = (score1 + score2);
		
		
		//tu bola }
		
			BigDecimal bd = new BigDecimal(Float.toString(total_score));
	    	bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

			PermisionsAdapter adapter = new PermisionsAdapter(getActivity(),
														permissions,
														green_perm,
														yellow_perm,
														orange_perm,
														red_perm);
		
			//mListPermisions = (ExpandableListView)view.findViewById(R.id.list_permisions);
			//mResultScore = (TextView)view.findViewById(R.id.result_score);
		
			//mListPermisions.setAdapter(adapter);
		
			////mResultScore.setText(Float.toString(score));
			//mResultScore.setText(bd.toEngineeringString()+" %");
		
			//mProgress = (ProgressBar) view.findViewById(R.id.progress_bar);
			//mProgress.setProgress((int)total_score);
		}
		else
		{
			total_score = 0;
			BigDecimal bd = new BigDecimal(Float.toString(total_score));
		    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		    riskScore = bd.floatValue();
		    //mResultScore = (TextView)view.findViewById(R.id.result_score);
		    //mResultScore.setText(bd.toEngineeringString()+" %");
			
			//mProgress = (ProgressBar) view.findViewById(R.id.progress_bar);
			//mProgress.setProgress((int)total_score);
	
		}
		
		int threadLvl = (int)(riskScore/20) + 1;
		threadLvl = threadLvl == 0 ? 2 : threadLvl;
	    switch(threadLvl)
	    {
		    case 1:
		    	btnThreadLvl1.setAlpha(1.0f);
		    	break;
		    case 2:
		    	btnThreadLvl2.setAlpha(1.0f);
		    	break;
		    case 3:
		    	btnThreadLvl3.setAlpha(1.0f);
		    	break;
		    case 4:
		    	btnThreadLvl4.setAlpha(1.0f);
		    	break;
		    case 5:
		    	btnThreadLvl5.setAlpha(1.0f);
		    	break;
	    }
		
		return view;
	}

	private class ThreatButtonListenter implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), AnalysisDetailActivity.class);
			intent.putExtra("riskScore", riskScore);
			intent.putExtra("clickedBtnId", v.getId());
			startActivity(intent);
		}
	}
}
