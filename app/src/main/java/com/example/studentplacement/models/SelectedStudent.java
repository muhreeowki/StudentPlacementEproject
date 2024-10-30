package com.example.studentplacement.models;

// Model class for Selected Students
public class SelectedStudent {
    private int id;
    private String studentId;
    private String studentName;
    private String companyName;
    private double packageOffered;
    private String selectionDate;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public double getPackageOffered() { return packageOffered; }
    public void setPackageOffered(double packageOffered) { this.packageOffered = packageOffered; }
    public String getSelectionDate() { return selectionDate; }
    public void setSelectionDate(String selectionDate) { this.selectionDate = selectionDate; }
}
