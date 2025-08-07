package indiv.abko.todo.todo.application.port.in;

import indiv.abko.todo.todo.application.port.in.command.*;
import indiv.abko.todo.todo.domain.Comment;
import indiv.abko.todo.todo.domain.Todo;
import org.springframework.data.domain.Page;

public interface TodoUseCaseFacade {
    public void deleteTodo(DeleteTodoCommand command);

    public Todo createTodo(CreateTodoCommand command);

    public Todo updateTodo(UpdateTodoCommand command);

    public Page<Todo> searchTodos(SearchTodosCommand command);

    public Todo getTodo(GetTodoCommand command);
}
