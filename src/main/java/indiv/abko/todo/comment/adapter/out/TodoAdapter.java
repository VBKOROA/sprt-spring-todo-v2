package indiv.abko.todo.comment.adapter.out;

import indiv.abko.todo.comment.domain.out.TodoPort;
import indiv.abko.todo.todo.application.port.in.TodoIdShouldExistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoAdapter implements TodoPort {
    private final TodoIdShouldExistUseCase todoIdShouldExistUseCase;

    @Override
    public void shouldExistBy(final long todoId) {
        todoIdShouldExistUseCase.execute(todoId);
    }
}
