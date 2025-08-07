package indiv.abko.todo.todo.application.usecase;

import indiv.abko.todo.todo.application.port.in.TodoDto;
import indiv.abko.todo.todo.application.port.in.TodoUseCaseFacade;
import indiv.abko.todo.todo.application.port.in.command.*;
import indiv.abko.todo.todo.domain.Todo;
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
    public TodoDto createTodo(CreateTodoCommand command) {
        return createTodoUseCase.execute(command);
    }

    @Override
    public TodoDto updateTodo(UpdateTodoCommand command) { return updateTodoUseCase.execute(command); }

    @Override
    public Page<TodoDto> searchTodos(SearchTodosCommand command) { return searchTodosUseCase.execute(command); }

    @Override
    public TodoDto getTodo(GetTodoCommand command) { return getTodoUseCase.execute(command); }
}
