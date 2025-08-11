package indiv.abko.todo.comment.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentJpaEntity, Long> {
    int countByTodoId(long todoId);

    List<CommentJpaEntity> findAllByTodoId(Long todoId);

    void deleteAllByTodoId(Long todoId);

    void deleteAllByAuthorId(Long authorId);

    void deleteAllByTodoIdIn(Collection<Long> todoIds);
}
