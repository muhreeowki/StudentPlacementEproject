package com.example.studentplacement.activities;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;
import com.example.studentplacement.models.Company;
import com.example.studentplacement.models.Notification;
import com.example.studentplacement.models.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

// ViewNotificationsActivity.java
public class StudentViewNotificationsActivity extends AppCompatActivity {
    private RecyclerView rvNotifications;
    private DatabaseHelper dbHelper;
    private NotificationAdapter adapter;
    private List<Notification> notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_notifications);

        dbHelper = new DatabaseHelper(this);
        initializeViews();
        loadNotifications();
    }

    private void initializeViews() {
        rvNotifications = findViewById(R.id.rvNotifications);
        rvNotifications.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadNotifications() {
        notifications = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(DatabaseHelper.TABLE_NOTIFICATION,
                null, null, null, null, null, "id DESC");

        while(cursor.moveToNext()) {
            try {
                Notification notification = new Notification(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("message")),
                        cursor.getString(cursor.getColumnIndexOrThrow("company"))
                );
                notifications.add(notification);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load notifications: " + e);
            }
        }
        cursor.close();

        adapter = new NotificationAdapter(this, notifications);
        rvNotifications.setAdapter(adapter);
    }
}