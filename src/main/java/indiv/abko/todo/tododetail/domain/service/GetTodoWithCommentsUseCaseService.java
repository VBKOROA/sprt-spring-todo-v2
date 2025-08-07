package indiv.abko.todo.tododetail.domain.service;

import indiv.abko.todo.tododetail.domain.in.CommentDto;
import indiv.abko.todo.tododetail.domain.in.GetTodoWithCommentsUseCase;
import indiv.abko.todo.tododetail.domain.in.TodoDto;
import indiv.abko.todo.tododetail.domain.in.TodoWithCommentsDto;
import indiv.abko.todo.tododetail.domain.out.CommentSpec;
import indiv.abko.todo.tododetail.domain.out.TodoDetailCommentPort;
import indiv.abko.todo.tododetail.domain.out.TodoDetailTodoPort;
import indiv.abko.todo.tododetail.domain.out.TodoSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTodoWithCommentsUseCaseService implements GetTodoWithCommentsUseCase {
    private final TodoDetailTodoPort todoPort;
    private final TodoDetailCommentPort commentPort;

    @Override
    @Transactional(readOnly = true)
    public TodoWithCommentsDto execute(long todoId) {
        TodoSpec todo = todoPort.getTodoById(todoId);
        List<CommentSpec> comments = commentPort.getCommentsByTodoId(todoId);
        TodoDto todoDto = TodoDto.builder()
                .id(todo.id())
                .title(todo.title())
                .content(todo.content())
                .authorName(todo.authorName())
                .createdAt(todo.createdAt())
                .modifiedAt(todo.modifiedAt())
                .authorId(todo.authorId())
                .build();
        List<CommentDto> commentDtos = comments.stream().map(this::toCommentDto).toList();
        return new TodoWithCommentsDto(todoDto, commentDtos);
    }

    private CommentDto toCommentDto(CommentSpec comment) {
        return CommentDto.builder()
                .id(comment.id())
                .modifiedAt(comment.modifiedAt())
                .content(comment.content())
                .authorId(comment.authorId())
                .createdAt(comment.createdAt())
                .authorName(comment.authorName())
                .build();
    }
}
