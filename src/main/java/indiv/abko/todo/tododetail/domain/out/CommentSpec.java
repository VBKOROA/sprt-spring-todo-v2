package indiv.abko.todo.tododetail.domain.out;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentSpec(
        long id,
        String content,
        long authorId,
        String authorName,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
