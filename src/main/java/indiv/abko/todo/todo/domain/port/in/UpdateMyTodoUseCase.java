package indiv.abko.todo.todo.domain.port.in;

import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.port.in.command.UpdateMyTodoCommand;

public interface UpdateMyTodoUseCase {
    Todo execute(UpdateMyTodoCommand updateCommand);
}
