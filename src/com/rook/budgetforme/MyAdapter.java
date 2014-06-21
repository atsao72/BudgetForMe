package com.rook.budgetforme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter{
private Context context;
String[] parentList = {"Categories"};
String[] childList = {"Housing", "Food", "Transportation", "Entertainment", "Savings", "Other"}; 
	public MyAdapter(MainFragment context) {
		// TODO Auto-generated constructor stub
		this.context = context.getActivity();
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		// TODO Auto-generated method stub
		TextView text = new TextView(context);
		for(int i=0;i<childList.length;i++){
			text = new TextView(context);
			text.setText(childList[i]);
		}
		text.setPadding(70, 0, 0, 0);
		return text;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return childList.length;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return parentList.length;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		TextView text = new TextView(context);
		text.setText(parentList[arg0]);
		text.setPadding(50, 0, 0, 0);
		return text;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}


}
