package com.rook.budgetforme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = null;
		switch(getArguments().getInt(ARG_SECTION_NUMBER)){
			case 2:
				rootView = inflater.inflate(R.layout.fragment_input, container, false);
				TextView tv2 = (TextView) rootView.findViewById(R.id.section_label);
				tv2.setText("Income");
				break;
			case 3:
				rootView = inflater.inflate(R.layout.fragment_input, container, false);
				TextView tv3 = (TextView) rootView.findViewById(R.id.section_label);
				tv3.setText("Expense");
				break;
		}
		return rootView;
	}
}