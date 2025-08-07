package indiv.abko.todo.comment.adapter.in.rest.dto;

import indiv.abko.todo.comment.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Schema(description = "댓글 응답 DTO")
@Builder
public record CommentResp(
    @Schema(description = "댓글 ID", example = "1")
    Long id,
    @Schema(description = "댓글 내용", example = "테스트")
    String content,
    @Schema(description = "댓글 작성자 이름", example = "테스트")
    String authorName,
    @Schema(description = "댓글 작성일", example = "2025-01-01T12:00:00")
    LocalDateTime createdAt,
    @Schema(description = "댓글 수정일", example = "2025-01-01T12:00:00")
    LocalDateTime modifiedAt
) {
    public static CommentResp from(Comment comment) {
        return CommentResp.builder()
                .id(comment.getId())
                .content(comment.getContent().getContent())
                .authorName(comment.getAuthor().getName())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}