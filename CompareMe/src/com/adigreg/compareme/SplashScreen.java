package com.adigreg.compareme;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity
{
	 CompareMeDB myDb;
	 SQLiteDatabase db;
	  Context con = null;

	   /**************************** onCreate Method****************************/
	   @Override
	   protected void onCreate(Bundle b)
	   {
	    super.onCreate(b);

	    requestWindowFeature(Window.FEATURE_NO_TITLE); // remove the activity title
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // Removes notification bar
	    setContentView(R.layout.activity_splash_screen);
	    
	    // Database credentials - not used for now
	    String masterUserName=getResources().getString(R.string.DBMasterUserName);
	    String masterPassword=getResources().getString(R.string.DBMasterPasswrod);
	    
	    // delete DB, just to debug
	    Context context = getApplicationContext();
	    context.deleteDatabase("compareMe.db");
	    
	    Log.d("SplashActivity","onCreate");
	    
	    // Start new Thread for connecting to the database
	    new ConnectToDatabase(this).execute(masterUserName,masterPassword);
	    
	   }
	   
	   private class ConnectToDatabase extends AsyncTask<String,Integer,Integer>
	   {
	      ProgressDialog pd = null;
	      Context con;
	      public String tag = null;
	      ConnectToDatabase(Context ctx)
	      {
	            con = ctx;
	            Log.d("ConnectToDatabase","Constructor");
	      }
	      /**************************** onPreExecute **************************
	* The callback is called right execute() is being called
	*/
	      @Override
		protected void onPreExecute()
	      {
	         Log.d("onPreExecute", "onPreExecute");
	         // Start a progress bar
	         pd = ProgressDialog.show(con, "ComapreMe", "In Progress...",true);
	         Log.d("onPreExecute", "onPreExecute");
	      }
	      
	      /******************** onPostExecute *****************************
	* The callback is called once the task is complete
	*/
	      @Override
		protected void onPostExecute(Integer integer)
	      {
	         Log.d("onProgressUpdate", "onPostExecute result");
	         // cancel progress bar
	         pd.cancel();
	         
	         //**************Sets db to Application***********
	         CompareMe compareme = (CompareMe)getApplication();
		     compareme.setDb(db);
	         
		     
		     Bundle b= new Bundle();
	         Intent i = new Intent (con,MainScreen.class);
	         startActivity(i);
	         
	      }
	      
	      @Override
		protected Integer doInBackground(String...strings)
	      {
	         Log.d("doInBackground", "doInBackground");
	         // creates a new database
	         myDb = new CompareMeDB(con);
	         
	         // lets open the database
	         myDb.openDatabase();
	         db=myDb.getReadableDatabase();
	       
	         return 1;
	      }
	   }
}
