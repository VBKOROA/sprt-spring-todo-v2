package indiv.abko.todo.comment.domain.in;

import java.util.List;

public interface GetCommentsByTodoIdUseCase {
    List<CommentDto> execute(long id);
}
