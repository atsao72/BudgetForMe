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

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

public class OverviewFragment extends Fragment implements OnItemSelectedListener {
	private String[] categories = {"Select a category", "Income", "Housing", "Food", "Transportation", "Entertainment", "Savings", "Other"};
	Spinner spinner;
	TextView tvValues, tvTotal;
	String myData;
	String collected = ""; 
	private CategorySeries mSeries = new CategorySeries("");	  
	private DefaultRenderer mRenderer = new DefaultRenderer();	  
	private GraphicalView mChartView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories);
		spinner = (Spinner) rootView.findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(this);
		spinner.setAdapter(adapter);
		tvValues = (TextView) rootView.findViewById(R.id.text);
		tvTotal = (TextView) rootView.findViewById(R.id.totalText);
		//new LoadData().execute("housing.txt");
		
		mRenderer.setApplyBackgroundColor(true);  
		//mRenderer.setBackgroundColor(Color.WHITE);  
		//mRenderer.setChartTitleTextSize(20);  
		mRenderer.setLabelsTextSize(15);  
		mRenderer.setLegendTextSize(15);  
		mRenderer.setMargins(new int[] {20, 30, 15, 0 });  
		//mRenderer.setZoomButtonsVisible(true);  
		//mRenderer.setStartAngle(90);  
		  
		for (int i = 1; i < MainActivity.totalValues.length; i++) {  
			mSeries.add(MainActivity.names[i] + " " + MainActivity.totalValues[i], MainActivity.totalValues[i]);  
			SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();  
			renderer.setColor(MainActivity.COLORS[(mSeries.getItemCount() - 1) % MainActivity.COLORS.length]);  
			mRenderer.addSeriesRenderer(renderer);
		}    
		if (mChartView != null) {  
			mChartView.repaint();
		}
		
		return rootView;
	}
	
	@Override
	public void onResume() {  
		super.onResume();  
		if (mChartView == null) {  
				LinearLayout layout = (LinearLayout) this.getActivity().findViewById(R.id.chart);  
				mChartView = ChartFactory.getPieChartView(this.getActivity(), mSeries, mRenderer);  
				mRenderer.setClickEnabled(true);  
				mRenderer.setSelectableBuffer(10);
				layout.addView(mChartView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));  
		}  
		else {  
				mChartView.repaint();  
		}  
	}  
	
private class LoadData extends AsyncTask<Integer, Integer, String> {
	
	@Override
	protected String doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		myData = "";
		collected = "";
		String[] strArray = null;
		int position = spinner.getSelectedItemPosition();
		if (position!=0){
			try {
			    FileInputStream fis = new FileInputStream(MainActivity.files[position-1]);
			    DataInputStream in = new DataInputStream(fis);
			    BufferedReader br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    while ((strLine = br.readLine()) != null) {
			    	myData = myData + strLine;
			    }
			    strArray = myData.split("&");
			    in.close();
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
				for(int i=0; i<strArray.length;i++){
					collected = collected + strArray[i] + '\n';
				}
				return collected;
		}
		return null;
	}
	protected void onPostExecute(String result){
		tvValues.setText(result);
		tvTotal.setText("Total: $" + Double.toString(MainActivity.totalValues[0]));
		if(MainActivity.totalValues[0]<0){
			tvTotal.setTextColor(Color.RED);
		}
	}
	
}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		new LoadData().execute();
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		spinner.setSelection(0);
	}	
}
