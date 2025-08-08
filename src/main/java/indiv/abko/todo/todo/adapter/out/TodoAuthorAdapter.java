package indiv.abko.todo.todo.adapter.out;

import indiv.abko.todo.member.domain.port.in.GetNameByMemberIdUseCase;
import indiv.abko.todo.todo.application.port.out.TodoAuthorPort;
import indiv.abko.todo.global.vo.AuthorVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoAuthorAdapter implements TodoAuthorPort {
    private final GetNameByMemberIdUseCase getNameByMemberIdUseCase;

    @Override
    public AuthorVO getAuthor(final long authorId) {
        final var authorInfo =  getNameByMemberIdUseCase.getAuthor(authorId);
        return AuthorVO.reconstitute(authorId, authorInfo.name());
    }
}
