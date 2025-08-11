package indiv.abko.todo.comment.domain.usecase;

import indiv.abko.todo.comment.domain.in.DeleteCommentsByTodoIdUseCase;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCommentsByTodoIdUseCaseService implements DeleteCommentsByTodoIdUseCase {
    private final CommentRepository commentRepo;

    @Override
    @Transactional
    public void execute(final long todoId) {
        commentRepo.deleteAllBy(todoId);
    }
}
