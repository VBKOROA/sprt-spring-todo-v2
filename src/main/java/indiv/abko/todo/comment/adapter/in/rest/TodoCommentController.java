package indiv.abko.todo.comment.adapter.in.rest;

import indiv.abko.todo.comment.adapter.in.rest.dto.CommentResp;
import indiv.abko.todo.comment.adapter.in.rest.dto.CommentWriteReq;
import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.in.WriteCommentCommand;
import indiv.abko.todo.comment.domain.in.WriteCommentUseCase;
import indiv.abko.todo.global.dto.ApiResp;
import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.global.exception.GlobalExceptionEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comment API", description = "할일 관리 시스템의 댓글 관련 API")
public class TodoCommentController {

    private final WriteCommentUseCase writeCommentUseCase;

    @PostMapping("/api/v1/todos/{todoId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "댓글 작성", description = "특정 할일에 대한 댓글을 작성함.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "댓글이 성공적으로 작성됨"),
            @ApiResponse(responseCode = "400", description = "파라미터가 유효하지 않음",
                    content = @Content(
                            schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"BAD_REQUEST\",\"message\":\"요청이 잘못되었습니다.\",\"data\":null}")
                    )),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
                    content = @Content(
                            schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"UNAUTHORIZED\",\"message\":\"인증이 필요합니다.\",\"data\":null}")
                    ))
    })
    public ApiResp<CommentResp> writeComment(
            @PathVariable("todoId")
            @Parameter(description = "할일 ID", example = "1")
            long todoId,
            @RequestBody
            @Valid
            CommentWriteReq req,
            @RequestAttribute(name = "memberId", required = false)
            @Parameter(hidden = true)
            Long authorId) {
        if (authorId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        final var command = WriteCommentCommand.builder() 
                .todoId(todoId)
                .authorId(authorId)
                .content(req.content())
                .build();
        final Comment addedComment = writeCommentUseCase.execute(command);
        return ApiResp.created(CommentResp.from(addedComment));
    }
}
