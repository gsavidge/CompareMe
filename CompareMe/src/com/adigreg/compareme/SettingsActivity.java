package com.adigreg.compareme;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SettingsActivity extends Activity 
{
	final String TAG = "Settings Activity";	

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "Preference started");
     // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
       .replace(android.R.id.content, new SettingsFragment()).commit();
        
        
    }
}  