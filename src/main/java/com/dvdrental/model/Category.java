package com.dvdrental.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private long categoryID;
    private String name;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }
}
