package org.example.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.models.enums.ActionType;

@Data
@NoArgsConstructor
public class UserActionRequest {
    private Long userId;
    private Long bookId;
    private ActionType actionType;
}
