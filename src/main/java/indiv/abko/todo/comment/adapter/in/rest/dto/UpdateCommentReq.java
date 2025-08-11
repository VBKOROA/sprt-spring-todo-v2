package indiv.abko.todo.comment.adapter.in.rest.dto;

import indiv.abko.todo.comment.domain.in.UpdateMyCommentCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "댓글 수정 DTO")
public record UpdateCommentReq(
        @NotBlank
        @Schema(description = "수정할 댓글 내용 / 1~200자 이내", example = "수정된 내용", requiredMode = Schema.RequiredMode.REQUIRED)
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
