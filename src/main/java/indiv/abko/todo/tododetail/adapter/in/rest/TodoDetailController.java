package indiv.abko.todo.tododetail.adapter.in.rest;

import indiv.abko.todo.global.dto.ApiResp;
import indiv.abko.todo.tododetail.adapter.in.rest.dto.CommentResp;
import indiv.abko.todo.tododetail.adapter.in.rest.dto.TodoResp;
import indiv.abko.todo.tododetail.adapter.in.rest.dto.TodoWithCommentsResp;
import indiv.abko.todo.tododetail.domain.in.GetTodoWithCommentsUseCase;
import indiv.abko.todo.tododetail.domain.in.TodoWithCommentsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Validated
@Tag(name = "Todo API", description = "할일 관리 시스템의 Todo 단건 조회 API")
public class TodoDetailController {
    private final GetTodoWithCommentsUseCase getTodoWithCommentsByIdUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "Todo 조회", description = "Todo를 조회함. 해당 Todo와 연결된 댓글 목록도 함께 조회함.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo가 성공적으로 조회됨"),
            @ApiResponse(responseCode = "404", description = "Todo를 찾을 수 없음",
                    content = @Content(
                            schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"NOT_FOUND\",\"message\":\"Todo를 찾을 수 없습니다.\",\"data\":null}")
                    ))
    })
    public ApiResp<TodoWithCommentsResp> getTodo(
            @PathVariable("id")
            @Parameter(name = "id", description = "Todo ID")
            long id) {
        TodoWithCommentsDto dto = getTodoWithCommentsByIdUseCase.execute(id);
        var todoResp = TodoResp.of(dto.todo());
        var commentResps = dto.comments().stream().map(CommentResp::of).toList();
        var responseData = new TodoWithCommentsResp(todoResp, commentResps);
        return ApiResp.ok(responseData);
    }
}
