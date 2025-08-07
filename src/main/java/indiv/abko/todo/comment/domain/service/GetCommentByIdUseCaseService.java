package indiv.abko.todo.comment.domain.service;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.CommentExceptionEnum;
import indiv.abko.todo.comment.domain.in.CommentDto;
import indiv.abko.todo.comment.domain.in.GetCommentByIdUseCase;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import indiv.abko.todo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommentByIdUseCaseService implements GetCommentByIdUseCase {
    private final CommentRepository commentRepo;

    @Override
    public CommentDto execute(long id) {
        var comment = commentRepo.findById(id)
                .orElseThrow(() -> new BusinessException(CommentExceptionEnum.COMMENT_NOT_FOUND));
        return CommentDto.from(comment);
    }
}
