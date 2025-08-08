package indiv.abko.todo.tododetail.adapter.out;

import indiv.abko.todo.comment.domain.in.GetCommentsByTodoIdUseCase;
import indiv.abko.todo.tododetail.domain.out.CommentSpec;
import indiv.abko.todo.tododetail.domain.out.TodoDetailCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TodoDetailCommentPortService implements TodoDetailCommentPort {
    private final GetCommentsByTodoIdUseCase  getCommentsByTodoIdUseCase;

    @Override
    public List<CommentSpec> getCommentsByTodoId(long todoId) {
        var comments = getCommentsByTodoIdUseCase.execute(todoId);
        return comments.stream().map(comment -> toSpec(comment)).toList();
    }

    private CommentSpec toSpec(CommentDto comment) {
        return CommentSpec.builder()
                .id(comment.id())
                .authorId(comment.authorId())
                .authorName(comment.authorName())
                .createdAt(comment.createdAt())
                .modifiedAt(comment.modifiedAt())
                .content(comment.content())
                .build();
    }
}
