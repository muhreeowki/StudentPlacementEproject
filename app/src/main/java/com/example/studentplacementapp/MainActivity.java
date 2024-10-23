package com.example.studentplacementapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    // UI Elements
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    public DatabaseHelper databaseHelper;

    // Database Helper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Database Helper
        databaseHelper = new DatabaseHelper(this);

        // Initialize Views
        //  initViews();

        // Set Click Listeners
        // setClickListeners();
    }

    /**
     * Initialize all UI elements from layout
     */
    private void initViews() {
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        editTextEmail = findViewById(R.id.editTextPassword);
        editTextPassword = findViewById(R.id.editTextEmail);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
    }

    /**
     * Set click listeners for buttons
     */
    private void setClickListeners() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegister();
            }
        });
    }

    /**
     * Handle login button click
     */
    private void performLogin() {
        if (validateInputs()) {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            // Check user exists
            if (databaseHelper.checkUser(email, password)) {
                // Login Success
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                clearInputs();

                // TODO: Navigate to home screen or dashboard
                // Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                // startActivity(intent);
                // finish();
            } else {
                // Login Failed
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Handle register button click
     */
    private void performRegister() {
        if (validateInputs()) {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            // Check if user already exists
            if (!databaseHelper.checkUser(email)) {
                // Create new user
                User user = new User(null, email, password);

                if (databaseHelper.addUser(user)) {
                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    clearInputs();
                } else {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                textInputLayoutEmail.setError("User already exists");
            }
        }
    }

    /**
     * Validate input fields
     */
    private boolean validateInputs() {
        boolean isValid = true;

        // Reset errors
        textInputLayoutEmail.setError(null);
        textInputLayoutPassword.setError(null);

        // Get values
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate Email
        if (TextUtils.isEmpty(email)) {
            textInputLayoutEmail.setError("Please enter email");
            isValid = false;
        }

        // Validate Password
        if (TextUtils.isEmpty(password)) {
            textInputLayoutPassword.setError("Please enter password");
            isValid = false;
        } else if (password.length() < 6) {
            textInputLayoutPassword.setError("Password must be at least 6 characters");
            isValid = false;
        }

        return isValid;
    }

    /**
     * Clear input fields
     */
    private void clearInputs() {
        editTextEmail.setText(null);
        editTextPassword.setText(null);
        textInputLayoutEmail.setError(null);
        textInputLayoutPassword.setError(null);
    }

    /**
     * Show error message
     */
    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close database connection
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    /**
     * Optional: Add these lifecycle methods if needed
     */
    @Override
    protected void onPause() {
        super.onPause();
        clearInputs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Add any resume state logic here
    }
}