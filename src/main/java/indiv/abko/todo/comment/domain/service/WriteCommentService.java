package indiv.abko.todo.comment.domain.service;

import indiv.abko.todo.comment.domain.CommentExceptionEnum;
import indiv.abko.todo.comment.domain.in.CommentDto;
import indiv.abko.todo.comment.domain.in.WriteCommentCommand;
import indiv.abko.todo.comment.domain.in.WriteCommentUseCase;
import indiv.abko.todo.comment.domain.out.AuthorPort;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import indiv.abko.todo.comment.domain.out.CommentTodoPort;
import indiv.abko.todo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriteCommentService implements WriteCommentUseCase {
    private static final int COMMENT_LIMIT = 10;
    private final CommentRepository commentRepo;
    private final CommentTodoPort todoPort;
    private final AuthorPort authorPort;

    @Override
    @Transactional
    public CommentDto execute(final WriteCommentCommand command) {
        todoPort.todoShouldExist(command.todoId());
        if(commentRepo.countByTodoId(command.todoId()) == COMMENT_LIMIT) {
            throw new BusinessException(CommentExceptionEnum.COMMENT_LIMIT_EXCEEDED);
        }
        var author = authorPort.getAuthor(command.todoId());
        var comment = command.toDomain(author);
        var savedComment = commentRepo.save(comment);
        return CommentDto.from(savedComment);
    }
}
