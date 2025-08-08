package indiv.abko.todo.todo.domain.port.in.command;

import lombok.Builder;

@Builder
public record DeleteTodoCommand(
    long todoId,
    long requesterId
) {
    
}
