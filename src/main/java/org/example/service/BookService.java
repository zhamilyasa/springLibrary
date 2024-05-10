package org.example.library.service;

import org.example.library.dtos.BookSearchDto;
import org.example.library.models.entity.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long bookId);
    List<Book> getAllBooks();
    void createBook(Book book);
    void deleteBook(Long id);
    List<Book> searchBooksByCriteria(BookSearchDto searchDto);
    List<Book> getAllByCategory(String category);
    void  updateBook(Book book);
}
