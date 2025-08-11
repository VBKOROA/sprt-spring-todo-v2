package indiv.abko.todo.comment.adapter.out;

import indiv.abko.todo.comment.domain.out.CommentTodoPort;
import indiv.abko.todo.todo.domain.port.in.TodoIdShouldExistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentTodoAdapter implements CommentTodoPort {
    private final TodoIdShouldExistUseCase todoIdShouldExistUseCase;

    @Override
    public void todoShouldExist(final long todoId) {
        todoIdShouldExistUseCase.execute(todoId);
    }
}
