package com.example.studentplacement.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

import java.util.ArrayList;

import com.example.studentplacement.models.PastPaper;
import com.example.studentplacement.models.PastPaperAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewPastPapersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PastPaperAdapter adapter;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fabAdd;
    private boolean isTpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_papers);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);

        // Check if user is TPO
        isTpo = getIntent().getBooleanExtra("is_tpo", false);

        // Only show FAB for TPO
        fabAdd.setVisibility(isTpo ? View.VISIBLE : View.GONE);

        setupRecyclerView();

        if (isTpo) {
            fabAdd.setOnClickListener(v -> {
                Intent intent = new Intent(this, TPOAddPastPaperActivity.class);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPastPapers();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void loadPastPapers() {
        ArrayList<PastPaper> papersList = dbHelper.getAllPastPapers();
        adapter = new PastPaperAdapter(this, papersList);
        recyclerView.setAdapter(adapter);
    }
}