/*
 * Main Screen Activity
 */

package com.adigreg.compareme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainScreenActivity extends Activity
{
	CompareMeDB myDb;
	TextView debugOutput;
	SQLiteDatabase db;
	Context con;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); // remove the activity title
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // Removes notification bar
		setContentView(R.layout.activity_main_screen);
		 
		debugOutput = (TextView) findViewById(R.id.testDebug);
		Log.d("Message", "app is starting");
		
		
		
		con = getApplicationContext();
		con.deleteDatabase("compareMe.db");
		
		
		//**************From Application*********************
		CompareMe compareme = (CompareMe)getApplication();
	    db = compareme.getDbNow();
		
		
		
		
		Cursor c = db.query("users",null,null,null,null,null,null,null);
	    logCursorInfo(c);
	    
	    c = db.query("questions",null,null,null,null,null,null,null);
	    logCursorInfo(c);
	    
	    c = db.query("topics",null,null,null,null,null,null,null);
	    logCursorInfo(c);
	    
	    c = db.query("language_table",null,null,null,null,null,null,null);
	    logCursorInfo(c);
		
	    //CompareMe display= new CompareMe(getBaseContext());
	    
	    if (!db.isOpen()) debugOutput.setText("open");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}

//************************ButtonHandler***************************************/	
	public void mainActivityBtnHandler(View v)
	{
		switch (v. getId())
		{
		   case R.id.getQuestionsBtn:   hitMe(); break;
		   case R.id.askQuestionsBtn:   debugOutput.setText("2"); break;
		   case R.id.searchBtn:         debugOutput.setText("3"); break;
		   case R.id.settingsBtn:       settings(); break;
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

	//*************************Database Log Visualization************************/	
	public void logCursorInfo(Cursor c)
	{
	   String TAG="DB Log";
	   Log.i(TAG,"***Cursor begin***" + " Results: " + c.getCount()+ " Columns: "+c.getColumnCount());       
	   String header="|";
	   for (int i=0; i<c.getColumnCount();i++)
	   {
	       header+= c.getColumnName(i)+"|";
	   } 
	   Log.i (TAG,"Columns"+header);
	   c.moveToFirst();
	   while (c.isAfterLast()==false)
	   {
	       String results = "|";
	       for(int i=0;i<c.getColumnCount();i++)
	       {
	           results +=c.getString(i)+"|";
	       }
	       Log.i(TAG,"Row "+c.getPosition()+ ":"+results);
	       c.moveToNext();
	   }
	   Log.i(TAG,"*** CursorEnd ***");
    }
}

