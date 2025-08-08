package indiv.abko.todo.comment.domain.service;

import indiv.abko.todo.comment.domain.in.DeleteMyCommentUseCase;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteMyCommentUseCaseService implements DeleteMyCommentUseCase {
    private final CommentRepository commentRepo;

    @Override
    @Transactional
    public void execute(final long commentId, final long requesterId) {
        commentRepo.findById(commentId).ifPresent(comment -> {
           comment.shouldHaveAuth(requesterId);
           commentRepo.delete(commentId);
        });
    }
}
