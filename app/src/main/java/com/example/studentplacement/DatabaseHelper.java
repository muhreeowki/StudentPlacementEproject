package com.example.studentplacement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PlacementSystem.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_TPO = "tpo";
    public static final String TABLE_STUDENT = "student";
    public static final String TABLE_COMPANY = "company";
    public static final String TABLE_NOTIFICATION = "notification";
    public static final String TABLE_SELECTED_STUDENT = "selected_student";

    // Common column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";

    // Student Table columns
    public static final String COLUMN_BRANCH = "branch";
    public static final String COLUMN_PERCENTAGE = "percentage";

    // Company Table columns
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_REQUIREMENTS = "requirements";

    // Create table statements
    private static final String CREATE_TABLE_TPO = "CREATE TABLE " + TABLE_TPO + "("
            + COLUMN_ID + " TEXT PRIMARY KEY,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "("
            + COLUMN_ID + " TEXT PRIMARY KEY,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_BRANCH + " TEXT,"
            + COLUMN_PERCENTAGE + " REAL" + ")";

    private static final String CREATE_TABLE_COMPANY = "CREATE TABLE " + TABLE_COMPANY + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_REQUIREMENTS + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TPO);
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_COMPANY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TPO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        onCreate(db);
    }
}