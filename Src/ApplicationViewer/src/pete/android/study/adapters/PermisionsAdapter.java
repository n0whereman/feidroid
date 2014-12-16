package pete.android.study.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.ButterKnife;

import models.PermisionListObject;

import pete.android.study.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
/**
 * Adapter for Application permissions
 * @author Peter Muska
 *
 */
public class PermisionsAdapter extends BaseExpandableListAdapter {
	
	List<PermisionListObject> mPermisions;
	List<String> mAppPermisions;
	Context mContext;
	//String[] mAppPermisions;
	
	/**
	 * Create permission adapter
	 * @param appPerms - permissions of selected app
	 * @param greenPerms - list of permissions from green group
	 * @param yellowPerms - list of permissions from yellow group
	 * @param orangePerms - list of permissions from orange group
	 * @param redPerms - list of permissions from red group
	 */
	public PermisionsAdapter(Context context, String[] appPerms
							, String[] greenPerms, String[] yellowPerms
							, String[] orangePerms, String[] redPerms){
		
		mPermisions = new ArrayList<PermisionListObject>();
		
		if(appPerms == null)
			return;
		
		mAppPermisions = new ArrayList<String>();
		for(String perm : appPerms)
			mAppPermisions.add(perm);
		
		final String[] greenPermisions = greenPerms;
		final String[] yellowPermisions = yellowPerms;
		final String[] orangePermisions = orangePerms;
		final String[] redPermisions = redPerms;
		
		final String[] greenDescriptions = context.getResources().getStringArray(R.array.green_descriptions);
		final String[] yellowDescriptions = context.getResources().getStringArray(R.array.yellow_descriptions);
		final String[] orangeDescriptions = context.getResources().getStringArray(R.array.orange_descriptions);
		final String[] redDescriptions = context.getResources().getStringArray(R.array.red_descriptions);
		
		addPermisions(context.getResources().getColor(R.color.perm_green), greenPermisions, greenDescriptions);
		addPermisions(context.getResources().getColor(R.color.perm_yellow), yellowPermisions, yellowDescriptions);
		addPermisions(context.getResources().getColor(R.color.perm_orange), orangePermisions, orangeDescriptions);
		addPermisions(context.getResources().getColor(R.color.perm_red), redPermisions, redDescriptions);
		
		mContext = context;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mPermisions.get(groupPosition).getPermisionDescription();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		if(convertView == null) 
			view = LayoutInflater.from(mContext).inflate(R.layout.list_item_permision, null);
		
		TextView descriptionView = ButterKnife.findById(view, R.id.permision_detail);
		descriptionView.setText(mPermisions.get(groupPosition).getPermisionDescription());
		
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mPermisions.get(groupPosition).getPermisionName();
	}

	@Override
	public int getGroupCount() {
		return mPermisions.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		if(convertView == null)
			view = LayoutInflater.from(mContext).inflate(R.layout.list_item_parent_permision, null);
		
		PermisionListObject permObj = mPermisions.get(groupPosition);
		
		TextView nameView = ButterKnife.findById(view, R.id.permision_name);
		nameView.setText(permObj.getPermisionName());
		
		setColorToText(permObj.getColor(), nameView);
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/**
	 * Add permission to List
	 * @param color - color of permission
	 * @param permNames - name of permission
	 * @param permDescriptions - description of permission
	 */
	private void addPermisions(int color, String[] permNames, String[] permDescriptions){
		List<Integer> itemsToRemove = new ArrayList<Integer>();
		int j = 0;
		for(String appPerm : mAppPermisions) {
			for(int i = 0; i < permNames.length; i++){
				if(appPerm.equals(permNames[i])){
					mPermisions.add(new PermisionListObject(color, permNames[i], permDescriptions[i]));
					itemsToRemove.add(j);
					break;
				}
			}
			j++;
		}
		
		for(Integer i : itemsToRemove)
			mAppPermisions.remove(i);
	}

	/**
	 * Set color of permission
	 */
	private void setColorToText(int color, TextView textView){
		textView.setTextColor(color);
	}
}
