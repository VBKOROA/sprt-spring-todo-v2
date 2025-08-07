package indiv.abko.todo.comment.domain.in;

import lombok.Builder;

@Builder
public record UpdateCommentCommand(
        long commentId,
        long requesterId,
        String content
) {
}
