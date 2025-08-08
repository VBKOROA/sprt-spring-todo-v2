package indiv.abko.todo.todo.domain.port.in;

import indiv.abko.todo.todo.domain.Todo;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TodoDto (
        long id,
        String title,
        String content,
        long authorId,
        String authorName,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static TodoDto from(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle().getTitle())
                .content(todo.getContent().getContent())
                .authorId(todo.getAuthor().getId())
                .authorName(todo.getAuthor().getName())
                .createdAt(todo.getCreatedAt())
                .modifiedAt(todo.getModifiedAt())
                .build();
    }
}
