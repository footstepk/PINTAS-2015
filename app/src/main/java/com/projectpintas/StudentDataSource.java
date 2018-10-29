package com.projectpintas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*
/*Create the CommentsDataSource class. This class is our DAO. 
It maintains the database connection and supports adding new comments 
and fetching all comments.*/

public class StudentDataSource {

	// Database fields
	private SQLiteDatabase database;
	private DbSQLiteHelper dbHelper;
	private String[] allColumns = { DbSQLiteHelper.COL_ID,
									DbSQLiteHelper.COL_FIRSTNAME, 
									DbSQLiteHelper.COL_SURNAME };

	public StudentDataSource(Context context) {
		dbHelper = new DbSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Student createStudent(Student student) {
		Log.d("addStudent", student.toString());
		// Create ContentValues to add key "COL"/value
		ContentValues values = new ContentValues();
		values.put(DbSQLiteHelper.COL_FIRSTNAME, student.getFirstName()); // get firstName 
		values.put(DbSQLiteHelper.COL_SURNAME, student.getLastName()); // get lastName

		long insertId = database.insert(DbSQLiteHelper.TABLE_STUDENTS, null, values);
		Cursor cursor = database.query(DbSQLiteHelper.TABLE_STUDENTS,
						allColumns, DbSQLiteHelper.COL_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Student newStudent = cursorToStudent(cursor);
		cursor.close();
		return newStudent;
	}

	public void deleteStudent(Student student) {
		int id = student.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(DbSQLiteHelper.TABLE_STUDENTS, DbSQLiteHelper.COL_ID + " = " + id, null);
	}
    public Student getStudent(int id){
    	  
        //Build query
        Cursor cursor = 
                database.query(DbSQLiteHelper.TABLE_STUDENTS, // a. table
                allColumns, // b. COL names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        // Get first result
        if (cursor != null)
            cursor.moveToFirst();
 
        // Build the student object
        Student student = new Student();
        student.setId(Integer.parseInt(cursor.getString(0)));
        student.setFirstName(cursor.getString(1));
        student.setLastName(cursor.getString(2));
 
        Log.d("getStudent("+id+")", student.toString());
 
        //Return student object
        cursor.close();
        return student;
    }
    //Return a list of all the students
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();

		Cursor cursor = database.query(DbSQLiteHelper.TABLE_STUDENTS,
						allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			Student student = cursorToStudent(cursor);
			students.add(student);
			cursor.moveToNext();
		}
		//close the cursor
		cursor.close();
		return students;
	}

	private Student cursorToStudent(Cursor cursor) {
		Student student = new Student();
		student.setId(cursor.getInt(0));
		student.setFirstName(cursor.getString(1));
		student.setLastName(cursor.getString(2));
		return student;
	}
} 
