package indiv.abko.todo.comment.adapter.in.rest;

import indiv.abko.todo.comment.adapter.in.rest.dto.CommentResp;
import indiv.abko.todo.comment.adapter.in.rest.dto.CommentWriteReq;
import indiv.abko.todo.comment.adapter.in.rest.dto.UpdateCommentReq;
import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.in.*;
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
@RequestMapping("/api/v1/todos/{todoId}/comments")
@RequiredArgsConstructor
@Tag(name = "Comment API", description = "할일 관리 시스템의 댓글 관련 API")
public class CommentController {
    private final WriteCommentUseCase writeCommentUseCase;
    private final ReadCommentByIdUseCase readCommentByIdUseCase;
    private final UpdateMyCommentUseCase updateMyCommentUseCase;
    private final DeleteMyCommentUseCase deleteMyCommentUseCase;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "댓글 작성", description = "특정 할일에 대한 댓글을 작성함.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "댓글이 성공적으로 작성됨"),
            @ApiResponse(responseCode = "400", description = "파라미터가 유효하지 않음",
                    content = @Content(
                            schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"BAD_REQUEST\",\"message\":\"요청이 잘못되었습니다.\",\"data\":null}")
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

    @GetMapping("/{id}")
    @Operation(summary = " 댓글 단건 조회", description = "댓글 ID로 댓글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 조회 성공"),
            @ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"NOT_FOUND\",\"message\":\"해당 댓글이 존재하지 않습니다.\",\"data\":null}")))
    })
    public ApiResp<CommentResp> readComment(
            @Parameter(description = "할일 ID", example = "1") @PathVariable("todoId") long todoId,
            @Parameter(description = "댓글 ID", example = "1") @PathVariable("id") long id) {
        final Comment comment = readCommentByIdUseCase.execute(id);
        return ApiResp.ok(CommentResp.from(comment));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "내 댓글 수정", description = "자신이 작성한 댓글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "400", description = "파라미터가 유효하지 않음", content = @Content(schema = @Schema(implementation = ApiResp.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ApiResp.class))),
            @ApiResponse(responseCode = "403", description = "권한이 없음", content = @Content(schema = @Schema(implementation = ApiResp.class))),
            @ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResp.class)))
    })
    public ApiResp<CommentResp> updateComment(
            @Parameter(description = "할일 ID", example = "1") @PathVariable("todoId") long todoId,
            @Parameter(description = "댓글 ID", example = "1") @PathVariable("id") long id,
            @RequestBody @Valid UpdateCommentReq updateReq,
            @RequestAttribute(name = "memberId", required = false) @Parameter(hidden = true) Long requesterId) {
        if (requesterId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        final UpdateMyCommentCommand command = updateReq.toCommand(id, requesterId);
        final Comment updatedComment = updateMyCommentUseCase.execute(command);
        return ApiResp.ok(CommentResp.from(updatedComment));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "내 댓글 삭제", description = "자신이 작성한 댓글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ApiResp.class))),
            @ApiResponse(responseCode = "403", description = "권한이 없음", content = @Content(schema = @Schema(implementation = ApiResp.class))),
            @ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResp.class)))
    })
    public void deleteComment(
            @Parameter(description = "할일 ID", example = "1") @PathVariable("todoId") long todoId,
            @Parameter(description = "댓글 ID", example = "1") @PathVariable("id") long id,
            @RequestAttribute(name = "memberId", required = false) @Parameter(hidden = true) Long requesterId) {
        if (requesterId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        deleteMyCommentUseCase.execute(id, requesterId);
    }
}
