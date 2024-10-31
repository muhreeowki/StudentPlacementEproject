package com.example.studentplacement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.R;

public class TPOActivity extends AppCompatActivity {
    private Button btnAddCompany, btnNotifications, btnPreviousPapers, btnSelectedStudents, btnLogout;

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
        btnLogout = findViewById(R.id.btnLogoutTpo);

    }

    private void setupClickListeners() {
        btnAddCompany.setOnClickListener(v -> startActivity(new Intent(TPOActivity.this, TPOAddCompanyActivity.class)));
        btnNotifications.setOnClickListener(v -> startActivity(new Intent(TPOActivity.this, TPONotificationsActivity.class)));
        btnPreviousPapers.setOnClickListener(v -> startActivity(new Intent(TPOActivity.this, TPOAddPastPaperActivity.class)));
        btnSelectedStudents.setOnClickListener(v -> startActivity(new Intent(TPOActivity.this, TPOAddSelectedStudentActivity.class)));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TPOActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}

