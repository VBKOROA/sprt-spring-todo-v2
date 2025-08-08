package indiv.abko.todo.comment.adapter.in.rest.dto;

import indiv.abko.todo.comment.domain.in.UpdateMyCommentCommand;
import jakarta.validation.constraints.NotBlank;

public record UpdateCommentReq(
        @NotBlank
        String content
) {
    public UpdateMyCommentCommand toCommand(final long id, final long requesterId) {
        return UpdateMyCommentCommand.builder()
                .commentId(id)
                .content(content)
                .requesterId(requesterId)
                .build();
    }
}
