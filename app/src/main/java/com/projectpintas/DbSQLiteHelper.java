package com.projectpintas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*Create the MySQLiteHelper class. This class is responsible for creating 
the database. It also defines several constants for the table name
and the table columns.*/

public class DbSQLiteHelper extends SQLiteOpenHelper {
	//Database name and version
	private static final String DATABASE_NAME = "database.db";
	private static final int DATABASE_VERSION = 4;
	
	//Student table name and columns
	public static final String TABLE_STUDENTS = "students";
	public static final String COL_ID = "_id";
	public static final String COL_FIRSTNAME = "firstname";
	public static final String COL_SURNAME = "surname";
	
	//Login table name and columns
	public static final String TABLE_LOGIN = "login";
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";

	//Letter S table name and columns
	public static final String TABLE_LETTER_S = "letter_s";
	public static final String COL_LINK_SID = "link_id";
	public static final String COL_S_RESULT1 = "stage_1";
	public static final String COL_S_RESULT2 = "stage_2";

	//Letter A table name and columns
	public static final String TABLE_LETTER_A = "letter_a";
	public static final String COL_LINK_AID = "link_id";
	public static final String COL_A_RESULT1 = "stage_1";
	public static final String COL_A_RESULT2 = "stage_2";

    public static final String TABLE_LETTER_T = "letter_s";
    public static final String COL_LINK_TID = "link_id";
    public static final String COL_T_RESULT1 = "stage_1";
    public static final String COL_T_RESULT2 = "stage_2";

    //Letter A table name and columns
    public static final String TABLE_LETTER_P = "letter_a";
    public static final String COL_LINK_AIP = "link_id";
    public static final String COL_P_RESULT1 = "stage_1";
    public static final String COL_P_RESULT2 = "stage_2";

    public static final String TABLE_LETTER_I = "letter_s";
    public static final String COL_LINK_IID = "link_id";
    public static final String COL_I_RESULT1 = "stage_1";
    public static final String COL_I_RESULT2 = "stage_2";

    //Letter A table name and columns
    public static final String TABLE_LETTER_N = "letter_a";
    public static final String COL_LINK_NID = "link_id";
    public static final String COL_N_RESULT1 = "stage_1";
    public static final String COL_N_RESULT2 = "stage_2";

    // Create SQL database tables
	private static final String CREATE_TABLE_STUDENTS = "create table "+ 
											TABLE_STUDENTS + "(" + 
											COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
											COL_FIRSTNAME + " TEXT, " +
											COL_SURNAME + " TEXT" + ")";
	
	private static final String CREATE_TABLE_LETTER_S = "create table "+ 
											TABLE_LETTER_S + "(" + 
											COL_LINK_SID + " INTEGER PRIMARY KEY, " + 
											COL_S_RESULT1 + " TEXT, " +
											COL_S_RESULT2 + " TEXT " + ")";
	
	private static final String CREATE_TABLE_LETTER_A = "create table "+ 
											TABLE_LETTER_A + "(" + 
											COL_LINK_AID + " INTEGER PRIMARY KEY, " + 
											COL_A_RESULT1 + " TEXT, " +
											COL_A_RESULT2 + " TEXT " + ")";
    private static final String CREATE_TABLE_LETTER_T = "create table "+
                                            TABLE_LETTER_T + "(" +
                                            COL_LINK_TID + " INTEGER PRIMARY KEY, " +
                                            COL_T_RESULT1 + " TEXT, " +
                                            COL_T_RESULT2 + " TEXT " + ")";

    private static final String CREATE_TABLE_LETTER_P = "create table "+
                                            TABLE_LETTER_P + "(" +
                                            COL_LINK_TID + " INTEGER PRIMARY KEY, " +
                                            COL_P_RESULT1 + " TEXT, " +
                                            COL_P_RESULT2 + " TEXT " + ")";
    private static final String CREATE_TABLE_LETTER_I = "create table "+
                                            TABLE_LETTER_I + "(" +
                                            COL_LINK_IID + " INTEGER PRIMARY KEY, " +
                                            COL_I_RESULT1 + " TEXT, " +
                                            COL_I_RESULT2 + " TEXT " + ")";

    private static final String CREATE_TABLE_LETTER_N = "create table "+
                                            TABLE_LETTER_N + "(" +
                                            COL_LINK_NID + " INTEGER PRIMARY KEY, " +
                                            COL_N_RESULT1 + " TEXT, " +
                                            COL_N_RESULT2 + " TEXT " + ")";

    // Database table creation sql statement
	private static final String CREATE_TABLE_LOGIN = "create table "+ 
												TABLE_LOGIN + "(" + 
												COL_USERNAME + " TEXT PRIMARY KEY, " + 
												COL_PASSWORD + " TEXT " +")";
		
    
	public DbSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_STUDENTS);
		database.execSQL(CREATE_TABLE_LOGIN);
		database.execSQL(CREATE_TABLE_LETTER_S);
		database.execSQL(CREATE_TABLE_LETTER_A);
	}
    //The onUpgrade() method will delete all existing data
    //and re-create the table.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DbSQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LETTER_S);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LETTER_A);
		onCreate(db);
	}

} 