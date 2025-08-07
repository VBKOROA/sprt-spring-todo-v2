package indiv.abko.todo.tododetail.adapter.out;

import indiv.abko.todo.comment.domain.Comment;
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
        return comments.stream().map(this::toSpec).toList();
    }

    private CommentSpec toSpec(Comment comment) {
        return CommentSpec.builder()
                .id(comment.getId())
                .authorId(comment.getAuthor().getId())
                .authorName(comment.getAuthor().getName())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .content(comment.getContent().getContent())
                .build();
    }
}
