package com.example.studentplacement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studentplacement.models.PastPaper;
import com.example.studentplacement.models.SelectedStudent;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PlacementSystem.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_ADMIN = "admin";
    public static final String TABLE_TPO = "tpo";
    public static final String TABLE_STUDENT = "student";
    public static final String TABLE_COMPANY = "company";
    public static final String TABLE_NOTIFICATION = "notification";
    public static final String TABLE_PAST_PAPERS = "past_papers";
    public static final String TABLE_SELECTED_STUDENTS = "selected_students";

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

    // Past Papers table columns
    private static final String COLUMN_PAPER_ID = "paper_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";
    private static final String COLUMN_PAPER_YEAR = "year";
    private static final String COLUMN_PAPER_URL = "paper_url";
    private static final String COLUMN_PAPER_TYPE = "paper_type";

    // Selected Students table columns
    private static final String COLUMN_SELECTION_ID = "selection_id";
    private static final String COLUMN_STUDENT_ID = "student_id";
    private static final String COLUMN_STUDENT_NAME = "student_name";
    private static final String COLUMN_PACKAGE = "package_offered";
    private static final String COLUMN_SELECTION_DATE = "selection_date";


    // Create table statements
    private static final String CREATE_TABLE_ADMIN = "CREATE TABLE " + TABLE_ADMIN + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT NOT NULL UNIQUE,"
            + COLUMN_PASSWORD + " TEXT NOT NULL" + ")";

    private static final String CREATE_TABLE_TPO = "CREATE TABLE " + TABLE_TPO + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL,"
            + COLUMN_NAME + " TEXT NOT NULL,"
            + COLUMN_PASSWORD + " TEXT NOT NULL" + ")";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
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

    private static final String CREATE_PAST_PAPERS_TABLE = "CREATE TABLE " + TABLE_PAST_PAPERS + "("
            + COLUMN_PAPER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_COMPANY_NAME + " TEXT,"
            + COLUMN_PAPER_YEAR + " INTEGER,"
            + COLUMN_PAPER_URL + " TEXT,"
            + COLUMN_PAPER_TYPE + " TEXT"
            + ")";

    private static final String CREATE_SELECTED_STUDENTS_TABLE = "CREATE TABLE " + TABLE_SELECTED_STUDENTS + "("
            + COLUMN_SELECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_ID + " TEXT,"
            + COLUMN_STUDENT_NAME + " TEXT,"
            + COLUMN_COMPANY_NAME + " TEXT,"
            + COLUMN_PACKAGE + " REAL,"
            + COLUMN_SELECTION_DATE + " TEXT"
            + ")";


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
        db.execSQL(CREATE_PAST_PAPERS_TABLE);
        db.execSQL(CREATE_SELECTED_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TPO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAST_PAPERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELECTED_STUDENTS);
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
    public boolean validateLogin(String nameOrId, String password, String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        if (tableName == DatabaseHelper.TABLE_ADMIN) {
            cursor = db.query(tableName,
                    new String[]{COLUMN_NAME},
                    COLUMN_NAME + "=? AND " + COLUMN_PASSWORD + "=?",
                    new String[]{nameOrId, password},
                    null, null, null);
        } else {
         cursor = db.query(tableName,
                new String[]{COLUMN_ID},
                COLUMN_ID + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{nameOrId, password},
                null, null, null);
        }

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }

    // Methods to add past papers
    public long addPastPaper(String companyName, int year, String paperUrl, String paperType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANY_NAME, companyName);
        values.put(COLUMN_PAPER_YEAR, year);
        values.put(COLUMN_PAPER_URL, paperUrl);
        values.put(COLUMN_PAPER_TYPE, paperType);
        return db.insert(TABLE_PAST_PAPERS, null, values);
    }

    // Method to add selected student
    public long addSelectedStudent(String studentId, String studentName, String companyName,
                                   double packageOffered, String selectionDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_ID, studentId);
        values.put(COLUMN_STUDENT_NAME, studentName);
        values.put(COLUMN_COMPANY_NAME, companyName);
        values.put(COLUMN_PACKAGE, packageOffered);
        values.put(COLUMN_SELECTION_DATE, selectionDate);
        return db.insert(TABLE_SELECTED_STUDENTS, null, values);
    }

    // Method to get all past papers
    public ArrayList<PastPaper> getAllPastPapers() {
        ArrayList<PastPaper> papersList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PAST_PAPERS + " ORDER BY " + COLUMN_PAPER_YEAR + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PastPaper paper = new PastPaper();
                paper.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PAPER_ID)));
                paper.setCompanyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPANY_NAME)));
                paper.setYear(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PAPER_YEAR)));
                paper.setPaperUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAPER_URL)));
                paper.setPaperType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAPER_TYPE)));
                papersList.add(paper);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return papersList;
    }

    // Method to get all selected students
    public ArrayList<SelectedStudent> getAllSelectedStudents() {
        ArrayList<SelectedStudent> selectedStudents = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SELECTED_STUDENTS +
                " ORDER BY " + COLUMN_SELECTION_DATE + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SelectedStudent student = new SelectedStudent();
                student.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SELECTION_ID)));
                student.setStudentId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_ID)));
                student.setStudentName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_NAME)));
                student.setCompanyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPANY_NAME)));
                student.setPackageOffered(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PACKAGE)));
                student.setSelectionDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SELECTION_DATE)));
                selectedStudents.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return selectedStudents;
    }
}