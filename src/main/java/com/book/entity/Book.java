package com.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id @GeneratedValue
    private Integer id;
    private String title;
    private String author;
    private String genre;
    private Integer price; 
    // Getters and Setters
}

