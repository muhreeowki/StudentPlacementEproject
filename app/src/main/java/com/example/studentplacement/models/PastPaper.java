package com.example.studentplacement.models;

public class PastPaper {
    private int id;
    private String companyName;
    private int year;
    private String paperUrl;
    private String paperType;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getPaperUrl() { return paperUrl; }
    public void setPaperUrl(String paperUrl) { this.paperUrl = paperUrl; }
    public String getPaperType() { return paperType; }
    public void setPaperType(String paperType) { this.paperType = paperType; }
}
