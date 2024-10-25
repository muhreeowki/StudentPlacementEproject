package com.example.studentplacement.activities;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

// AddCompanyActivity.java
public class TPOAddCompanyActivity extends AppCompatActivity {
    private EditText etCompanyName, etJobDescription, etEligibilityCriteria, etPackage;
    private Button btnSubmit;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_add_company);

        dbHelper = new DatabaseHelper(this);
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        etCompanyName = findViewById(R.id.etCompanyName);
        etJobDescription = findViewById(R.id.etJobDescription);
        etEligibilityCriteria = findViewById(R.id.etEligibilityCriteria);
        etPackage = findViewById(R.id.etPackage);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupClickListeners() {
        btnSubmit.setOnClickListener(v -> {
            if (validateFields()) {
                saveCompanyDetails();
            }
        });
    }

    private boolean validateFields() {
        // Add validation logic
        return true;
    }

    private void saveCompanyDetails() {
        ContentValues values = new ContentValues();
        values.put("company_name", etCompanyName.getText().toString());
        values.put("job_description", etJobDescription.getText().toString());
        values.put("eligibility", etEligibilityCriteria.getText().toString());
        values.put("package", etPackage.getText().toString());

        long result = dbHelper.getWritableDatabase().insert("companies", null, values);
        if (result != -1) {
            Toast.makeText(this, "Company details added successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}