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
    private Button btnViewCompanies, btnViewNotifications, btnViewPapers, btnViewSelected, btnLogout;
    private String studentId, studentName;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        dbHelper = new DatabaseHelper(this);
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        btnViewCompanies = findViewById(R.id.btnViewCompanies);
        btnViewNotifications = findViewById(R.id.btnViewNotifications);
        btnViewPapers = findViewById(R.id.btnViewPapers);
        btnViewSelected = findViewById(R.id.btnViewSelected);
        btnLogout = findViewById(R.id.btnLogoutStudent);
    }

    private void setupClickListeners() {
        btnViewCompanies.setOnClickListener(v -> startActivity(new Intent(StudentActivity.this, StudentViewCompaniesActivity.class)));
        btnViewNotifications.setOnClickListener(v -> startActivity(new Intent(StudentActivity.this, StudentViewNotificationsActivity.class)));
        btnViewPapers.setOnClickListener(v -> startActivity(new Intent(StudentActivity.this, ViewPastPapersActivity.class)));
        btnViewSelected.setOnClickListener(v -> startActivity(new Intent(StudentActivity.this, ViewSelectedStudentsActivity.class)));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}