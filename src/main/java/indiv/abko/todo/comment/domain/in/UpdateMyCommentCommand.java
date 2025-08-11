package indiv.abko.todo.comment.domain.in;

import lombok.Builder;

@Builder
public record UpdateMyCommentCommand(
        long commentId,
        long requesterId,
        String content
) {
}
