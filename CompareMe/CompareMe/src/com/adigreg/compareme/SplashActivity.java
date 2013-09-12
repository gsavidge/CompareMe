
package com.adigreg.compareme;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashActivity extends Activity 
{
   CompareMeDB myDb;
   SQLiteDatabase db;
   Context con = null;

   /**************************** onCreate Method****************************/
   @Override
   protected void onCreate(Bundle b) 
   {
    super.onCreate(b);

    requestWindowFeature(Window.FEATURE_NO_TITLE);    // remove the activity title
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar
    setContentView(R.layout.activity_splash);
    
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
   
   private class ConnectToDatabase extends AsyncTask<String,Integer,SQLiteDatabase>
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
      protected void onPostExecute(SQLiteDatabase db)
      {
         Log.d("onProgressUpdate", "onPostExecute result");
         // cancel progress bar
         pd.cancel();
         Bundle b= new Bundle();
         Intent i = new Intent (con,MainScreenActivity.class);
         startActivity(i);
         
      }
      
      protected SQLiteDatabase doInBackground(String...strings)
      {
         Log.d("doInBackground", "doInBackground");
         // creates a new database
         myDb = new CompareMeDB(con);
         
         // lets open the database
         myDb.openDatabase();
         db=myDb.getReadableDatabase();
       
         return db;
      }
   }
   
}
