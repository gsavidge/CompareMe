package com.adigreg.compareme;
import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle b)
	{
		super.onCreate(b);
		//requestWindowFeature(Window.FEATURE_NO_TITLE); // remove the activity title
	   //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // Removes notification bar
		setContentView(R.layout.activity_settings);
	}
}
