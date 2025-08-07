package indiv.abko.todo.comment.domain.service;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.in.CommentDto;
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
    public List<CommentDto> execute(long todoId) {
        var comments = commentRepo.findAllBy(todoId);
        return comments.stream().map(CommentDto::from).toList();
    }
}
