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
import android.widget.Toast;

public class SplashScreenActivity extends Activity
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
	    
	    // Start new Thread for SplashScreen music and progress dialog
        new SplashScreen(this).execute();
	    
	   }
	   
	   
	   /*********************** class ConnectToDatabase *****************************/
	   private class ConnectToDatabase extends AsyncTask<String,Integer,Integer>
	   {
	      Context con;

	      ConnectToDatabase(Context ctx)
	      {
	            con = ctx;
	            Log.d("ConnectToDatabase","Constructor");
	      }
	      
	      /******************** onPostExecute *****************************
	       * The callback is called once the task is complete
	       */
	         @Override
	      protected void onPostExecute(Integer integer)
	      {
	         Log.d("onProgressUpdate", "onPostExecute result");
	         
	         //**************Sets db to Application***********
	         CompareMe compareme = (CompareMe)getApplication();
		     compareme.setDb(db);
	         
	      }
	      
	      @Override
	      protected Integer doInBackground(String...strings)
	      {
	         Log.d("doInBackground", "doInBackground");
	         
	         // prints the user name and password
	         Log.d("doInBackground", strings[0]+" :" + strings[1]);
	         // creates a new database
	         myDb = new CompareMeDB(con);
	         
	         // lets open the database
	         myDb.openDatabase();
	         db=myDb.getReadableDatabase();
	         if (db!=null)
	            return 0;
	         else return 1;
	      }
	   }
	   
	   
	   /*********************** class SplashScreen *****************************/
       private class SplashScreen extends AsyncTask<Void,Void,Integer>
       {
          Context con;
          ProgressDialog pd;

          /************************** constructor *********************************/
          SplashScreen(Context ctx)
          {
                con = ctx;
                Log.d("SplashScreen","Constructor");
          }

          /**************************** onPreExecute **************************
           * The callback is called right execute() is being called
           */
          @Override
          protected void onPreExecute()
          {
             Log.d("onPreExecute", "onPreExecute");
             // Start a progress bar
             pd = ProgressDialog.show(con, "ComapreMe", "Loading...",true);
             Log.d("onPreExecute", "onPreExecute");
          }
          
          @Override
          protected Integer doInBackground(Void... params)
          {
             int times =0;
             //*************From Application**********************
             CompareMe compareme = (CompareMe)getApplication();
             
             do
             {
                Log.d("doInBackground", "times:"+times);
                sleep(5000);    // sleep for 5 sec
                db= compareme.getDbNow();
                if (times==6)
                   break;
             } while (!db.isOpen()); // database is not open or ~30 sec passes
             
             if (times==6) 
                return 1;
                         
             return 0;
          }
          
          /******************** onPostExecute *****************************
           * The callback is called once the task is complete
           */
             @Override
             protected void onPostExecute(Integer integer)
             {
             // cancel progress bar
                pd.cancel();
             
                if (integer==0)
                {
                   Toast.makeText(con, "Working, database is open!",Toast.LENGTH_SHORT).show();
                   Intent i = new Intent (con,MainScreenActivity.class);
                   startActivity(i);
                }
                else Toast.makeText(con, "Error opening DB after 30 sec",Toast.LENGTH_SHORT).show();
             }
             
      
         
             public void sleep (long msec)
             {
                try 
                {
                   Thread.sleep(msec);
                }
                catch (InterruptedException e){e.printStackTrace(); }
             }

     
       }
}
