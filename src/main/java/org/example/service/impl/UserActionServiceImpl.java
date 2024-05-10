package org.example.library.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.library.dtos.UserActionRequest;
import org.example.library.models.entity.Book;
import org.example.library.models.entity.User;
import org.example.library.models.entity.UserAction;
import org.example.library.models.enums.ActionType;
import org.example.library.repasitory.BookRepository;
import org.example.library.repasitory.UserActionRepository;
import org.example.library.repasitory.UserRepository;
import org.example.library.service.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@Slf4j
public class UserActionServiceImpl implements UserActionService {
    private final UserActionRepository userActionRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserActionServiceImpl(UserActionRepository userActionRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.userActionRepository = userActionRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> getActionType(Long userId , ActionType actionType){
        log.info("Get action type for user id {} and action type {}", userId , actionType);
        List<Book> books = bookRepository.findAllBooksByUserIdAndActionType(userId ,actionType);
        log.info("books {} ", books.toString());
        return books;
    }


    @Transactional
    public void createAction(UserActionRequest userActionRequest){
        log.info("Create action for user id {} and action type {}" , userActionRequest.toString());
        User user = userRepository.findById(userActionRequest.getUserId()).orElse(null);
        Book book = bookRepository.findById(userActionRequest.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        UserAction userAction = new UserAction();
        userAction.setActionType(userActionRequest.getActionType());
        userAction.setUser(user);
        userAction.setBook(book);
        userActionRepository.save(userAction);
    }

}
