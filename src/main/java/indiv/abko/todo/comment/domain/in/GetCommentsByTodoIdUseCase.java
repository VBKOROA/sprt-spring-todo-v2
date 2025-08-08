package indiv.abko.todo.comment.domain.in;

import indiv.abko.todo.comment.domain.Comment;

import java.util.List;

public interface GetCommentsByTodoIdUseCase {
    List<Comment> execute(long id);
}
