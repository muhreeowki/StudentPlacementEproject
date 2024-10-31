package com.example.studentplacement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private TextView tvLogin;
    private RadioGroup rgUserType;
    private Button btnLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etLoginUsername);
        etPassword = findViewById(R.id.etLoginPassword);
        rgUserType = findViewById(R.id.rgUserType);
        btnLogin = findViewById(R.id.btnLogin);
        tvLogin = findViewById(R.id.loginTextVeiw);

        rgUserType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbAdmin) {
                    tvLogin.setText(R.string.login_into_admin_account);
                } else if (checkedId == R.id.rbTPO) {
                    tvLogin.setText(R.string.login_into_tpo_account);
                } else if (checkedId == R.id.rbStudent) {
                    tvLogin.setText(R.string.login_into_student_account);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = rgUserType.getCheckedRadioButtonId();
        if (selectedId == R.id.rbAdmin) {
            // Hardcoded admin credentials for demo
            if (dbHelper.validateLogin(username, password, DatabaseHelper.TABLE_ADMIN)) {
                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                intent.putExtra("ADMIN_NAME", username);
                Toast.makeText(LoginActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid admin credentials", Toast.LENGTH_SHORT).show();
            }
        } else if (selectedId == R.id.rbTPO) {
            // Verify TPO credentials from database
            if (dbHelper.validateLogin(username, password, DatabaseHelper.TABLE_TPO)) {
                Intent intent = new Intent(LoginActivity.this, TPOActivity.class);
                intent.putExtra("TPO_NAME", username);
                Toast.makeText(LoginActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid TPO credentials", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Verify Student credentials from database
            if (dbHelper.validateLogin(username, password, DatabaseHelper.TABLE_STUDENT)) {
                Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                intent.putExtra("STUDENT_NAME", username);
                Toast.makeText(LoginActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid student credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }
}