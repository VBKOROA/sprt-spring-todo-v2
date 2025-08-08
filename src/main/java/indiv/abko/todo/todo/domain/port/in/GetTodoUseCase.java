package indiv.abko.todo.todo.domain.port.in;

import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.port.in.command.GetTodoCommand;

public interface GetTodoUseCase {
    Todo execute(GetTodoCommand getCommand);
}
