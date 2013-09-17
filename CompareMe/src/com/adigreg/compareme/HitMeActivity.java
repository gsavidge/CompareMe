package com.adigreg.compareme;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;

public class HitMeActivity extends Activity
{
	TextView debugOutput;
	CompareMeDB hitmeDB;
	SQLiteDatabase db;
	Cursor c;
	
	
	
	RadioButton question1, question2, question3, question4;
	TextView questionTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // remove the activity title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // Removes notification bar
		setContentView(R.layout.activity_hit_me);
		
		//hitmeDB = new CompareMeDB(this);
		//hitmeDB.openDatabase();
		//db = hitmeDB.getReadableDatabase();
		
		
		//*************From Application**********************
	     CompareMe compareme = (CompareMe)getApplication();
	     db= compareme.getDbNow();
		
		c = db.query("questions",null,null,null,null,null,null,null);
		c.moveToPosition(0);
		
		setQuestions();
		
		debugOutput = (TextView) findViewById(R.id.testDebug);
		
		 
	
		if (db.isOpen()) debugOutput.setText("open");
		
		
	}
//*****************************Set Q's and Title From DB**********************/
	public void setQuestions()
	{
		questionTitle = (TextView) findViewById(R.id.questionTitle);
		
		String strQuestionTitle = c.getString(4);
		questionTitle.setText(strQuestionTitle);
		
		question1 = (RadioButton) findViewById(R.id.Question1);
		question2 = (RadioButton) findViewById(R.id.Question2);
		question3 = (RadioButton) findViewById(R.id.Question3);
		question4 = (RadioButton) findViewById(R.id.Question4);
		
		String strQuestion1 = c.getString(5);
		question1.setText(strQuestion1);
		
		String strQuestion2 = c.getString(6);
		question2.setText(strQuestion2);
		
		String strQuestion3 = c.getString(7);
		question3.setText(strQuestion3);
		
		String strQuestion4 = c.getString(8);
		question4.setText(strQuestion4);
	}	
//******************************RadioButton Handler***************************/	
	public void onRadioBtnClicked(View v)
    {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) v).isChecked(); 
	
	    switch (v.getId())
	    {
 	      case R.id.Question1: if (checked) debugOutput.setText("Clicked 1"); break;
 	      case R.id.Question2: if (checked) debugOutput.setText("Clicked 2"); break;
 	      case R.id.Question3: if (checked) debugOutput.setText("Clicked 3"); break;
 	      case R.id.Question4: if (checked) debugOutput.setText("Clicked 4"); break;

        }   
    }
 
//*****************************Submit Button Handler**************************/
    public void submitBtnPushed(View v)
    {
    	Intent showResults = new Intent (this, ShowResultsActivity.class);
    	startActivity(showResults);
    }
}
