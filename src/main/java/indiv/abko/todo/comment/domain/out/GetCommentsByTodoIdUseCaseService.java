package indiv.abko.todo.comment.domain.out;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.in.GetCommentsByTodoIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommentsByTodoIdUseCaseService implements GetCommentsByTodoIdUseCase {
    private final CommentRepository commentRepo;

    @Override
    public List<Comment> execute(long todoId) {
        return commentRepo.findAllBy(todoId);
    }
}
