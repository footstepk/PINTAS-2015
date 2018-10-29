package com.projectpintas;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SplashScreen extends ActionBarActivity {
	
	 // Splash screen timer
    private static int SPLASH_TIME_OUT = 4000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		new Handler().postDelayed(new Runnable() {
			 
            //Showing splash screen with a timer.
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Starts app login activity
                Intent i = new Intent(SplashScreen.this, MainMenuActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
	}

}
