package com.example.studentplacement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.R;

import java.util.Objects;


public class AdminActivity extends AppCompatActivity {
    private Button btnAddTpo, btnAddStudent, btnLogout;
    private TextView tvAdminWelcome;
    private String adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        Intent intent = this.getIntent();
        adminName = intent.getStringExtra("ADMIN_NAME");

        btnAddTpo = findViewById(R.id.btnAddTpo);
        tvAdminWelcome = findViewById(R.id.tvAdminWelcome);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnLogout = findViewById(R.id.btnLogout);

        tvAdminWelcome.setText(String.format("Welcome %s !", adminName));
    }

    private void setupClickListeners() {
        btnAddTpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, AdminAddTPOActivity.class));
            }
        });

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, AdminAddStudentActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}