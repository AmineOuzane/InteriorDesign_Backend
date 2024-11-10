package com.example.sketch20_backend.dto;

public class CanapeDTO {

    private String title;
    private String description;
    private String image;

    // Default constructor
    public CanapeDTO() {
    }

    // Parameterized constructor
    public CanapeDTO(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
