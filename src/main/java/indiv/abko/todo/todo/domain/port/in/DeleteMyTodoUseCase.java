package indiv.abko.todo.todo.domain.port.in;

import indiv.abko.todo.todo.domain.port.in.command.DeleteMyTodoCommand;

public interface DeleteMyTodoUseCase {
    void execute(DeleteMyTodoCommand command);
}
