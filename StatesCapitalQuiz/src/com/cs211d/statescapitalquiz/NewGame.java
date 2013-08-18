/*
 Programmer:  Greg Savidge **modifying**
 Class     :  CS211D
 Date      :  July 17, 2013
 Homework  :  #5
 Instructor:  Abbas Moghtanei
 File Name :  NewGame.java
 
Purpose:

This is my NewGame activity.  This activity is responsible for querying
database to get states and capitals.  Displaying the states to the user,
and determining if the user typed the correct answer.  Upon completion
the activity sends the "name" of the user and his/her "score" to 
the ShowScore activity.  
 */

package com.cs211d.statescapitalquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.*;
import android.view.*;
import android.widget.*;

public class NewGame extends Activity
{
	SQLiteDatabase db;
	Cursor c;
	
	TextView name;
	String myName;
	
	String strAnswer;
	
	List<Integer> position;
	int positionCounter = 1;
	
	int numberCorrect = 0;
	
//***************************onCreate()**************************************//
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
		
		//get name from GeoQuiz activity//
		Bundle myBundle = getIntent().getExtras();
		myName = myBundle.getString("Name");
		
		 Toast isName = Toast.makeText(this, "Welcome " 
		     + myName, Toast.LENGTH_SHORT);
	     isName.setGravity(Gravity.CENTER, 0, -200);
		 isName.show();
		
		TreeSet <Integer> ts = new TreeSet<Integer>(); //unique Integer entry 
		
		while (ts.size() != 10)
		{	
		   
		    ts.add(rand(0,49)); 
		} 
		
		position = new ArrayList<Integer>(ts); //creates List from TreeSet
		
		//Connect to Database//
		 db = openOrCreateDatabase("geoquiz_db", SQLiteDatabase.OPEN_READWRITE, null);
		
		 c = db.query("statesTable", null, null, null, null, null, null, null);
 
		c.moveToPosition(position.get(0)); // This is first position 0-9 		
		
		String question1 = c.getString(1); // Returns 1st State at position 0
		
		name = (TextView) findViewById(R.id.question);
		
		name.setText("The capital of " + question1 + " ?");
	}
//***************************getAnswer()*************************************//
	public void getAnswer(View v)
	{
		EditText answer = (EditText) findViewById(R.id.answer);
		
		if ((! answer.getText().toString().trim().isEmpty()) && 
				(positionCounter != 11))
		{
			strAnswer = answer.getText().toString().toLowerCase();
			
			if (strAnswer.equals(c.getString(2).toLowerCase())) //matches?
			{
				numberCorrect+= 10; //each answer worth 10 pts.
				
				toaster("Correct Answer", Color.GREEN);

			    //waits until message is displayed before method askQuestion()
				final Handler handler = new Handler();
			    handler.postDelayed(new Runnable() 
			    {
			      @Override
			      public void run() 
			      {
			        askQuestion();
			      }
			    }, 2500);   
			}
			else
			{
				toaster("Incorrect, Answer is: " + c.getString(2), Color.RED);

				//waits until message is displayed before method askQuestion()
				final Handler handler = new Handler();
			    handler.postDelayed(new Runnable() 
			    {
			      @Override
			      public void run() 
			      {
			        askQuestion();
			      }
			    }, 2500);  
			}
		}	
	}
//***********************askQuestion()***************************************//
	public void askQuestion ()
	{
		if (positionCounter !=10)
		{		
		   EditText answer = (EditText) findViewById(R.id.answer);
		   answer.setText("");
		   answer.setHint("type answer here:");
			
		   c.moveToPosition(position.get(positionCounter));
		   String questionX = c.getString(1);
		   name.setText("The capital of " + questionX + " ?");
		   positionCounter++;
		}
		else  //ends game and sends name, score to ShowScores.java
		{
			Toast noName = Toast.makeText(this, "You Score Is: " 
		       + numberCorrect + " out of 100 great Job!", Toast.LENGTH_LONG);
			noName.setGravity(Gravity.CENTER, 0, -200);
			noName.show();
			positionCounter++;
			//waits until message is displayed before method showScores()
			final Handler handler = new Handler();
		    handler.postDelayed(new Runnable() {
		    @Override
		    public void run() {
		        showScores();
		      }
		    }, 2500);
		}	
	}
//**************************rand()*******************************************//	
	public int rand(int a, int b)
	{
	   return((int)((b-a+1)*Math.random() + a));
	}
	//***************************showScores()********************************//	
	public void showScores()
	{
	    //String score = Integer.toString(numberCorrect);
		String score = String.format("%03d", numberCorrect);
		Intent showScores = new Intent (this, ShowScores.class);
		showScores.putExtra("name", myName);
		showScores.putExtra("score", score);
		 
		startActivity(showScores);
	}
//*******************************toaster()***********************************//
	public void toaster(String text, int color)
	{
		Toast toast = Toast.makeText(getApplicationContext(), text, 
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, -200);
		LinearLayout toastLayout = (LinearLayout) toast.getView();
		TextView toastTV = (TextView) toastLayout.getChildAt(0);
		toastTV.setTextSize(20);
		toastTV.setTextColor(color);
		toast.show();
	}
}
