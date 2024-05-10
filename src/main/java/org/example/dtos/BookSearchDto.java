package org.example.dtos;

import lombok.Data;

@Data
public class BookSearchDto {
    private String title;
    private String author;
    private String category;
    private String year;
}

