package com.example.studentplacement.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

// AddStudentActivity.java
public class AdminAddStudentActivity extends AppCompatActivity {
    private EditText etStudentName, etStudentPassword, etBranch, etPercentage;
    private Button btnSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_student);

        initializeViews();
        setupDatabase();
        setupClickListeners();
    }

    private void initializeViews() {
        etStudentName = findViewById(R.id.etStudentName);
        etStudentPassword = findViewById(R.id.etStudentPassword);
        etBranch = findViewById(R.id.etBranch);
        etPercentage = findViewById(R.id.etPercentage);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setupDatabase() {
        dbHelper = new DatabaseHelper(this);
    }

    private void setupClickListeners() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });
    }

    private void saveStudent() {
        String studentName = etStudentName.getText().toString().trim();
        String studentPassword = etStudentPassword.getText().toString().trim();
        String branch = etBranch.getText().toString().trim();
        String percentageStr = etPercentage.getText().toString().trim();

        if (validateInput(studentName, studentPassword, branch, percentageStr)) {
            double percentage = Double.parseDouble(percentageStr);

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_NAME, studentName);
            values.put(DatabaseHelper.COLUMN_PASSWORD, studentPassword);
            values.put(DatabaseHelper.COLUMN_BRANCH, branch);
            values.put(DatabaseHelper.COLUMN_PERCENTAGE, percentage);

            long result = db.insert(DatabaseHelper.TABLE_STUDENT, null, values);
            if (result != -1) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateInput(String studentName, String studentPassword,
                                  String branch, String percentageStr) {
        if (studentName.isEmpty()) {
            etStudentName.setError("Student Name is required");
            return false;
        }
        if (studentPassword.isEmpty()) {
            etStudentPassword.setError("Password is required");
            return false;
        }
        if (branch.isEmpty()) {
            etBranch.setError("Branch is required");
            return false;
        }
        if (percentageStr.isEmpty()) {
            etPercentage.setError("Percentage is required");
            return false;
        }
        try {
            double percentage = Double.parseDouble(percentageStr);
            if (percentage < 0 || percentage > 100) {
                etPercentage.setError("Invalid percentage");
                return false;
            }
        } catch (NumberFormatException e) {
            etPercentage.setError("Invalid percentage format");
            return false;
        }
        return true;
    }
}