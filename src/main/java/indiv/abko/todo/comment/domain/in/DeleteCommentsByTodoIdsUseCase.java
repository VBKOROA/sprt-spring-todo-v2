package indiv.abko.todo.comment.domain.in;

import java.util.List;

public interface DeleteCommentsByTodoIdsUseCase {
    void execute(List<Long> todoIds);
}
