package com.rook.budgetforme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainFragment extends Fragment implements OnClickListener {

	public static final String ARG_SECTION_NUMBER = "section_number";
	private String[] categories = {"Select a category", "Housing", "Food", "Transportation", "Entertainment", "Savings", "Other"};
	Spinner list = null;
	TextView tv = null;
	EditText value;
	EditText description;
	Button bSave, bCancel;
	//FileOutputStream fos;
	int year, month, day;
	Calendar calendar; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_input, container, false);
		tv = (TextView) rootView.findViewById(R.id.section_label);
		value = (EditText) rootView.findViewById(R.id.valueInput);
		description = (EditText) rootView.findViewById(R.id.descriptionInput);
		bSave = (Button) rootView.findViewById(R.id.saveButton);
		bCancel = (Button) rootView.findViewById(R.id.cancelButton);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories);
		list = (Spinner) rootView.findViewById(R.id.categorySpinner);
		//list.setOnItemSelectedListener(this);
		bSave.setOnClickListener(this);
		bCancel.setOnClickListener(this);
		Calendar calendar = Calendar.getInstance();
		
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
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DAY_OF_MONTH);
		return rootView;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.floatValue();
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
			switch(arg0.getId()){
			case (R.id.saveButton):
				double value = Double.parseDouble(this.value.getText().toString());
				value = round(value, 2);
				String description = this.description.getText().toString();
				MainActivity.totalValues[0] = MainActivity.totalValues[0] + value;
				SharedPreferences.Editor editor = MainActivity.income.edit();
				editor.putFloat(MainActivity.names[0] , (float) MainActivity.totalValues[0]);
				editor.commit();
				try {
					FileOutputStream fos = new FileOutputStream(MainActivity.files[0], true);
					fos.write((month + "/" + day + "/" + year + ": " + description + ": $" + value + "&").getBytes());
					fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				this.value.setText("");
				this.description.setText("");
				InputMethodManager inputManager = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			case (R.id.cancelButton):
				this.value.setText("");
				this.description.setText("");
				InputMethodManager inputManager1 = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager1.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			}
		}
		else{
			switch(arg0.getId()){
			case (R.id.saveButton):
				String value = this.value.getText().toString();
				String description = this.description.getText().toString();
				int position = list.getSelectedItemPosition();
				MainActivity.totalValues[position] = round(MainActivity.totalValues[position] + Double.parseDouble(value), 2);
				MainActivity.totalValues[0] = round(MainActivity.totalValues[0] - MainActivity.totalValues[position], 2);
				SharedPreferences.Editor editor = MainActivity.income.edit();
				editor.putFloat(MainActivity.names[position] , (float) MainActivity.totalValues[position]);
				editor.putFloat(MainActivity.names[0], (float) MainActivity.totalValues[0]);
				editor.commit();
				if(position!=0){
					try {
						FileOutputStream fos = new FileOutputStream(MainActivity.files[position], true);
						fos.write((month + "/" + day + "/" + year + ": " + description + ": $" + value + "&").getBytes());
						fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					this.value.setText("");
					this.description.setText("");
					list.setSelection(0);
					InputMethodManager inputManager = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); 
					inputManager.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				}
				break;
			case (R.id.cancelButton):
				this.value.setText("");
				this.description.setText("");
				list.setSelection(0);
				InputMethodManager inputManager = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			}
		}
	}
}