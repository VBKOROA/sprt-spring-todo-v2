package indiv.abko.todo.comment.domain.usecase;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.CommentExceptionEnum;
import indiv.abko.todo.comment.domain.in.WriteCommentCommand;
import indiv.abko.todo.comment.domain.in.WriteCommentUseCase;
import indiv.abko.todo.comment.domain.out.CommentAuthorPort;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import indiv.abko.todo.comment.domain.out.CommentTodoPort;
import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.global.vo.AuthorVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriteCommentService implements WriteCommentUseCase {
    private static final int COMMENT_LIMIT = 10;
    private final CommentRepository commentRepo;
    private final CommentTodoPort todoPort;
    private final CommentAuthorPort authorPort;

    @Override
    @Transactional
    public Comment execute(final WriteCommentCommand command) {
        todoPort.todoShouldExist(command.todoId());
        if(commentRepo.countByTodoId(command.todoId()) == COMMENT_LIMIT) {
            throw new BusinessException(CommentExceptionEnum.COMMENT_LIMIT_EXCEEDED);
        }
        final AuthorVO author = authorPort.getAuthor(command.todoId());
        final Comment comment = command.toDomain(author);
        return commentRepo.save(comment);
    }
}
