package com.rook.budgetforme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OverviewFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */

	public OverviewFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
		TextView tv1 = (TextView) rootView.findViewById(R.id.text);
		tv1.setText("Budget");

		return rootView;
	}
}
