package indiv.abko.todo.todo.application.port.out;

public interface TodoCommentPort {
    void deleteAllByTodoId(long todoId);
}
