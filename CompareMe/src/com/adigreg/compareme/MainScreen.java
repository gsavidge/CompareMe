/*
 * Main Screen Activity
 */

package com.adigreg.compareme;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainScreen extends Activity
{
	TextView debugOutput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		 
		debugOutput = (TextView) findViewById(R.id.testDebug);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}
	
	
    
	public void mainActivityBtnHandler(View v)
	{
		switch (v. getId())
		{
		   case R.id.getQuestionsBtn:   debugOutput.setText("1"); break;
		   case R.id.askQuestionsBtn:   debugOutput.setText("2"); break;
		   case R.id.searchBtn:         debugOutput.setText("3"); break;
		   case R.id.settingsBtn:       debugOutput.setText("4"); break;
		}
	}
}
