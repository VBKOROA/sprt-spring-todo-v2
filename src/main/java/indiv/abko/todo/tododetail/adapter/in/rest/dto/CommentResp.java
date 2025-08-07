package indiv.abko.todo.tododetail.adapter.in.rest.dto;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.tododetail.domain.in.CommentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "댓글 응답 DTO")
@Builder
public record CommentResp(
    @Schema(description = "댓글 ID", example = "1")
    long id,
    @Schema(description = "댓글 내용", example = "테스트")
    String content,
    @Schema(description = "댓글 작성자 ID", example = "테스트")
    long authorId,
    @Schema(description = "댓글 작성자 이름", example = "테스트")
    String authorName,
    @Schema(description = "댓글 작성일", example = "2025-01-01T12:00:00")
    LocalDateTime createdAt,
    @Schema(description = "댓글 수정일", example = "2025-01-01T12:00:00")
    LocalDateTime modifiedAt
) {
    public static CommentResp of(CommentDto dto) {
        return CommentResp.builder()
                .id(dto.id())
                .content(dto.content())
                .authorId(dto.authorId())
                .authorName(dto.authorName())
                .createdAt(dto.createdAt())
                .modifiedAt(dto.modifiedAt())
                .build();
    }
}