package com.example.sketch20_backend.model;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "habillage")
public class Habillage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;

    @Lob
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
