package indiv.abko.todo.todo.domain.port.in.command;

import lombok.Builder;

@Builder
public record CreateTodoCommand(
    long memberId,
    String title,
    String content
) {
}