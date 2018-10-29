package com.projectpintas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseLetterActivity extends Activity {

	Intent session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_letter);
		session = new Intent(ChooseLetterActivity.this,
				DrawingCanvasActivity.class);

	}

	// Methods to set the correct image

	public void startSessionS(View view) {
		session.putExtra("int", 1);
		startActivity(session);

	}

	public void startSessionA(View view) {

		session.putExtra("int", 2);
		startActivity(session);

	}

	public void startSessionT(View view) {

		session.putExtra("int", 3);
		startActivity(session);

	}

	public void startSessionI(View view) {

		session.putExtra("int", 4);
		startActivity(session);

	}

	public void startSessionP(View view) {

		session.putExtra("int", 5);
		startActivity(session);
	}

	public void startSessionN(View view) {

		session.putExtra("int", 6);
		startActivity(session);
	}
	//Method to return to main menu
	public void goBack(View view){
		Intent back = new Intent(ChooseLetterActivity.this,
				MainMenuActivity.class);
		startActivity(back);
	}

}
