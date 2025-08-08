package indiv.abko.todo.todo.domain.port.out;

public interface TodoCommentPort {
    void deleteAllByTodoId(final long todoId);
}
