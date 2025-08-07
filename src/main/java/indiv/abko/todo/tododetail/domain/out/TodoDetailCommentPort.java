package indiv.abko.todo.tododetail.domain.out;

import java.util.List;

public interface TodoDetailCommentPort {
    List<CommentSpec> getCommentsByTodoId(long todoId);
}
