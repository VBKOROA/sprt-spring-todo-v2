package indiv.abko.todo.todo.application.usecase;

import indiv.abko.todo.todo.application.port.in.DeleteAllByAuthorId;
import indiv.abko.todo.todo.application.port.out.TodoCommentPort;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultDeleteAllByAuthorId implements DeleteAllByAuthorId {
    private final TodoRepository todoRepo;
    private final TodoCommentPort todoCommentPort;

    @Override
    @Transactional
    public void execute(long authorId) {
        var todoIds = todoRepo.findTodoIdsByAuthorId(authorId);
        todoIds.forEach(todoId -> {
            todoRepo.deleteById(todoId);
            todoCommentPort.deleteAllByTodoId(todoId);
        });
    }
}
