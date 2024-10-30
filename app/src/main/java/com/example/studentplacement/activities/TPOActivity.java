package com.example.studentplacement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.R;

public class TPOActivity extends AppCompatActivity {
    private Button btnAddCompany, btnNotifications, btnPreviousPapers, btnSelectedStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {

        btnAddCompany = findViewById(R.id.btnAddCompany);
        btnNotifications = findViewById(R.id.btnNotifications);
        btnPreviousPapers = findViewById(R.id.btnPreviousPapers);
        btnSelectedStudents = findViewById(R.id.btnSelectedStudents);

    }

    private void setupClickListeners() {
        btnAddCompany.setOnClickListener(v -> startActivity(new Intent(TPOActivity.this, TPOAddCompanyActivity.class)));
        btnNotifications.setOnClickListener(v -> startActivity(new Intent(TPOActivity.this, TPONotificationsActivity.class)));
        // TODO: btnPreviousPapers.setOnClickListener(v -> startActivity(new Intent(TPOActivity.this, PreviousPapersActivity.class)));
        // TODO: btnSelectedStudents.setOnClickListener(v -> startActivity(new Intent(TPOActivity.this, SelectedStudentsActivity.class)));
    }
}

