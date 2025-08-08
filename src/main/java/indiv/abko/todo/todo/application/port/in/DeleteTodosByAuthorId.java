package indiv.abko.todo.todo.application.port.in;

public interface DeleteTodosByAuthorId {
    void execute(long authorId);
}
