package indiv.abko.todo.tododetail.domain.out;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TodoSpec(
        long id,
        String title,
        String content,
        long authorId,
        String authorName,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
