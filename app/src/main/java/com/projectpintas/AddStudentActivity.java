package com.projectpintas;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class AddStudentActivity extends ListActivity {
    //Declare Variables
	private StudentDataSource datasource;
	private View viewContainer;
	TextView idView;
	EditText firstNameBox;
	EditText lastNameBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        //Open a link to the database via a data access object
		datasource = new StudentDataSource(this);
		datasource.open();

		idView = (TextView) findViewById(R.id.studentID);
		firstNameBox = (EditText) findViewById(R.id.firstname);
		lastNameBox = (EditText) findViewById(R.id.lastname);

		List<Student> values = datasource.getAllStudents();
		ListView l = (ListView) findViewById(android.R.id.list);

		// Show elements of the arrayadapter in a list
		ArrayAdapter<Student> adapter =
                new ArrayAdapter<Student>(this,android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		viewContainer = findViewById(R.id.undobar);
		l.setAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		showUndo(viewContainer);
		return true;
	}
    //Notification of undelete action
	public void onClick(View view) {
		Toast.makeText(this, "Record has been re-created", Toast.LENGTH_LONG).show();
		viewContainer.setVisibility(View.GONE);
	}
    //Return to main menu
	public void onClickBack(View view) {

		Intent i = new Intent(AddStudentActivity.this, MainMenuActivity.class);
		startActivity(i);
	}
    //Add a student to the database
	public void onClickAdd(View view) {
		Student student = new Student(firstNameBox.getText().toString(),
				lastNameBox.getText().toString());
		datasource.createStudent(student);
		@SuppressWarnings("unchecked")
		ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) getListAdapter();
		adapter.add(student);
		adapter.notifyDataSetChanged();
		firstNameBox.setText("");
		lastNameBox.setText("");

	}
    //Delete a student from the database
	public void onClickDelete(View view) {
		ListView listview = (ListView) findViewById(android.R.id.list);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				@SuppressWarnings("Unchecked")
				ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) getListAdapter();
				if (getListAdapter().getCount() > 0) {
					Student student = (Student) getListAdapter().getItem(
							position);
					datasource.deleteStudent(student);
					adapter.remove(student);
					adapter.notifyDataSetChanged();
					showUndo(viewContainer);
				}
			}
		});
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void showUndo(final View viewContainer) {
		viewContainer.setVisibility(View.VISIBLE);
		viewContainer.setAlpha(1);
		viewContainer.animate().alpha(0.4f).setDuration(5000)
				.withEndAction(new Runnable() {
					@Override
					public void run() {
						viewContainer.setVisibility(View.GONE);
					}
				});
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}
