package indiv.abko.todo.todo.application.port.out;

public interface CommentPort {
    void deleteAllByTodoId(long todoId);
}
