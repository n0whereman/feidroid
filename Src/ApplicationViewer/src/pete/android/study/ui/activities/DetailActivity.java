package pete.android.study.ui.activities;

import pete.android.study.R;
import pete.android.study.R.id;
import pete.android.study.R.layout;
import pete.android.study.R.menu;
import pete.android.study.ui.fragments.DetailFragment;
import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
/**
 * Activity for DetailFragment
 * @author Peter Muska
 *
 */
public class DetailActivity extends FragmentActivity {

	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		if(savedInstanceState == null){
			
			bundle = null;
			Intent i = getIntent();
			if(i != null)
				bundle = i.getExtras();
			
			DetailFragment fragment = DetailFragment.newInstance(bundle);
			
			getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container, fragment, DetailFragment.TAG)
				.commit();
		}
	}
	
}
