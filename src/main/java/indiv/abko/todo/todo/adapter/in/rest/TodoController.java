package indiv.abko.todo.todo.adapter.in.rest;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.global.exception.GlobalExceptionEnum;
import indiv.abko.todo.todo.adapter.in.rest.dto.*;
import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.port.in.TodoUseCaseFacade;
import indiv.abko.todo.todo.domain.port.in.command.CreateTodoCommand;
import indiv.abko.todo.todo.domain.port.in.command.DeleteMyTodoCommand;
import indiv.abko.todo.todo.domain.port.in.command.SearchTodosCommand;
import indiv.abko.todo.todo.domain.port.in.command.UpdateMyTodoCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import indiv.abko.todo.global.dto.ApiResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Validated
@Tag(name = "Todo API", description = "할일 관리 시스템의 Todo 관련 API")
public class TodoController {
    private final TodoUseCaseFacade todoUseCaseFacade;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "새 Todo 생성", description = "새 Todo 생성")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Todo가 성공적으로 생성됨"),
        @ApiResponse(responseCode = "400", description = "파라미터가 유효하지 않음",
            content = @Content(
                schema = @Schema(implementation = ApiResp.class),
                examples = @ExampleObject(value = "{\"status\":\"BAD_REQUEST\",\"message\":\"요청이 잘못되었습니다.\",\"data\":null}")
            ))
    })
    public ApiResp<TodoResp> createTodo(@RequestBody @Valid TodoCreateReq createReq,
                                        @RequestAttribute(name = "memberId", required = false) Long memberId) {
        if(memberId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        final var command = CreateTodoCommand.builder()
                .memberId(memberId)
                .title(createReq.title())
                .content(createReq.content())
                .build();
        final Todo createdTodo = todoUseCaseFacade.createTodo(command);
        return ApiResp.created(TodoResp.from(createdTodo));
    }

    // @ModelAttribute: 여러 개의 파라미터를 객체로 바인딩 할 수 있음
    @GetMapping("")
    @Operation(summary = "Todo 목록 조회", description = "Todo 목록을 조회함. 필터링 및 정렬 기능을 지원함.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Todo 목록이 성공적으로 조회됨"),
        @ApiResponse(responseCode = "400", description = "파라미터가 유효하지 않음",
            content = @Content(
                schema = @Schema(implementation = ApiResp.class),
                examples = @ExampleObject(value = "{\"status\":\"BAD_REQUEST\",\"message\":\"요청이 잘못되었습니다.\",\"data\":null}")
            ))
    })
    public ApiResp<TodoListResp> searchTodos(
            @ModelAttribute 
            @ParameterObject
            TodoSearchCondition condition,
            @ParameterObject
            @PageableDefault(size = 10)
            Pageable pageable) {
        final var command = SearchTodosCommand.builder()
                .title(condition.title())
                .authorName(condition.authorName())
                .content(condition.content())
                .orderBy(condition.orderBy())
                .pageable(pageable)
                .build();
        final Page<Todo> todos = todoUseCaseFacade.searchTodos(command);
        final List<TodoResp> responseTodos = todos.map(TodoResp::from).getContent();
        final var responseData = TodoListResp.builder()
                .todos(responseTodos)
                .pageNumber(todos.getNumber())
                .totalPages(todos.getTotalPages())
                .build();
        return ApiResp.ok(responseData);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Todo 수정", description = "Todo를 수정함. 수정할 수 있는 필드는 제목, 작성자임.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Todo가 성공적으로 수정됨"),
        @ApiResponse(responseCode = "400", description = "파라미터가 유효하지 않음",
            content = @Content(
                schema = @Schema(implementation = ApiResp.class),
                examples = @ExampleObject(value = "{\"status\":\"BAD_REQUEST\",\"message\":\"요청이 잘못되었습니다.\",\"data\":null}")
            )),
        @ApiResponse(responseCode = "401", description = "인증 실패",
            content = @Content(
                schema = @Schema(implementation = ApiResp.class),
                examples = @ExampleObject(value = "{\"status\":\"UNAUTHORIZED\",\"message\":\"인증에 실패하였습니다.\",\"data\":null}")
            )),
        @ApiResponse(responseCode = "404", description = "Todo를 찾을 수 없음",
            content = @Content(
                schema = @Schema(implementation = ApiResp.class),
                examples = @ExampleObject(value = "{\"status\":\"NOT_FOUND\",\"message\":\"Todo를 찾을 수 없습니다.\",\"data\":null}")
            ))
    })
    public ApiResp<TodoResp> updateMyTodo(
        @PathVariable("id") 
        @Parameter(name = "id", description = "Todo ID") 
        long id,
        @RequestBody 
        @Valid 
        TodoUpdateReq updateReq,
        @RequestAttribute(name = "memberId", required = false)
        Long requesterId) {
        if(requesterId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        final UpdateMyTodoCommand command =  UpdateMyTodoCommand.builder()
                .requesterId(requesterId)
                .todoId(id)
                .content(updateReq.content())
                .build();
        final Todo updatedTodo = todoUseCaseFacade.updateMyTodo(command);
        return ApiResp.ok(TodoResp.from(updatedTodo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Todo 삭제", description = "Todo를 삭제함.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Todo가 성공적으로 삭제됨"),
        @ApiResponse(responseCode = "401", description = "인증 실패",
            content = @Content(
                schema = @Schema(implementation = ApiResp.class),
                examples = @ExampleObject(value = "{\"status\":\"UNAUTHORIZED\",\"message\":\"인증에 실패하였습니다.\",\"data\":null}")
            )),
        @ApiResponse(responseCode = "404", description = "Todo를 찾을 수 없음",
            content = @Content(
                schema = @Schema(implementation = ApiResp.class),
                examples = @ExampleObject(value = "{\"status\":\"NOT_FOUND\",\"message\":\"Todo를 찾을 수 없습니다.\",\"data\":null}")
            ))
    })
    public void deleteMyTodo(
        @Parameter(name = "id", description = "Todo ID")
        @PathVariable("id") 
        long id,
        @Parameter(name = "X-Todo-Password", 
            in = ParameterIn.HEADER, 
            description = "Todo 삭제를 위한 인증 비밀번호 / base64로 인코딩되어야 함.", 
            required = true)
        @RequestAttribute(name = "memberId", required = false)
        Long requesterId) {
        if(requesterId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        final DeleteMyTodoCommand command = DeleteMyTodoCommand.builder()
                .todoId(id)
                .requesterId(requesterId)
                .build();
        todoUseCaseFacade.deleteMyTodo(command);
    }
}
