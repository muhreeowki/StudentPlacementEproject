package com.example.studentplacement.models;

public class Company {
    private int id;
    private String name;
    private String description;
    private String requirements;

    public Company(int id, String name, String description, String requirements) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requirements = requirements;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }
}
