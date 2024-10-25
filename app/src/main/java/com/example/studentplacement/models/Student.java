package com.example.studentplacement.models;

public class Student extends User {
    private String branch;
    private double percentage;

    public Student(String id, String name, String password, String branch, double percentage) {
        super(id, name, password);
        this.branch = branch;
        this.percentage = percentage;
    }

    // Additional getters and setters
    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
}
