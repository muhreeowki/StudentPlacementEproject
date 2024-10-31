package com.example.studentplacement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;
import com.example.studentplacement.models.SelectedStudent;
import com.example.studentplacement.models.SelectedStudentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewSelectedStudentsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SelectedStudentAdapter adapter;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fabAdd;
    private boolean isTpo;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_selected_students);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);
        searchView = findViewById(R.id.searchView);

        isTpo = getIntent().getBooleanExtra("is_tpo", false);
        fabAdd.setVisibility(isTpo ? View.VISIBLE : View.GONE);

        setupRecyclerView();
        setupSearchView();

        if (isTpo) {
            fabAdd.setOnClickListener(v -> {
                Intent intent = new Intent(this, TPOAddSelectedStudentActivity.class);
                startActivity(intent);
            });
        }
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterStudents(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterStudents(newText);
                return true;
            }
        });
    }

    private void filterStudents(String query) {
        ArrayList<SelectedStudent> allStudents = dbHelper.getAllSelectedStudents();
        ArrayList<SelectedStudent> filteredList = new ArrayList<>();

        for (SelectedStudent student : allStudents) {
            if (student.getStudentName().toLowerCase().contains(query.toLowerCase()) ||
                    student.getCompanyName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(student);
            }
        }

        adapter = new SelectedStudentAdapter(filteredList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSelectedStudents();
    }

    private void loadSelectedStudents() {
        ArrayList<SelectedStudent> studentsList = dbHelper.getAllSelectedStudents();
        adapter = new SelectedStudentAdapter(studentsList);
        recyclerView.setAdapter(adapter);
    }
}