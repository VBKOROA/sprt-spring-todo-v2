package indiv.abko.todo.comment.domain.usecase;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.CommentExceptionEnum;
import indiv.abko.todo.comment.domain.in.UpdateMyCommentCommand;
import indiv.abko.todo.comment.domain.in.UpdateMyCommentUseCase;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import indiv.abko.todo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMyCommentService implements UpdateMyCommentUseCase {
    private final CommentRepository commentRepo;

    @Override
    @Transactional
    public Comment execute(final UpdateMyCommentCommand command) {
        final Comment comment = commentRepo.findById(command.commentId())
                .orElseThrow(() -> new BusinessException(CommentExceptionEnum.COMMENT_NOT_FOUND));
        comment.updateContent(command.content(), command.requesterId());
        return commentRepo.save(comment);
    }
}
