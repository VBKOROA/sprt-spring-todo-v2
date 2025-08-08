package indiv.abko.todo.todo.adapter.in.rest.dto;

import java.time.LocalDateTime;

import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.port.in.TodoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Todo 응답 DTO")
@Builder
public record TodoResp(
    @Schema(description = "Todo ID", example = "1")
    long id,
    @Schema(description = "Todo 제목", example = "테스트")
    String title,
    @Schema(description = "Todo 내용", example = "테스트")
    String content,
    @Schema(description = "Todo 작성자 ID", example = "테스트")
    long authorId,
    @Schema(description = "Todo 작성자 이름", example = "테스트")
    String authorName,
    @Schema(description = "Todo 생성 날짜/시간", example = "2025-01-01T00:00:00")
    LocalDateTime createdAt,
    @Schema(description = "Todo 수정 날짜/시간", example = "2025-01-01T00:00:00")
    LocalDateTime modifiedAt
) {
    public static TodoResp from(final Todo todo) {
        return TodoResp.builder()
                .id(todo.getId())
                .title(todo.getTitle().getTitle())
                .content(todo.getContent().getContent())
                .authorName(todo.getAuthor().getName())
                .createdAt(todo.getCreatedAt())
                .modifiedAt(todo.getModifiedAt())
                .build();
    }
}
