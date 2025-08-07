package indiv.abko.todo.tododetail.adapter.in.rest.dto;

import indiv.abko.todo.tododetail.domain.in.TodoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Schema(description = "Todo 응답 DTO")
@Builder
public record TodoResp(
    @Schema(description = "Todo ID", example = "1")
    Long id,
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
    public static TodoResp of(TodoDto todoDto) {
        return TodoResp.builder()
                .id(todoDto.id())
                .title(todoDto.title())
                .content(todoDto.content())
                .authorId(todoDto.authorId())
                .authorName(todoDto.authorName())
                .createdAt(todoDto.createdAt())
                .modifiedAt(todoDto.modifiedAt())
                .build();
    }
}
