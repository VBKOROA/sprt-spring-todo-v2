package indiv.abko.todo.todo.domain.port.in;

import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.port.in.command.SearchTodosCommand;
import org.springframework.data.domain.Page;

public interface SearchTodosUseCase {
    Page<Todo> execute(SearchTodosCommand searchCommand);
}
