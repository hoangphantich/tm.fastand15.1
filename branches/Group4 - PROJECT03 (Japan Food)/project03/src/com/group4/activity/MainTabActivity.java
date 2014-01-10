package com.group4.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.group4.custom.CustomGridViewAdapter;
import com.group4.foodclass.Data;
import com.group4.foodclass.Food;
import com.group4.view.BaseFragmentActivity;

public class MainTabActivity extends BaseFragmentActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_section1);
			case 1:
				return getString(R.string.title_section2);
			case 2:
				return getString(R.string.title_section3);
			}
			return null;
		}
	}

	public static class DummySectionFragment extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";
		ArrayList<Food> arrFood;
		
		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			final View rootView= inflater.inflate(R.layout.fragment_main,container, false);
			GridView gridView;
			CustomGridViewAdapter customGridAdapter;	
//			String sdcard=Environment.getExternalStorageDirectory().getAbsolutePath();
			int tabSelected=getArguments().getInt(ARG_SECTION_NUMBER);
			
			switch (tabSelected) {
			case 1:				
//				rootView= inflater.inflate(R.layout.fragment_tab1,container, false);
			
				//get Data
//				String xmlfile=sdcard+"/mydata/assets/1.xml";
				Data dt=new Data(rootView.getContext(),1);
				arrFood = new ArrayList<Food>();				
				arrFood=dt.getArrFood();
//				Toast.makeText(rootView.getContext(), dt.getArrFood().get(0).subject, Toast.LENGTH_LONG).show();
				break;
			case 2:
//				rootView= inflater.inflate(R.layout.fragment_tab2, container, false);
				
				//get Data
//				xmlfile=sdcard+"/mydata/assets/2.xml";
				dt=new Data(rootView.getContext(),2);
				arrFood = new ArrayList<Food>();				
				arrFood=dt.getArrFood();
				break;
			case 3:
//				rootView= inflater.inflate(R.layout.fragment_tab3, container, false);
				//get Data
//				xmlfile=sdcard+"/mydata/assets/3.xml";
				dt=new Data(rootView.getContext(),3);
				arrFood = new ArrayList<Food>();				
				arrFood=dt.getArrFood();				
				break;
			default:
				break;
			}		

			//custom gridView			
			gridView = (GridView) rootView.findViewById(R.id.gridView1);
			customGridAdapter = new CustomGridViewAdapter(rootView.getContext(), R.layout.row_grid, arrFood);
			gridView.setAdapter(customGridAdapter);
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Intent it_detail=new Intent(rootView.getContext(), DetailActivity.class);
					it_detail.putExtra("object", arrFood.get(arg2));
					startActivity(it_detail);
//					Toast.makeText(arg1.getContext(), arrFood.get(arg2).getCbb(), Toast.LENGTH_LONG).show();
				}
			});
			
//			TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
//			dummyTextView.setText(Integer.toString(getArguments().getInt(
//					ARG_SECTION_NUMBER)));
			return rootView;
		}		
	}
}
