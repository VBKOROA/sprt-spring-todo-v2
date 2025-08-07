package indiv.abko.todo.comment.adapter.out;

import indiv.abko.todo.comment.domain.out.AuthorPort;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.member.application.port.in.GetAuthorDTO;
import indiv.abko.todo.member.application.port.in.GetAuthorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentAuthorAdapter implements AuthorPort {
    private final GetAuthorUseCase getAuthorUseCase;

    @Override
    public AuthorVO getAuthor(long authorId) {
        GetAuthorDTO getAuthorDTO = getAuthorUseCase.getAuthor(authorId);
        return AuthorVO.reconstitute(authorId, getAuthorDTO.name());
    }
}
