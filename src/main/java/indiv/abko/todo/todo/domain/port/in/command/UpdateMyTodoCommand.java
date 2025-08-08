package indiv.abko.todo.todo.domain.port.in.command;

import lombok.Builder;

@Builder
public record UpdateMyTodoCommand(
    long requesterId,
    long todoId,
    String content
) {
} 
