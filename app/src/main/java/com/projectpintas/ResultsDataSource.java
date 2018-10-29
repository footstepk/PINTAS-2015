package com.projectpintas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ResultsDataSource {
	// Database fields
	private SQLiteDatabase database;
	private DbSQLiteHelper dbHelper;
	
	private String[] allS = { DbSQLiteHelper.COL_LINK_SID,
									DbSQLiteHelper.COL_S_RESULT1, 
									DbSQLiteHelper.COL_S_RESULT2, 
									};
	
	@SuppressWarnings("unused")
	private String[] allA = { DbSQLiteHelper.COL_LINK_AID,
									DbSQLiteHelper.COL_A_RESULT1, 
									DbSQLiteHelper.COL_A_RESULT2, 
									};
	
	public ResultsDataSource(Context context) {
		dbHelper = new DbSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	public void addResult(Result result) {
		Log.d("addResult", result.toString());
		//create ContentValues to add key "COL"/value
		ContentValues values = new ContentValues();
		values.put(DbSQLiteHelper.COL_LINK_SID, result.getLinkId());  
		values.put(DbSQLiteHelper.COL_S_RESULT1, result.getResult1()); 
		values.put(DbSQLiteHelper.COL_S_RESULT2, result.getResult2()); 

		database.insert(DbSQLiteHelper.TABLE_LETTER_S, null, values);
	}
	
	public List<Result> getAllResults() {
		List<Result> results = new ArrayList<Result>();

		Cursor cursor = database.query(DbSQLiteHelper.TABLE_LETTER_S,
						allS, null, null, null, null, null);

		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			Result result = cursorToResult(cursor);
			results.add(result);
			cursor.moveToNext();
		}
		//close the cursor
		cursor.close();
		return results;
	}


	private Result cursorToResult(Cursor cursor) {
		Result result = new Result();
		result.setLinkId(cursor.getInt(0));
		result.setResult1(cursor.getString(1));
		result.setResult2(cursor.getString(2));
		return result;
	}

}
