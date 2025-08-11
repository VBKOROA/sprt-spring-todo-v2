package indiv.abko.todo.todo.domain.port.in;

public interface DeleteTodosByAuthorId {
    void execute(long authorId);
}
