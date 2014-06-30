package com.rook.budgetforme;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class OverviewFragment extends Fragment implements OnItemSelectedListener {
	private String[] categories = {"Select a category", "Housing", "Food", "Transportation", "Entertainment", "Savings", "Other"};
	Spinner spinner;
	TextView tv;
	String myData;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories);
		spinner = (Spinner) rootView.findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(this);
		spinner.setAdapter(adapter);
		tv = (TextView) rootView.findViewById(R.id.text);
		//new LoadData().execute("housing.txt");


		return rootView;
	}
/*	
public class LoadData extends AsyncTask<String, Integer, String> {

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
}*/
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		myData = "";
		switch(spinner.getSelectedItemPosition()){
		case 0:
			break;
		case 1:
			try {
			    FileInputStream fis = new FileInputStream(MainActivity.housingFile);
			    DataInputStream in = new DataInputStream(fis);
			    BufferedReader br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    while ((strLine = br.readLine()) != null) {
			    myData = myData + strLine + '\n';
			    }
			    in.close();
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
			   tv.setText(myData);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
