package indiv.abko.todo.comment.domain.service;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.CommentExceptionEnum;
import indiv.abko.todo.comment.domain.in.AddCommentCommand;
import indiv.abko.todo.comment.domain.in.AddCommentUseCase;
import indiv.abko.todo.comment.domain.out.AuthorPort;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import indiv.abko.todo.comment.domain.out.TodoPort;
import indiv.abko.todo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddCommentService implements AddCommentUseCase {
    private static final int COMMENT_LIMIT = 10;
    private final CommentRepository commentRepo;
    private final TodoPort todoPort;
    private final AuthorPort authorPort;

    @Override
    @Transactional
    public Comment execute(final AddCommentCommand command) {
        todoPort.shouldExistBy(command.todoId());
        if(commentRepo.countByTodoId(command.todoId()) == COMMENT_LIMIT) {
            throw new BusinessException(CommentExceptionEnum.COMMENT_LIMIT_EXCEEDED);
        }
        var author = authorPort.getAuthor(command.todoId());
        var comment = command.toDomain(author);
        return commentRepo.save(comment);
    }
}
