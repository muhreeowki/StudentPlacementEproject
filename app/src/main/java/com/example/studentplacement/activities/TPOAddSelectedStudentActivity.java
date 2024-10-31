package com.example.studentplacement.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;;

public class TPOAddSelectedStudentActivity extends AppCompatActivity {
        private EditText etStudentId, etStudentName, etCompanyName, etPackage;
        private Button btnAdd;
        private DatabaseHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tpo_add_selected_student);

            dbHelper = new DatabaseHelper(this);
            etStudentId = findViewById(R.id.etStudentUsername);
            etStudentName = findViewById(R.id.etStudentName);
            etCompanyName = findViewById(R.id.etCompanyName);
            etPackage = findViewById(R.id.etPackage);
            btnAdd = findViewById(R.id.btnAdd);

            btnAdd.setOnClickListener(v -> addSelectedStudent());
        }

        private void addSelectedStudent() {
            String studentId = etStudentId.getText().toString();
            String studentName = etStudentName.getText().toString();
            String companyName = etCompanyName.getText().toString();
            double packageOffered = Double.parseDouble(etPackage.getText().toString());
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            long result = dbHelper.addSelectedStudent(studentId, studentName, companyName,
                    packageOffered, currentDate);
            if (result != -1) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error adding student", Toast.LENGTH_SHORT).show();
            }
        }
    }