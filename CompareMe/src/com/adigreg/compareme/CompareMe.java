package com.adigreg.compareme;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CompareMe extends Application
{
	CompareMeDB myDb;
	SQLiteDatabase db;

	SQLiteDatabase testDb;
	
	Context con = null;
	
	SQLiteDatabase myDbSet;
	






	
	
	
	
	@Override
	public void onCreate()
	{
		
		
		super.onCreate();
		
		// Database credentials - not used for now //need to extend Activity or Service
	    //String masterUserName=getResources().getString(R.string.DBMasterUserName);
	    //String masterPassword=getResources().getString(R.string.DBMasterPasswrod);
	    
	    // delete DB, just to debug
	    //Context context = getApplicationContext();
	    //context.deleteDatabase("compareMe.db");
	    
	    Log.d("SplashActivity","onCreate");
	    
	    // Start new Thread for connecting to the database
	    
	    
	   
	    
	   
	    //Toast.makeText(constant, "Here I am", Toast.LENGTH_LONG).show();
	    
	   }
	  
	  public SQLiteDatabase getDbNow ()
      {
    	  return myDbSet;
      }
	  
	  public void setDb(SQLiteDatabase sql)
	  {
		  myDbSet = sql;
	  }
	  
	   
	   
	}