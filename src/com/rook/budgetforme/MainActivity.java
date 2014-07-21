package com.rook.budgetforme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	//public static File housing = new File("housing.txt");	
	//public static FileOutputStream fos;
	//public static String housing = "housing.txt";
	public static String filepath = "MyFileStorage";
	public static File fIncome, fHousing, fFood, fTransportation, fEntertainment, fSavings, fOther, 
		fIncomeTotal, fHousingTotal, fFoodTotal, fTransportationTotal, fEntertainmentTotal, fSavingsTotal, fOtherTotal;
	private String[] docs = {"income.txt", "housing.txt", "food.txt", "transportation.txt", "entertainment.txt", "savings.txt", "other.txt"};
	public static String[] names = {"incomeTotal", "housingTotal", "foodTotal", "transportationTotal", "entertainmentTotal",
		"savingsTotal", "otherTotal"};
	public static File[] files = {fIncome, fHousing, fFood, fTransportation, fEntertainment, fSavings, fOther};
	//public static File[] totals = {fIncomeTotal, fHousingTotal, fFoodTotal, fTransportationTotal, fEntertainmentTotal, fSavingsTotal, fOtherTotal};
	private static double tIncome, tHousing, tFood, tTransportation, tEntertainment, tSavings, tOther;
	public static double[] totalValues = {tIncome, tHousing, tFood, tTransportation, tEntertainment, tSavings, tOther};
	public static String filename = "MyIncome";
	//public static float totalIncome;
	public static SharedPreferences income;
	
	public static int[] COLORS = new int[] {Color.GREEN, Color.BLUE,Color.MAGENTA, Color.CYAN, Color.RED, Color.YELLOW, Color.BLACK};  
	//public static double[] pieValues = new double[7];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
		File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
		for(int i=0; i<docs.length;i++){
			files[i] = new File(directory, docs[i]);
			//totals[i] = new File(directory, totalDocs[i]);
		}
		for(int i=0; i<docs.length;i++){
			try {
				FileOutputStream fos = new FileOutputStream(MainActivity.files[i], true);
				fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		income = getSharedPreferences(filename, 0);
		new LoadIncomes().execute();
		//File fHousing = new File(directory , housing);
		
		/*try {
			fos = new FileOutputStream(housing, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = null;
			switch(position){
			case 0:
				fragment = new OverviewFragment();
				break;
			case 1:
				fragment = new MainFragment();
				Bundle args = new Bundle();
				args.putInt(MainFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				break;
			case 2:
				fragment = new MainFragment();
				Bundle args1 = new Bundle();
				args1.putInt(MainFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args1);
				break;
			}
			
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
			
		}
	}
	public class LoadIncomes extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			income = getSharedPreferences(filename, 0);
			for(int i=0; i<names.length;i++){
				totalValues[i] = MainFragment.round(income.getFloat(names[i], 0),2);
			}
			return null;
		}
		
	}
}
