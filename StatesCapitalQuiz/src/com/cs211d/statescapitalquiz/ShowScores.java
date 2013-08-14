/*
 Programmer:  Greg Savidge
 Class     :  CS211D
 Date      :  July 17, 2013
 Homework  :  #5
 Instructor:  Abbas Moghtanei
 File Name :  ShowScores.java
 
Purpose:

This is my ShowScores activity. This activity is responsible for receiving
a "name" and a "score" and placing them into the scoresTable that is within
the geoquiz_db.  It then queries 10 records ordered by descending order and 
displays then to two TextViews that have 11 lines each.  The format is of
"Name\nName\nName\n...etc".  If this activity is called without data being 
passed such as from the main screen, it will simply show the top ten scores
without inputting any data.
 */

package com.cs211d.statescapitalquiz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowScores extends Activity
{	
	String myName;
	String myScore;
	String concatenatedName="";
	String concatenatedScore="";
	
	Cursor c;
//***************************onCreate()**************************************//	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_scores);
		
		SQLiteDatabase db = openOrCreateDatabase("geoquiz_db", 
				SQLiteDatabase.OPEN_READWRITE, null);
		
		Bundle myBundle = getIntent().getExtras();
		
		if (myBundle != null) //in case is called from main screen//
		{	
		    myName = myBundle.getString("name");
		    myScore = myBundle.getString("score");

		    db.setVersion(1);
		
		    db.execSQL("create table if not exists scoresTable" 
				   + "(name text not null, " 
						+ "score text not null);");
		
		    ContentValues cv = new ContentValues();
		
		    cv.put("name", myName);
		    cv.put("score", myScore);
		    long recNumber = db.insert("scoresTable", null, cv);
		}
		
       //Query the db order by scores descending and limit of 10 records//
		c = db.query("scoresTable", null, null, null, null, null, "score DESC", "10");
		
		c.moveToFirst();
		
		//I am using concatenation of all the scores and all the names
		//and then am setting text to a TextView with 11 lines.
		while (c.isAfterLast() == false) 
		{
			String displayName = c.getString(0);
			concatenatedName += displayName + "\n";
			
			String displayScore = c.getString(1);
			concatenatedScore += displayScore + "\n";
			
			c.moveToNext();
			
		}
		System.out.println(concatenatedName);
		System.out.println(concatenatedScore);
		
		TextView name = (TextView)findViewById(R.id.namescore);
		name.setText(concatenatedName);
		
		TextView score = (TextView)findViewById(R.id.score);
		score.setText(concatenatedScore);
	}
//***************************playAgain()*************************************//
	public void playAgain(View v)
	{
		 Intent again = new Intent (this, GeoQuiz.class);
		 startActivity(again);
	}
}