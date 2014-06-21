package com.rook.budgetforme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	private String[] categories = {"Housing", "Food", "Transportation", "Entertainment", "Savings", "Other"}; 

	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_input, container, false);
		TextView tv = (TextView) rootView.findViewById(R.id.section_label);
		Button bSave = (Button) rootView.findViewById(R.id.saveButton);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories) ;
		Spinner list = (Spinner) rootView.findViewById(R.id.categorySpinner); 
		switch(getArguments().getInt(ARG_SECTION_NUMBER)){
			case 2:
				tv.setText("Income");
				list.setVisibility(8);
				break;
			case 3:
				tv.setText("Expense");
				
				list.setAdapter(adapter);
				break;
		}
		return rootView;
	}
}