package com.adigreg.compareme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class BaseActivity extends Activity
{
	final String TAG = "Base Activity";
	Context con;
	TabHost tabHost;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_activity);
		con = getApplicationContext();
		
		tabHost = (TabHost)findViewById(R.id.tabhost);
		tabHost.setup();
		TabSpec tabSpec = tabHost.newTabSpec("tag1");
		tabSpec.setContent(R.id.tab1);
		tabSpec.setIndicator("Hit Me");
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tag2");
		tabSpec.setContent(R.id.tab2);
		tabSpec.setIndicator("Hit Them");
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tag3");
		tabSpec.setContent(R.id.tab3);
		tabSpec.setIndicator("Results");
		tabHost.addTab(tabSpec);

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
		  
				@Override
				public void onTabChanged(String arg0) {
					 Log.i("***Selected Tab", "Im currently in tab with index::" + tabHost.getCurrentTab());
					 
					 switch (tabHost.getCurrentTab())
						{
						case 0:
							hitMe();
							Log.i(TAG,"Hit Me Clicked!");
							break;
						case 1:
							Log.i(TAG,"Ask A Question Clicked!");
							break;
						case 2:
							Log.i(TAG,"Show Results Clicked!");
							break;
						}
				}       
		    });
		    
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		 Log.i("OptionMenu","***Option Menu***");       
		// Inflate the menu; this adds items to the action bar if it is present.
	//	MenuInflater inflater = getMenuInflater();
		getMenuInflater().inflate(R.menu.main_screen, menu);
		//return true;
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch (item.getItemId())
		{
			case R.id.ShowSettings:
				settings();
				Log.i(TAG,"Show Settings Clicked!");
				return true;
			default: return super.onOptionsItemSelected(item);
		}
	}
				

	//************************HitMeScreen Intent**********************************/
		public void hitMe()
		{
			Intent hitMe = new Intent (con, HitMeActivity.class);
			startActivity(hitMe);
		}
		
	//************************Questions Intent************************************/
		public void askAQuestion()
		{
			
		}
		
	//************************Search Results Intent*******************************/
		public void searchResults()
		{
			
		}
	
		
	//************************Settings Intent*************************************/
		public void settings()
		{
			Intent settings = new Intent (con, SettingsActivity.class);
			startActivity(settings);
		}


}
