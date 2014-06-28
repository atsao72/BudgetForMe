package com.rook.budgetforme;

import java.io.BufferedReader;
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
		TextView tv = (TextView) rootView.findViewById(R.id.text);
		//new LoadData().execute("housing.txt");

		FileInputStream fis = null;
		String collected = "";
		try {
			FileOutputStream fos = new FileOutputStream(MainActivity.housing, true);
			//fos.write();
			fos.close();
			fis = new FileInputStream(MainActivity.housing);
			byte[] dataArray = new byte[fis.available()];
			while(fis.read(dataArray)!= -1){
				collected = new String(dataArray);
			}
			tv.setText(collected);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
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
}
