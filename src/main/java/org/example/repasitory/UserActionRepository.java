package org.example.library.repasitory;

import org.example.library.models.entity.Book;
import org.example.library.models.entity.User;
import org.example.library.models.entity.UserAction;
import org.example.library.models.enums.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction,Long> {

    List<ActionType> findByActionType(ActionType getType);

    @Query(value = "SELECT ua.* FROM user_actions ua " +
            "WHERE ua.user_id = :userId AND ua.action_type LIKE 'LIKE%'", nativeQuery = true)
    List<UserAction> findUserActionsByUserIdAndActionType(@Param("userId") Long userId);


}