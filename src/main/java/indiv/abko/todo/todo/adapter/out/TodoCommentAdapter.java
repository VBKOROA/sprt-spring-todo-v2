package indiv.abko.todo.todo.adapter.out;

import indiv.abko.todo.comment.domain.in.DeleteCommentsByTodoIdUseCase;
import indiv.abko.todo.comment.domain.in.DeleteCommentsByTodoIdsUseCase;
import indiv.abko.todo.member.domain.usecase.DefaultGetNameByMemberIdUseCase;
import indiv.abko.todo.todo.domain.port.out.TodoCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TodoCommentAdapter implements TodoCommentPort {
    private final DeleteCommentsByTodoIdUseCase deleteCommentsByTodoIdUseCase;
    private final DeleteCommentsByTodoIdsUseCase deleteCommentsByTodoIdsUseCase;

    @Override
    public void deleteAllByTodoId(final long todoId) {
        deleteCommentsByTodoIdUseCase.execute(todoId);
    }

    @Override
    public void deleteAllByTodoIds(List<Long> todoIds) {
        deleteCommentsByTodoIdsUseCase.execute(todoIds);
    }
}
