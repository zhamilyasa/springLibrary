package org.example.library.repasitory;

import jakarta.transaction.Transactional;
import org.example.library.models.entity.Book;
import org.example.library.models.enums.ActionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAll(Specification<Book> specification, Pageable pageable);

    List<Book> findAllByCategory(String category);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserAction ua WHERE ua.book.id = :id")
    void deleteBookActions(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Book b WHERE b.id = :id")
    void deleteBook(@Param("id") Long id);


    default void deleteBookAndActions(Long id) {
        deleteBookActions(id);
        deleteBook(id);
    }

    @Transactional
    @Modifying
    @Query(value = "SELECT DISTINCT b FROM Book b JOIN UserAction ua ON b.id = ua.book.id " +
            "WHERE ua.user.id = :userId AND ua.actionType = :actionType")
    List<Book> findAllBooksByUserIdAndActionType(@Param("userId") Long userId, @Param("actionType") ActionType actionType);

}