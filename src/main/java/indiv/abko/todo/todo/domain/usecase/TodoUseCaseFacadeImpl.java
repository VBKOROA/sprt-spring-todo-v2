package indiv.abko.todo.todo.domain.usecase;

import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.port.in.TodoUseCaseFacade;
import indiv.abko.todo.todo.domain.port.in.command.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoUseCaseFacadeImpl implements TodoUseCaseFacade {
    private final DeleteTodoUseCase deleteTodoUseCase;
    private final CreateTodoUseCase createTodoUseCase;
    private final UpdateTodoUseCase updateTodoUseCase;
    private final SearchTodosUseCase searchTodosUseCase;
    private final GetTodoUseCase getTodoUseCase;

    @Override
    public void deleteTodo(DeleteTodoCommand command) {
        deleteTodoUseCase.execute(command);
    }

    @Override
    public Todo createTodo(CreateTodoCommand command) {
        return createTodoUseCase.execute(command);
    }

    @Override
    public Todo updateTodo(UpdateTodoCommand command) { return updateTodoUseCase.execute(command); }

    @Override
    public Page<Todo> searchTodos(SearchTodosCommand command) { return searchTodosUseCase.execute(command); }

    @Override
    public Todo getTodo(GetTodoCommand command) { return getTodoUseCase.execute(command); }
}
