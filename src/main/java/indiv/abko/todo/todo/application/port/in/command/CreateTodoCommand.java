package indiv.abko.todo.todo.application.port.in.command;

import lombok.Builder;

@Builder
public record CreateTodoCommand(
    long memberId,
    String title,
    String content
) {
}