package indiv.abko.todo.todo.adapter.out;

import indiv.abko.todo.comment.domain.in.DeleteCommentsByTodoIdUseCase;
import indiv.abko.todo.todo.domain.port.out.TodoCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoCommentAdapter implements TodoCommentPort {
    private final DeleteCommentsByTodoIdUseCase deleteCommentsByTodoIdUseCase;

    @Override
    public void deleteAllByTodoId(long todoId) {
        deleteCommentsByTodoIdUseCase.execute(todoId);
    }
}
