package com.rook.budgetforme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
	//FileOutputStream fos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_input, container, false);
		tv = (TextView) rootView.findViewById(R.id.section_label);
		value = (EditText) rootView.findViewById(R.id.valueInput);
		description = (EditText) rootView.findViewById(R.id.descriptionInput);
		Button bSave = (Button) rootView.findViewById(R.id.saveButton);
		Button bCancel = (Button) rootView.findViewById(R.id.cancelButton);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories);
		list = (Spinner) rootView.findViewById(R.id.categorySpinner);
		//list.setOnItemSelectedListener(this);
		bSave.setOnClickListener(this);
		bCancel.setOnClickListener(this);
		/*try {
			fos = this.getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case (R.id.saveButton):
			String value = this.value.getText().toString();
			String description = this.description.getText().toString();
			int position = list.getSelectedItemPosition();
			switch(position){
				case 0:
					break;
				case 1:
					 try {
						    FileOutputStream fos = new FileOutputStream(MainActivity.housingFile, true);
						    fos.write((description + ": " + value + " ").getBytes());
						    fos.close();
						   } catch (IOException e) {
						    e.printStackTrace();
						   }
						   this.value.setText("");
						   this.description.setText("");
						/*try {
						MainActivity.fos.write(description.getBytes());
						MainActivity.fos.write(value.getBytes());
						MainActivity.fos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
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
			break;
		case (R.id.cancelButton):
			
			break;
		}
	}
}