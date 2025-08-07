package indiv.abko.todo.tododetail.domain.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentDto(
        long id,
        String content,
        long authorId,
        String authorName,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
