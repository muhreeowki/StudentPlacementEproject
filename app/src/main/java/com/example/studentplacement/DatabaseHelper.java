package com.example.studentplacement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PlacementSystem.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_ADMIN = "admin";
    public static final String TABLE_TPO = "tpo";
    public static final String TABLE_STUDENT = "student";
    public static final String TABLE_COMPANY = "company";
    public static final String TABLE_NOTIFICATION = "notification";
    public static final String TABLE_SELECTED_STUDENT = "selected_student";

    // Common column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ADMIN_ID = "adminID";

    // Student Table columns
    public static final String COLUMN_BRANCH = "branch";
    public static final String COLUMN_PERCENTAGE = "percentage";

    // Company Table columns
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_REQUIREMENTS = "requirements";

    // Create table statements
    private static final String CREATE_TABLE_ADMIN = "CREATE TABLE " + TABLE_ADMIN + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
            + COLUMN_NAME + " TEXT NOT NULL UNIQUE,"
            + COLUMN_PASSWORD + " TEXT NOT NULL" + ")";

    private static final String CREATE_TABLE_TPO = "CREATE TABLE " + TABLE_TPO + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT NOT NULL,"
            + COLUMN_PASSWORD + " TEXT NOT NULL" + ")";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY UNIQUE,"
            + COLUMN_NAME + " TEXT NOT NULL,"
            + COLUMN_PASSWORD + " TEXT NOT NULL,"
            + COLUMN_BRANCH + " TEXT,"
            + COLUMN_PERCENTAGE + " REAL" + ")";

    private static final String CREATE_TABLE_COMPANY = "CREATE TABLE " + TABLE_COMPANY + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_REQUIREMENTS + " TEXT" + ")";

    private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE " + TABLE_NOTIFICATION + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "title TEXT,"
            + "message TEXT,"
            + "company TEXT" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_TPO);
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_NOTIFICATION);
        db.execSQL(CREATE_TABLE_COMPANY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TPO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        onCreate(db);
    }

    // Method to create an admin account
    public boolean createAdminAccount(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD, password);

        // Insert row
        long result = db.insert(TABLE_ADMIN, null, values);
        db.close();

        // Return true if inserted successfully
        return result != -1;
    }

    // Method to create a TPO account
    public boolean createTPOAccount(String id, String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD, password);

        // Insert row
        long result = db.insert(TABLE_TPO, null, values);
        db.close();

        // Return true if inserted successfully
        return result != -1;
    }

    // Method to create a student account
    public boolean createStudentAccount(String id, String name, String password,
                                        String branch, double percentage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_BRANCH, branch);
        values.put(COLUMN_PERCENTAGE, percentage);

        // Insert row
        long result = db.insert(TABLE_STUDENT, null, values);
        db.close();

        // Return true if inserted successfully
        return result != -1;
    }

    // Method to create a company entry
    public boolean createCompany(String name, String description, String requirements) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_REQUIREMENTS, requirements);

        // Insert row
        long result = db.insert(TABLE_COMPANY, null, values);
        db.close();

        // Return true if inserted successfully
        return result != -1;
    }

    // Helper method to check if ID exists in a specific table
    public boolean isIDExists(String id, String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName,
                new String[]{COLUMN_ID},
                COLUMN_ID + "=?",
                new String[]{id},
                null, null, null);

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Method to validate login credentials
    public boolean validateLogin(String name, String password, String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName,
                new String[]{COLUMN_NAME},
                COLUMN_NAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{name, password},
                null, null, null);

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }
}