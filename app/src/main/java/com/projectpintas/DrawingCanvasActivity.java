package com.projectpintas;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class DrawingCanvasActivity extends Activity implements
		OnGesturePerformedListener {

	private static final String TAG = "com.projectpintas";
	private DrawingCanvas drawing_canvas;
	private GestureLibrary mLibrary;
	
	//Variables for the database
	private int studentID;
	private String correct = "false";
	private double percentageCorrect = 0;
	private int attempts = 0;
	private int correctAttempts = 0;
	private int numChoice;
	private String[] letters = { "s", "a", "t", "i", "p", "n" };
    private Toast imgToast;
	private ImageView img; 
	private LinearLayout layout;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getIntent() != null){
			
			studentID = getIntent().getExtras().getInt("studentId");
			numChoice = getIntent().getExtras().getInt("int");
		}
		
		
		
		DrawingCanvas.canvas_choice = numChoice;
		setContentView(R.layout.drawing_canvas_activity);

		// Load the gestures library
		mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!mLibrary.load()) {
			Log.w(TAG, "unable to load gesture library");
			finish();
		}

		drawing_canvas = (DrawingCanvas) findViewById(R.id.the_canvas);

		GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures_overlay);
		gestures.addOnGesturePerformedListener(this);
		
		layout = new LinearLayout(this);
		layout.setBackgroundColor(getResources().getColor(R.color.bg_custom_colour_lighter2));
		img = new ImageView(this);
		img.setImageResource(R.drawable.happy);
		layout.addView(img);
		imgToast = new Toast(this);
		imgToast.setView(layout);
		imgToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	}

	public void goBack(View view) {

		Intent i = new Intent(DrawingCanvasActivity.this,
				ChooseLetterActivity.class);
		startActivity(i);
	}
    public void goHome(View view){
        Intent back = new Intent(this,
                MainMenuActivity.class);
        startActivity(back);
    }
    //Move to next screen
	public void goForward(View view) {
		DrawingCanvas.dots = true;
		drawing_canvas.clear();
	}
    //Clear method
	public void deleteDrawing(View view) {
		drawing_canvas.clear();
	}

	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		ArrayList<Prediction> predictions = mLibrary.recognize(gesture);

		if (predictions.size() > 0) {
			Prediction prediction = predictions.get(0);
			// We want at least some confidence in the result
			if (prediction.score > 4.0
					&& letters[numChoice - 1].equals(prediction.name)) {
				correct = "true";
				correctAttempts++;
				attempts++;
				img.setImageResource(R.drawable.tick);
				imgToast.show();
				Toast.makeText(this, "Well Done!!!",
						Toast.LENGTH_SHORT).show();
			}
			else{
				// Incorrect attempt show a sad face on screen and increment attempts
				attempts++;
				img.setImageResource(R.drawable.cross);
				imgToast.show();
				calculatePercentage();
			}
		}
	}
	
	//Method to calculate the percentage of correct attempts
	private void calculatePercentage(){
		
		if(attempts != 0){
			percentageCorrect =( correctAttempts/attempts) * 100;
		}
		else{
			Log.d("calCulatePercentage","divide by zero attempt");
		}
	}

}
