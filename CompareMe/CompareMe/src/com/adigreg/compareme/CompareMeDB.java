package com.adigreg.compareme;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class CompareMeDB extends SQLiteOpenHelper
{
   private static String DBPATH;
   private static String DBNAME = "compareMe.db";
   private SQLiteDatabase db;
   private final Context con;
   
   /*********************************************** constructor *****************************/
   public CompareMeDB(Context con) 
   {
      super(con, DBNAME, null, 1);
      this.con = con;
      DBPATH =con.getDatabasePath(DBNAME).toString();
      Log.d("databse path",DBPATH);
      getWritableDatabase(); // create a writable database. first time is called, onCreate will be called
   }
 
 /****************************** fillDatabase *********************
  * Task: fill database from the application assets folder
  ****************************************************************/
 private void fillDatabase(SQLiteDatabase db) throws IOException
 {
    Log.d("Fill Database", "started");
	 try{
          InputStream is = con.getResources().openRawResource(R.raw.compare_me);
          Scanner sc = new Scanner(new InputStreamReader(is));
          StringBuffer str = new StringBuffer();
          StringBuffer strSQLCommand = new StringBuffer();
          for (int i=0;i<6;i++)   // disregard first lines
          {
             str = str.append(sc.nextLine());
             Log.d("file",str.toString());
             str.setLength(0);    //clear the string
          }
          Log.d("file","start reading file");
          while (sc.hasNextLine())
          {
             str = str.append(sc.nextLine());
             strSQLCommand.append(str);
             if (str.toString().endsWith(";"))    // end of SQL command
             {
                Log.d("file",strSQLCommand.toString());
                db.execSQL(strSQLCommand.toString());    // exec SQL command
                strSQLCommand.setLength(0); //clear the Command string
             }
             str.setLength(0);    //clear the string
          }
          sc.close();
    }
    catch (Exception e)
    {
       Log.d("file","catch :" + e.getMessage());
    }
    Log.d("file","done");
 }
 
 /****************************** openDatabase *********************
  * Task: open a read/write database
  ****************************************************************/
 public void openDatabase() throws SQLException
 {
    db = SQLiteDatabase.openDatabase(DBPATH, null,SQLiteDatabase.OPEN_READWRITE);
    Log.d("openDatabase", "was here");
 }
 
 /****************************** close *********************
  * Task: close database
  ****************************************************************/
 @Override
 public synchronized void close() 
 {
    Log.d("close", "closed was called");
    if(db != null)
       db.close();
    super.close();
 }
 
 /****************************** onCreate *********************
  * Task: fill up the database. being called after getWritableDatabase 
  ****************************************************************/
 @Override
 public void onCreate(SQLiteDatabase db) 
 {
    Log.d("onCreate", "onCreate was called");
    try
    {
       Log.d("onCreate", "try");
       fillDatabase(db);
    }  catch (IOException e)
    {
       Log.d("onCreate", "catch");
    //   e.printStackTrace();
    }
    
 }
 
 /****************************** onUpgrade *********************
  * Task: upgrade the database. tbd
  ****************************************************************/
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
 {
    Log.d("onUpgrade", "onUpgrade was called");
    //Handle upgrade tasks, etc.
 }
  
 /**************************** mkToast Method****************************
  *  @param String                                                      *
  *  Description: show String as Toast                                  *
  ***********************************************************************/
  public void mkToast(String text)
  { 
     Toast t = Toast.makeText(con,text,Toast.LENGTH_SHORT);
     t.show();
  }

} //end of CompareMeDB.java
