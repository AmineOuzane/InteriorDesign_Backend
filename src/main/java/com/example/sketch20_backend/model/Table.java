package com.example.sketch20_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;

    @Lob
    private byte[] image;
}
