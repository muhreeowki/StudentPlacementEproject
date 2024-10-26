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

public class LoginActivity extends AppCompatActivity {
    private EditText etName, etPassword;
    private RadioGroup rgUserType;
    private Button btnLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etLoginId);
        etPassword = findViewById(R.id.etLoginPassword);
        rgUserType = findViewById(R.id.rgUserType);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = rgUserType.getCheckedRadioButtonId();
        if (selectedId == R.id.rbAdmin) {
            // Hardcoded admin credentials for demo
            if (dbHelper.validateLogin(name, password, "admin")) {
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                intent.putExtra("ADMIN_NAME", name);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid admin credentials", Toast.LENGTH_SHORT).show();
            }
        } else if (selectedId == R.id.rbTPO) {
            // Verify TPO credentials from database
            if (verifyTPOLogin(name, password)) {
                Intent intent = new Intent(LoginActivity.this, TPOActivity.class);
                intent.putExtra("TPO_ID", name);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid TPO credentials", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Verify Student credentials from database
            if (verifyStudentLogin(name, password)) {
                Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                intent.putExtra("STUDENT_ID", name);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid student credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean verifyTPOLogin(String userId, String password) {
        // Add database verification logic here
        return true; // Temporary return for demo
    }

    private boolean verifyStudentLogin(String userId, String password) {
        // Add database verification logic here
        return true; // Temporary return for demo
    }
}