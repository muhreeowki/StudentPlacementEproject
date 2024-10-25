package com.example.studentplacement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;


// StudentActivity.java - Main dashboard for students
public class StudentActivity extends AppCompatActivity {
    private Button btnViewCompanies, btnViewNotifications, btnViewPapers, btnViewSelected;
    private TextView tvWelcome;
    private String studentId, studentName;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        // Get student details from login intent
        studentId = getIntent().getStringExtra("student_id");
        studentName = getIntent().getStringExtra("student_name");

        dbHelper = new DatabaseHelper(this);
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        btnViewCompanies = findViewById(R.id.btnViewCompanies);
        btnViewNotifications = findViewById(R.id.btnViewNotifications);
        btnViewPapers = findViewById(R.id.btnViewPapers);
        btnViewSelected = findViewById(R.id.btnViewSelected);

        tvWelcome.setText("Welcome, " + studentName);
    }

    private void setupClickListeners() {
        btnViewCompanies.setOnClickListener(v -> startActivity(new Intent(StudentActivity.this, StudentViewCompaniesActivity.class)));
        btnViewNotifications.setOnClickListener(v -> startActivity(new Intent(StudentActivity.this, StudentViewNotificationsActivity.class)));
        // TODO: btnViewPapers.setOnClickListener(v -> startActivity(new Intent(StudentActivity.this, ViewPapersActivity.class)));
        // TODO: btnViewSelected.setOnClickListener(v -> startActivity(new Intent(StudentActivity.this, ViewSelectedActivity.class)));
    }
}