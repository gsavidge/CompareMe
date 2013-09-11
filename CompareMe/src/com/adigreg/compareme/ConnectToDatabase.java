package com.adigreg.compareme;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ConnectToDatabase extends AsyncTask<String,Integer,Integer>
{
   ProgressDialog pd = null;
   Context con;
   public String tag = null;
   ConnectToDatabase(Context ctx, String inTag)
   {
         con = ctx;
         tag = inTag;
   }
   protected void onPreExecute()
   {
   //Runs on the main ui thread
      pd = ProgressDialog.show(con, "title", "In Progress...",true);
   }

   protected void onProgressUpdate(Integer... progress)
   {

      Integer i = progress[0];
      Log.d("onProgressUpdate", "Progress:" + i.toString());
   }
   
   protected void onPostExecute(Integer result)
   {
      Log.d("onProgressUpdate", "onPostExecute result:" + result);
      pd.cancel();
   }
   
   protected Integer doInBackground(String...strings)
   {
    
      for(String s :strings)
      {
         Log.d(tag, "Processing:" + s);
      }
      for (int i=0;i<3;i++)
      {
         publishProgress(i);
      }
      return 1;
      }
}
