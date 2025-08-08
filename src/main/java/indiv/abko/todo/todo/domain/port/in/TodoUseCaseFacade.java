package indiv.abko.todo.todo.domain.port.in;

import indiv.abko.todo.todo.application.port.in.command.*;
import indiv.abko.todo.todo.domain.port.in.command.*;
import org.springframework.data.domain.Page;

public interface TodoUseCaseFacade {
    public void deleteTodo(DeleteTodoCommand command);

    public TodoDto createTodo(CreateTodoCommand command);

    public TodoDto updateTodo(UpdateTodoCommand command);

    public Page<TodoDto> searchTodos(SearchTodosCommand command);

    public TodoDto getTodo(GetTodoCommand command);
}
