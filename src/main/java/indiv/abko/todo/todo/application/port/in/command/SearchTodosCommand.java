package indiv.abko.todo.todo.application.port.in.command;

import indiv.abko.todo.todo.domain.SearchTodosCriteria;
import lombok.Builder;
import org.springframework.data.domain.Pageable;

@Builder
public record SearchTodosCommand(
    String orderBy,
    String title,
    String content,
    String authorName,
    Pageable pageable
) {
    public SearchTodosCriteria toCriteria() {
        return SearchTodosCriteria.builder()
                .orderBy(orderBy)
                .title(title)
                .content(content)
                .authorName(authorName)
                .build();
    }
}
