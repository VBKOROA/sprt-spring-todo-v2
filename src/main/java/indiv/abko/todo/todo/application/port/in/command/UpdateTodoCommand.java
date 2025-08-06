package indiv.abko.todo.todo.application.port.in.command;

import lombok.Builder;

@Builder
public record UpdateTodoCommand(
    long requesterId,
    long todoId,
    String content
) {
} 
