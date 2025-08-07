package indiv.abko.todo.comment.domain.in;

public interface DeleteCommentsByTodoIdUseCase {
    void execute(long todoId);
}
