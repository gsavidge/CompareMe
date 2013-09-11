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
import android.widget.TextView;

public class MainScreenActivity extends Activity
{
	CompareMeDB myDb;
	TextView debugOutput;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle b)
	{
		super.onCreate(b);
		setContentView(R.layout.activity_main_screen);
	//	db=((CompareMe)getApplication()).db;
		 
		debugOutput = (TextView) findViewById(R.id.testDebug);
		Log.d("Mian Screen Activity", "Main Screen onCreate");
		
		Context con = getApplicationContext();
		con.deleteDatabase("compareMe.db");
		
		myDb = new CompareMeDB(this);
		Log.d("Hello","Hello");
		
	
		 // lets test to make
//	    myDb.openDatabase();
//	    db=myDb.getReadableDatabase();
		
		 		
		Cursor c = db.query("users",null,null,null,null,null,null,null);
	    logCursorInfo(c);
	    
	    c = db.query("questions",null,null,null,null,null,null,null);
	    logCursorInfo(c);
	    
	    c = db.query("topics",null,null,null,null,null,null,null);
	    logCursorInfo(c);
	    
	    c = db.query("language_table",null,null,null,null,null,null,null);
	    logCursorInfo(c);
	
		
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
		   case R.id.settingsBtn:       debugOutput.setText("4"); break;
		}
	}
	
//************************HitMeScreen Intent**********************************/
	public void hitMe()
	{
		Intent hitMe = new Intent (this, HitMeActivity.class);
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

