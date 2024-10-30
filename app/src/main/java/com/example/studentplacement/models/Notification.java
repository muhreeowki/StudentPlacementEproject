package com.example.studentplacement.models;

public class Notification {
    private int id;
    private String title;
    private String message;
    private String company;
    private String date;
    private boolean isRead;

    public Notification(int id, String title, String message, String company) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.company = company;
        this.isRead = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}

