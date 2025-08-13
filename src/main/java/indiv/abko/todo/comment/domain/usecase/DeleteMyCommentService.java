package indiv.abko.todo.comment.domain.usecase;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.CommentExceptionEnum;
import indiv.abko.todo.comment.domain.in.DeleteMyCommentUseCase;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import indiv.abko.todo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteMyCommentService implements DeleteMyCommentUseCase {
    private final CommentRepository commentRepo;

    @Override
    @Transactional
    public void execute(final long commentId, final long requesterId) {
        final Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new BusinessException(CommentExceptionEnum.COMMENT_NOT_FOUND));
        comment.shouldHaveAuth(requesterId);
        commentRepo.delete(commentId);
    }
}
