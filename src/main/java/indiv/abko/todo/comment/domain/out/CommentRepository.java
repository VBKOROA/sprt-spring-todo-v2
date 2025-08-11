package indiv.abko.todo.comment.domain.out;

import indiv.abko.todo.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    int countByTodoId(long todoId);

    List<Comment> findAllBy(long todoId);

    void deleteAllBy(long todoId);

    Optional<Comment> findById(long id);

    void deleteAllByAuthorId(long authorId);

    void delete(long commentId);

    void deleteAllByTodoIds(List<Long> todoIds);
}
