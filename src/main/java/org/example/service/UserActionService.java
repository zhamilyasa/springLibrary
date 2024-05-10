package org.example.library.service;

import org.example.library.dtos.UserActionRequest;
import org.example.library.models.entity.Book;
import org.example.library.models.entity.UserAction;
import org.example.library.models.enums.ActionType;

import java.util.List;

public interface UserActionService {
    void createAction(UserActionRequest userActionRequest);
    List<Book> getActionType(Long userId , ActionType actionType);
}
