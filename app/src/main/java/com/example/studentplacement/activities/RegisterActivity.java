package com.example.studentplacement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUserId, etPassword;
    private Button btnRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        initializeViews();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegister();
            }
        });
    }

    private void initializeViews() {
        etUserId = findViewById(R.id.etRegisterId);
        etPassword = findViewById(R.id.etRegisterPassword);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void performRegister() {
        String name = etUserId.getText().toString();
        String password = etPassword.getText().toString();

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }

        // TODO: Create the user in db.
        try {
            boolean adminCreated = dbHelper.createAdminAccount(name, password);
            if (adminCreated) {
                Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, AdminActivity.class);
                intent.putExtra("ADMIN_NAME", name);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, "Failed to Register User", Toast.LENGTH_SHORT).show();
        }
    }
}