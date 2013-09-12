package com.adigreg.compareme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class HitMeActivity extends Activity
{
	TextView debugOutput;
   
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hit_me);
		
		debugOutput = (TextView) findViewById(R.id.testDebug);
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
