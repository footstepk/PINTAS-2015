package com.projectpintas;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class PickStudentActivity extends ListActivity {
	
	private StudentDataSource datasource;
    List<Student> values;
    ArrayAdapter<Student> adapter;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		datasource = new StudentDataSource(this);
		datasource.open();
		addItemsToList();
	    listOnClicklistener(); 
	}

	private void addItemsToList() {
		values = datasource.getAllStudents();

	    // Use the ArrayAdapter to show the students in a ListView
	    adapter = new ArrayAdapter <Student>
                (this,R.layout.activity_pick_student_row, R.id.firstLine, values);
	    setListAdapter(adapter);
	}
	
	private void listOnClicklistener() {
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Student s = values.get(position);
				int studentId = s.getId();
				String message = "Loading User Profile ";
				Toast.makeText(PickStudentActivity.this, message, Toast.LENGTH_LONG).show();
				
				Intent i = new Intent(PickStudentActivity.this, ChooseLetterActivity.class);
				i.putExtra("studentId", studentId);
				startActivity(i);
			}
	      });
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(getParent(), "toast", Toast.LENGTH_LONG).show();
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pick_student, menu);
		return true;
	}

}
