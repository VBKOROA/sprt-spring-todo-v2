package indiv.abko.todo.comment.domain.out;

import indiv.abko.todo.comment.domain.in.DeleteCommentsByTodoIdsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteCommentsByTodoIdsUseCaseService implements DeleteCommentsByTodoIdsUseCase {
    private final CommentRepository commentRepo;

    @Override
    public void execute(final List<Long> todoIds) {
        commentRepo.deleteAllByTodoIds(todoIds);
    }
}
