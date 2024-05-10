package org.example.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.library.dtos.BookSearchDto;
import org.example.library.models.entity.Book;
import org.example.library.repasitory.BookRepository;
import org.example.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if(id != null) {
            bookRepository.deleteBookAndActions(id);
        }else {
            bookRepository.deleteAll();
        }
    }


    @Override
    public List<Book> searchBooksByCriteria(BookSearchDto searchDto) {
        String author = searchDto.getAuthor();
        String title = searchDto.getTitle();
        String category = searchDto.getCategory();
        String year = searchDto.getYear();

        Specification<Book> specification = Specification.where(null);

        if (author != null && !author.isEmpty()) {
            specification = specification.and((root, query, builder) ->
                    builder.like(root.get("author"), "%" + author + "%"));
        }

        if (title != null && !title.isEmpty()) {
            specification = specification.and((root, query, builder) ->
                    builder.like(root.get("title"), "%" + title + "%"));
        }
        if (year != null && !year.describeConstable().isEmpty()) {
            specification = specification.and((root, query, builder) ->
                    builder.like(root.get("year"), "%" + year + "%"));
        }
        if (category != null && !category.isEmpty()) {
            specification = specification.and((root, query, builder) ->
                    builder.like(root.get("category"), "%" + category + "%"));
        }

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "views"));
        Page<Book> resultPage = bookRepository.findAll(specification, pageable);
        return resultPage.getContent();
    }


    @Transactional(readOnly = true)
    public List<Book> getAllByCategory(String category){
        return  bookRepository.findAllByCategory(category);

    }

    @Override
    public void updateBook(Book book) {
        Long bookUpdatedId = book.getId();
        if(bookUpdatedId != null) {
            Book bookToUpdate = bookRepository.findById(book.getId()).orElseThrow();
            bookToUpdate.setName(book.getName());
            bookToUpdate.setDescription(book.getDescription());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setCategory(book.getCategory());
            bookToUpdate.setYear(book.getYear());
            bookRepository.save(bookToUpdate);
        }
        else {
            new NullPointerException("Book id is null");
        }
    }


}
