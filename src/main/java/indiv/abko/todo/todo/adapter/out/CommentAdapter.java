package indiv.abko.todo.todo.adapter.out;

import indiv.abko.todo.comment.domain.in.DeleteCommentsByTodoIdUseCase;
import indiv.abko.todo.todo.application.port.out.CommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentAdapter implements CommentPort {
    private final DeleteCommentsByTodoIdUseCase deleteCommentsByTodoIdUseCase;

    @Override
    public void deleteAllByTodoId(long todoId) {
        deleteCommentsByTodoIdUseCase.execute(todoId);
    }
}
