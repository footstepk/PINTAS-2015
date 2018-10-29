package com.projectpintas;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class ResultsWk1Activity extends Activity {
	private ResultsDataSource datasourceR;
	private StudentDataSource datasourceS;
    List<Result> valuesR;
    List<Student> valuesS;
    ArrayAdapter<Result> adapterR;
    ArrayAdapter<Student> adapterS;
    ArrayAdapter<String> adapter;
    SparseArray<Group> groups = new SparseArray<Group>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results_wk1);
		
		datasourceR = new ResultsDataSource(this);
		datasourceS = new StudentDataSource(this);
		datasourceR.open();
		datasourceS.open();
	    
	    // add Result
		datasourceR.addResult(new Result( 1, "1", "1"));  
		datasourceR.addResult(new Result( 3, "1", "0"));
		datasourceR.addResult(new Result( 4, "1", "1"));
		
		//Get both lists
		valuesR = datasourceR.getAllResults();
		valuesS = datasourceS.getAllStudents();
		//Process the lists
		ArrayList<String> datalist = new ArrayList<String>();
		for(Student s: valuesS){
			String data = s.toString(); 
			int studentId = s.getId();
			for(Result r: valuesR){
				int resultId = r.getLinkId();
				if(studentId == resultId){
					data = data + r.toString();
					datalist.add(data);
					break;
				}
				else
					data = data + " no results"; 
					datalist.add(data);
					break;
			}
		}
	    // use the ArrayAdapter to show the data in a ListView
		ListView l = (ListView) findViewById(R.id.listView1);
	    adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datalist);
	    l.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results_wk1, menu);
		return true;
	}


}