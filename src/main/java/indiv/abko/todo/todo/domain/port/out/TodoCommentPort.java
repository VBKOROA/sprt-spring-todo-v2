package indiv.abko.todo.todo.domain.port.out;

public interface TodoCommentPort {
    void deleteAllByTodoId(long todoId);
}
