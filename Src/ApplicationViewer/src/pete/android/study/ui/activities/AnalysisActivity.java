package pete.android.study.ui.activities;

import pete.android.study.R;
import pete.android.study.R.id;
import pete.android.study.R.layout;
import pete.android.study.R.menu;
import pete.android.study.ui.fragments.AnalysisFragment;
import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;
/**
 * Activity for AnalysisFragment
 * @author Peter Muska
 *
 */
public class AnalysisActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analysis);

		if (savedInstanceState == null) {
			
			Bundle bundle = getIntent().getExtras();
			if(bundle != null){
				AnalysisFragment jablko = AnalysisFragment.newInstance(bundle);
				getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, jablko).commit();
			}
		}
		
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.analysis, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		final int id = item.getItemId();
		
		if(id == android.R.id.home){
			super.onBackPressed();
		}
		
		return super.onOptionsItemSelected(item);
	}

}
