package indiv.abko.todo.comment.adapter.out;

import indiv.abko.todo.comment.domain.out.CommentAuthorPort;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.member.domain.port.in.GetNameByMemberIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentAuthorAdapter implements CommentAuthorPort {
    private final GetNameByMemberIdUseCase getNameByMemberIdUseCase;

    @Override
    public AuthorVO getAuthor(final long authorId) {
        final String authorName = getNameByMemberIdUseCase.execute(authorId);
        return AuthorVO.reconstitute(authorId, authorName);
    }
}
