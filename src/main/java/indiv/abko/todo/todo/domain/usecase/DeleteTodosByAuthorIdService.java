package indiv.abko.todo.todo.domain.usecase;

import indiv.abko.todo.todo.domain.port.in.DeleteTodosByAuthorId;
import indiv.abko.todo.todo.domain.port.out.TodoCommentPort;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteTodosByAuthorIdService implements DeleteTodosByAuthorId {
    private final TodoRepository todoRepo;
    private final TodoCommentPort todoCommentPort;

    @Override
    @Transactional
    public void execute(final long authorId) {
        final List<Long> todoIds = todoRepo.findTodoIdsByAuthorId(authorId);
        todoIds.forEach(todoCommentPort::deleteAllByTodoId);
        todoRepo.deleteAllByAuthorId(authorId);
    }
}
