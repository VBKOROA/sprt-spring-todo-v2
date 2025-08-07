package indiv.abko.todo.tododetail.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Todo와 댓글 응답 DTO")
public record TodoWithCommentsResp(
    @Schema(description = "Todo 정보")
    TodoResp todo,
    @Schema(description = "댓글 목록")
    List<CommentResp> comments
) {
    
}
