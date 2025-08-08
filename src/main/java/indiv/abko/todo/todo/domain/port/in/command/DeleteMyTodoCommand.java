package indiv.abko.todo.todo.domain.port.in.command;

import lombok.Builder;

@Builder
public record DeleteMyTodoCommand(
    long todoId,
    long requesterId
) {
    
}
