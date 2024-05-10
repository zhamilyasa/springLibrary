package org.example.library.mapper;

import org.example.library.dtos.BookDto;
import org.example.library.models.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDto, Book>{
    BookDto toDto(Book entity);
    Book toEntity(BookDto dto);
    List<Book> toEntity(List<BookDto> dtoList);
    List<BookDto> toDto(List<Book> entityList);
}