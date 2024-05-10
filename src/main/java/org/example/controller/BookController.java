package org.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.dtos.BookDto;
import org.example.library.dtos.BookSearchDto;
import org.example.library.mapper.BookMapper;
import org.example.library.models.entity.Book;
import org.example.library.models.enums.ActionType;
import org.example.library.service.BookService;
import org.example.library.service.UserActionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;
    private final UserActionService userActionService;

    @GetMapping("/get/{id}")
    @Operation(summary = "get Book")
    public BookDto getBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return bookMapper.toDto(book);
    }

    @PostMapping("/create")
    @Operation(summary = "Create book")
    public void createBook(@Valid @RequestBody BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        bookService.createBook(book);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete")
    public void deleteBook(@PathVariable Long id) {
        log.info(" id {}", id);
        bookService.deleteBook(id);
    }

    @GetMapping("/books")
    @Operation(summary = "Get all books")
    public List<BookDto> getAllBooks() {
        return bookMapper.toDto(bookService.getAllBooks());
    }

    @GetMapping("/search")
    @Operation(summary = "search Book")
    public List<BookDto> searchBooks(@RequestBody BookSearchDto searchDto) {
        List<Book> filteredBooks = bookService.searchBooksByCriteria(searchDto);
        return filteredBooks.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/books/category/{category}")
    @Operation(summary = "search Cate")
    public List<BookDto> getAllByCategory(@PathVariable  String category) {
        var findAllCategory = bookService.getAllByCategory(category);
        List<BookDto> bookDtos = (List<BookDto>) bookMapper.toDto(findAllCategory);
        return bookDtos;
    }

    @GetMapping("/books/likes")
    public List<BookDto> getActionLikeBooks(@RequestParam Long userId, @RequestParam ActionType actionType) {
        List<Book> book = (List<Book>) userActionService.getActionType(userId, actionType);
        return bookMapper.toDto(book);
    }

    @PutMapping("/update")
    public void updateBook(@Valid @RequestBody BookDto bookDto) {
        bookService.updateBook(bookMapper.toEntity(bookDto));
    }
}
