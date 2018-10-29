package com.projectpintas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainmenu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.test_database, menu);
		return true;
	}

	// Move to add student screen
	public void onClickSetupStudents(View view) {
		Intent i = new Intent(MainMenuActivity.this, AddStudentActivity.class);
		startActivity(i);
	}

	// Move to pick student screen
	public void onClickStartStudentSession(View view) {
		Intent i = new Intent(MainMenuActivity.this, PickStudentActivity.class);
		startActivity(i);
	}
	//Move to results screen
	public void onClickViewResults(View view) { 
        Intent i = new Intent(MainMenuActivity.this, ResultsWk1Activity.class);
        startActivity(i);
    } 

}
