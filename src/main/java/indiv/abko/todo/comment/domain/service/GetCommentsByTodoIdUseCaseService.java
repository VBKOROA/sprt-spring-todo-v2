package indiv.abko.todo.comment.domain.service;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.in.GetCommentsByTodoIdUseCase;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommentsByTodoIdUseCaseService implements GetCommentsByTodoIdUseCase {
    private final CommentRepository commentRepo;

    @Override
    public List<Comment> execute(final long todoId) {
        return commentRepo.findAllBy(todoId);
    }
}
