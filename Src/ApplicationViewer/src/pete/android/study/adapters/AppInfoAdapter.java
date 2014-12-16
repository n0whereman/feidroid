package pete.android.study.adapters;

import java.util.List;

import pete.android.study.R;
import pete.android.study.R.id;
import pete.android.study.R.layout;
import pete.android.study.ui.activities.DetailActivity;
import pete.android.study.ui.fragments.DetailFragment;
import pete.android.study.utils.Utilities;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * This Activity - AppInfoAdapter is an adapter for list of applications
 * @author Peter Muska
 *
 */
public class AppInfoAdapter extends BaseAdapter {
	private Context mContext;
	private List<PackageInfo> mListAppInfo;
	private PackageManager mPackManager;
	
	public AppInfoAdapter(Context c, List<PackageInfo> list, PackageManager pm) {
		mContext = c;
		mListAppInfo = list;
		mPackManager = pm;
	}

	@Override
	public int getCount() {
		return mListAppInfo.size();
	}

	@Override
	public Object getItem(int position) {
		return mListAppInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	/**
	 * View function of application list
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// get the selected entry
		ApplicationInfo entry = mListAppInfo.get(position).applicationInfo;
		
		// reference to convertView
		View v = convertView;
		
		// inflate new layout if null
		if(v == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			v = inflater.inflate(R.layout.list_item_app, null);
		}
		
		// load controls from layout resources
		ImageView ivAppIcon = (ImageView)v.findViewById(R.id.ivIcon);
		TextView tvAppName = (TextView)v.findViewById(R.id.tvName);
		//mTextView tvPkgName = (TextView)v.findViewById(R.id.tvPack);
		
		// buttons
		Button tvActionOpen = (Button)v.findViewById(R.id.tvActionOpen);
		Button tvActionDetail = (Button)v.findViewById(R.id.tvActionDetail);
		
		/**
		 * Action for Launch Button
		 */
		tvActionOpen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utilities.launchApp(mContext, mPackManager, mListAppInfo.get(position).packageName);
			}
		});
		
		/**
		 *  Action for Detail Button
		 */
		tvActionDetail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, DetailActivity.class);
				intent.putExtra(DetailFragment.APP_INFO, mListAppInfo.get(position));
				mContext.startActivity(intent);
			}
		});
		
		// set data to display
		ivAppIcon.setImageDrawable(entry.loadIcon(mPackManager));
		tvAppName.setText(entry.loadLabel(mPackManager));
		//tvPkgName.setText(entry.packageName);
		
		// return view
		return v;
	}

	
	

}
