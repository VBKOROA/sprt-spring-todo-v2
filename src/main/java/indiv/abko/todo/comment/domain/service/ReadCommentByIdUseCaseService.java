package indiv.abko.todo.comment.domain.service;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.CommentExceptionEnum;
import indiv.abko.todo.comment.domain.in.ReadCommentByIdUseCase;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import indiv.abko.todo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadCommentByIdUseCaseService implements ReadCommentByIdUseCase {
    private final CommentRepository commentRepo;

    @Override
    public Comment execute(final long id) {
        return commentRepo.findById(id)
                .orElseThrow(() -> new BusinessException(CommentExceptionEnum.COMMENT_NOT_FOUND));
    }
}
