package com.example.studentplacement.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

public class AdminAddTPOActivity extends AppCompatActivity {
    private EditText etTpoName, etTpoPassword, etTpoId;
    private Button btnSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_tpo);

        initializeViews();
        setupDatabase();
        setupClickListeners();
    }

    private void initializeViews() {
        etTpoId = findViewById(R.id.etTpoId);
        etTpoName = findViewById(R.id.etTpoName);
        etTpoPassword = findViewById(R.id.etTpoPassword);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setupDatabase() {
        dbHelper = new DatabaseHelper(this);
    }

    private void setupClickListeners() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTpo();
            }
        });
    }

    private void saveTpo() {
        String tpoId = etTpoId.getText().toString().trim();
        String tpoName = etTpoName.getText().toString().trim();
        String tpoPassword = etTpoPassword.getText().toString().trim();

        if (validateInput(tpoName, tpoPassword)) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_ID, tpoId);
            values.put(DatabaseHelper.COLUMN_NAME, tpoName);
            values.put(DatabaseHelper.COLUMN_PASSWORD, tpoPassword);

            long result = db.insert(DatabaseHelper.TABLE_TPO, null, values);
            if (result != -1) {
                Toast.makeText(this, "TPO added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add TPO", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateInput(String tpoName, String tpoPassword) {
        if (tpoName.isEmpty()) {
            etTpoName.setError("TPO Name is required");
            return false;
        }
        if (tpoPassword.isEmpty()) {
            etTpoPassword.setError("Password is required");
            return false;
        }
        return true;
    }
}