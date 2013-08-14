/*
 Programmer:  Greg Savidge
 Class     :  CS211D
 Date      :  July 17, 2013
 Homework  :  #5
 Instructor:  Abbas Moghtanei
 File Name :  GeoQuiz.java
 
 Purpose:
 
 This is my LAUNCHER activity.  It is a simple game that asks a user to 
 guess the capital of a state. The game begins with the player either entering 
 his/her name and then pressing the "New Game" button, or pressing the
 "Show Scores" button and viewing the top ten scores of all time.  Upon 
 pressing the "New Game" button the activity passes the "name" that the
 user has entered to the NewGame activity.
 
 Once play begins the is given ten states chosen randomly from all 50 states 
 and is awarded 10 points for each correct guess.  After each guess the user 
 is informed that his/her guess is either correct or incorrect and the correct
 answer is shown via a Toast message. At the end of the game the user is 
 shown their total points achieved and shown the list of high scores.  The
 user then can play again by pressing the "Play Again?" button which will take
 him/her back to the LAUNCHER activity. 
 */

package com.cs211d.statescapitalquiz;

import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GeoQuiz extends Activity
{
	EditText name;
	
//*************************onCreate()****************************************//	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geo_quiz);
//---------------------------creates database and table---------------------//
		SQLiteDatabase db = openOrCreateDatabase("geoquiz_db", 
				SQLiteDatabase.CREATE_IF_NECESSARY, null);
		db.setVersion(1);
		
		db.execSQL("DROP TABLE IF EXISTS statesTable;");
		
		db.execSQL("create table if not exists statesTable" 
		   + "(_id integer primary key autoincrement, state text not null, " 
				+ "capital text not null);");
//--------------------------creates database and table----------------------//	

//-------------------reads state file into loadDatabase array--------------//		
		ArrayList<String>loadDatabase = new ArrayList<String>();
		Scanner sc = new Scanner(getResources().openRawResource(R.raw.states));
		sc.useDelimiter("    *|\n");
		
		while (sc.hasNext())
		{
		   String line = sc.next();
		   loadDatabase.add(line);
		}
		sc.close();
//-------------------reads state file into loadDatabase array--------------//	     

//___________________puts data into statesTable in geoquiz_db______________//
		ContentValues cv = new ContentValues();
		
		int capital = 5;
		for(int state = 4; state<loadDatabase.size()-1;state+=2)
		{ 
		    cv.put("state", loadDatabase.get(state));
		    cv.put("capital", loadDatabase.get(capital));
		   
            long recNumber = db.insert("statesTable", null, cv);
		    
		    capital+=2;	
		}
		loadDatabase.clear();
	}	
//*****************************onCreateOptions()*****************************//
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.geo_quiz, menu);
		return true;
	}
//****************************buttonHandler()********************************//
	public void buttonHandler(View v)
	{
		switch (v.getId())
		{
                              //only calls method if user has entered name//		    
		    case R.id.new_game:if(getName()) startGame(); break; 
		                   
		    case R.id.show_scores: startShowScores(); break;           
		}
	}
//**********************************getName()********************************//	
	public boolean getName()
	{
		name = (EditText) findViewById(R.id.name);
		String enteredName = name.getText().toString();
		
		if (enteredName.trim().isEmpty()) //checks other than whitespace//
		{
			Toast noName = Toast.makeText(this, "Please Enter A Name", Toast.LENGTH_SHORT);
			noName.setGravity(Gravity.CENTER, 0, -100);
			noName.show();
			return (false);	
		}
		else
		{
		   return(true);
		}
	 }
//*******************************startGame()*********************************//
	 public void startGame()
	 {
		 name = (EditText) findViewById(R.id.name);
		 String enteredName = name.getText().toString();
		 
		 Intent newGame = new Intent (this, NewGame.class);
		 newGame.putExtra("Name", enteredName);
		 startActivity(newGame);
	 }
//******************************startShowScores******************************//	
	 public void startShowScores()
	 {
		
		 Intent showScores = new Intent (this, ShowScores.class);
		 startActivity(showScores);
	 }
}
