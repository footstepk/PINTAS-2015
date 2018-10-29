package com.projectpintas;

import android.annotation.TargetApi;
import android.app.ListActivity;
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

public class TestDatabaseActivity extends ListActivity {
	private StudentDataSource datasource;
	private View viewContainer;
	TextView idView;
	EditText firstNameBox;
	EditText lastNameBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		datasource = new StudentDataSource(this);
		datasource.open();
    
	    idView = (TextView) findViewById(R.id.studentID);
	    firstNameBox = (EditText) findViewById(R.id.firstname);
	    lastNameBox = (EditText) findViewById(R.id.lastname);
    
	    List<Student> values = datasource.getAllStudents();
	    ListView l = (ListView) findViewById(android.R.id.list);
	    // use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this,
	        android.R.layout.simple_list_item_1, values);
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
	public void onClick(View view) {
		Toast.makeText(this, "Deletion reversed", Toast.LENGTH_LONG).show();
		viewContainer.setVisibility(View.GONE);
	}

	// Add student
	public void onClickAdd(View view) {
		Student student = new Student(firstNameBox.getText().toString(), lastNameBox.getText().toString());
		datasource.createStudent(student);
		@SuppressWarnings("unchecked")
		ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) getListAdapter();
		adapter.add(student);
		adapter.notifyDataSetChanged();
		firstNameBox.setText("");
		lastNameBox.setText("");

	}
    //Delete student
	public void onClickDelete(View view) {
		  ListView lview = (ListView) findViewById(android.R.id.list);
		  lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					  @SuppressWarnings("unchecked")
					  ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) getListAdapter();
					  if (getListAdapter().getCount() > 0) {
				          Student student = (Student) getListAdapter().getItem(position);
				          datasource.deleteStudent(student);
				          adapter.remove(student);
				          adapter.notifyDataSetChanged();
				          showUndo(viewContainer);
				      }//close if
				}//Close onItemClick
			});//Close setOnItemClickListener
	}//Close onClickDelete

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void showUndo(final View viewContainer) {
		viewContainer.setVisibility(View.VISIBLE);
		viewContainer.setAlpha(1);
		viewContainer.animate().alpha(0.4f).setDuration(5000).withEndAction(new Runnable() {
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

