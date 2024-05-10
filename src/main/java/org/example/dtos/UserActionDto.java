package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.library.models.enums.ActionType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserActionDto {
    private Long id;
    private Integer userId;
    private Integer bookId;
    private ActionType actionType;
}
