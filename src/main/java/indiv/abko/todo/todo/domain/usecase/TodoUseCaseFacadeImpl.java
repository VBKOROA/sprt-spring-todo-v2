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
    private final DeleteMyTodoUseCaseService deleteMyTodoUseCase;
    private final CreateTodoUseCaseService createTodoUseCase;
    private final UpdateMyTodoUseCaseService updateMyTodoUseCase;
    private final SearchTodosUseCaseService searchTodosUseCase;
    private final GetTodoUseCaseService getTodoUseCase;

    @Override
    public void deleteMyTodo(DeleteMyTodoCommand command) {
        deleteMyTodoUseCase.execute(command);
    }

    @Override
    public Todo createTodo(CreateTodoCommand command) {
        return createTodoUseCase.execute(command);
    }

    @Override
    public Todo updateMyTodo(UpdateMyTodoCommand command) { return updateMyTodoUseCase.execute(command); }

    @Override
    public Page<Todo> searchTodos(SearchTodosCommand command) { return searchTodosUseCase.execute(command); }

    @Override
    public Todo getTodo(GetTodoCommand command) { return getTodoUseCase.execute(command); }
}
