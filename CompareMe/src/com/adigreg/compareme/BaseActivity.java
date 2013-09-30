package com.adigreg.compareme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends Activity
{
	final String TAG = "Base Activity";
	Context con;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);		
		con = getApplicationContext();
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
		case R.id.HitMe:
			hitMe();
			Log.i(TAG,"Hit Me Clicked!");
			return true;
		case R.id.AskAQestion:
			Log.i(TAG,"Ask A Question Clicked!");
			return true;
		case R.id.ShowRestuls:
			Log.i(TAG,"Show Results Clicked!");
			return true;
		case R.id.ShowSettings:
			settings();
			Log.i(TAG,"Show Settings Clicked!");
			return true;
		default: return super.onOptionsItemSelected(item);
	 
		
		}
	
	//	return super.onOptionsItemSelected(item);

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
