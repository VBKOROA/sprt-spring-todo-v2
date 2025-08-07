package indiv.abko.todo.comment.adapter.out;

import indiv.abko.todo.comment.domain.out.TodoPort;
import indiv.abko.todo.todo.application.port.in.DeleteAllByAuthorId;
import indiv.abko.todo.todo.application.port.in.TodoIdShouldExistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentTodoAdapter implements TodoPort {
    private final TodoIdShouldExistUseCase todoIdShouldExistUseCase;
    private final DeleteAllByAuthorId deleteAllByAuthorId;

    @Override
    public void shouldExistBy(final long todoId) {
        todoIdShouldExistUseCase.execute(todoId);
    }

    @Override
    public void deleteAllByMemberId(long requesterId) {
        deleteAllByAuthorId.execute(requesterId);
    }
}
