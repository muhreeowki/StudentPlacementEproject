package com.example.studentplacement.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

import java.util.ArrayList;

// NotificationsActivity.java
public class TPONotificationsActivity extends AppCompatActivity {
    private EditText etNotificationTitle, etNotificationMessage;
    private Spinner spinnerCompany;
    private Button btnSendNotification;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_notifications);

        dbHelper = new DatabaseHelper(this);
        initializeViews();
        loadCompanies();
        setupClickListeners();
    }

    private void initializeViews() {
        etNotificationTitle = findViewById(R.id.etNotificationTitle);
        etNotificationMessage = findViewById(R.id.etNotificationMessage);
        spinnerCompany = findViewById(R.id.spinnerCompany);
        btnSendNotification = findViewById(R.id.btnSendNotification);
    }

    private void loadCompanies() {
        // Load companies from database into spinner
        Cursor cursor = dbHelper.getReadableDatabase().query("company",
                new String[]{"name"}, null, null, null, null, null);

        ArrayList<String> companies = new ArrayList<>();
        while(cursor.moveToNext()) {
            companies.add(cursor.getString(0));
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, companies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCompany.setAdapter(adapter);
    }

    private void setupClickListeners() {
        btnSendNotification.setOnClickListener(v -> {
            if (validateFields()) {
                sendNotification();
            }
        });
    }

    private boolean validateFields() {
        // Add validation logic
        return true;
    }

    private void sendNotification() {
        ContentValues values = new ContentValues();
        values.put("title", etNotificationTitle.getText().toString());
        values.put("message", etNotificationMessage.getText().toString());
        values.put("company", spinnerCompany.getSelectedItem().toString());

        long result = dbHelper.getWritableDatabase().insert(DatabaseHelper.TABLE_NOTIFICATION, null, values);
        if (result != -1) {
            Toast.makeText(this, "Notification sent successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
