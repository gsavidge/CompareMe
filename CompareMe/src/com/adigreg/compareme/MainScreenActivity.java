/*
 * Main Screen Activity
 */

package com.adigreg.compareme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainScreenActivity extends BaseActivity
{
	final String TAG = "Main Screen Actvity";
	CompareMeDB myDb;
	TextView debugOutput;
	SQLiteDatabase db;
	Context con;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.activity_main_screen);
	
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

