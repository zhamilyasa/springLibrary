package org.example.library.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
        private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "year")
    private String year;

    @Column(name = "description")
    private String description;

    @Column(name = "views")
    private String views;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "average_rating")
    private String averageRating;

    @Column(name = "page_count")
    private String pageCount;
}