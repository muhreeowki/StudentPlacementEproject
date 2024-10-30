package com.example.studentplacement.activities;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;
import com.example.studentplacement.models.Company;
import com.example.studentplacement.models.CompanyAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentViewCompaniesActivity extends AppCompatActivity {
    private RecyclerView rvCompanies;
    private DatabaseHelper dbHelper;
    private CompanyAdapter adapter;
    private List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_companies);

        dbHelper = new DatabaseHelper(this);
        initializeViews();
        loadCompanies();
    }

    private void initializeViews() {
        rvCompanies = findViewById(R.id.rvCompanies);
        rvCompanies.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadCompanies() {
        companies = new ArrayList<Company>();
        Cursor cursor = dbHelper.getReadableDatabase().query(DatabaseHelper.TABLE_COMPANY, null, null, null, null, null, "name ASC");

        while (cursor.moveToNext()) {
            try {
                Company company = new Company(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getString(cursor.getColumnIndexOrThrow("requirements")));
                companies.add(company);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load companies: " + e);
            }
        }
        cursor.close();

        adapter = new CompanyAdapter(companies);
        rvCompanies.setAdapter(adapter);
    }
}
