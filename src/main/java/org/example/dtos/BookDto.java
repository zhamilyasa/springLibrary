package org.example.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private String category;
    private String year;
    private String description;
    private String views;
    private String coverUrl;
    private String isbn;
    private String averageRating;
    private String pageCount;
}
