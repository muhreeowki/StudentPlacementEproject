package com.example.studentplacement.models;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<Notification> notifications;
    private Context context;
    private DatabaseHelper dbHelper;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
        this.dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);

        // Set notification data to views
        holder.tvNotificationTitle.setText(notification.getTitle());
        holder.tvCompanyName.setText(notification.getCompany());
        holder.tvNotificationMessage.setText(notification.getMessage());
        holder.tvNotificationDate.setText(notification.getDate());

        // Change background color based on read status
        if (!notification.isRead()) {
            holder.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(context, R.color.unread_notification));
        } else {
            holder.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(context, R.color.read_notification));
        }

        // Handle click events
        holder.cardView.setOnClickListener(v -> {
            // Mark notification as read
            if (!notification.isRead()) {
                notification.setRead(true);
                updateNotificationReadStatus(notification.getId());
                notifyItemChanged(position);
            }

            // Show notification details in a dialog
            showNotificationDialog(notification);
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    private void updateNotificationReadStatus(int notificationId) {
        ContentValues values = new ContentValues();
        values.put("is_read", 1);
        dbHelper.getWritableDatabase().update("notifications",
                values,
                "id = ?",
                new String[]{String.valueOf(notificationId)});
    }

    private void showNotificationDialog(Notification notification) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context)
                .setTitle(notification.getTitle())
                .setMessage(notification.getMessage())
                .setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // ViewHolder class
    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView tvNotificationTitle;
        TextView tvCompanyName;
        TextView tvNotificationMessage;
        TextView tvNotificationDate;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (MaterialCardView) itemView;
            tvNotificationTitle = itemView.findViewById(R.id.tvNotificationTitle);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvNotificationMessage = itemView.findViewById(R.id.tvNotificationMessage);
            tvNotificationDate = itemView.findViewById(R.id.tvNotificationDate);
        }
    }

    // Method to update notifications list
    public void updateNotifications(List<Notification> newNotifications) {
        this.notifications = newNotifications;
        notifyDataSetChanged();
    }

    // Method to add a single notification
    public void addNotification(Notification notification) {
        notifications.add(0, notification);
        notifyItemInserted(0);
    }

    // Method to clear all notifications
    public void clearNotifications() {
        int size = notifications.size();
        notifications.clear();
        notifyItemRangeRemoved(0, size);
    }
}