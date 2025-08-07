package indiv.abko.todo.comment.adapter.in.rest.dto;

import indiv.abko.todo.comment.domain.in.UpdateCommentCommand;
import jakarta.validation.constraints.NotBlank;

public record UpdateCommentReq(
        @NotBlank
        String content
) {
    public UpdateCommentCommand toCommand(long id, long requesterId) {
        return UpdateCommentCommand.builder()
                .commentId(id)
                .content(content)
                .requesterId(requesterId)
                .build();
    }
}
