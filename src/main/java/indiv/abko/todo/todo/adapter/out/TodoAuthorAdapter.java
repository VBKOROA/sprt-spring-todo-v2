package indiv.abko.todo.todo.adapter.out;

import indiv.abko.todo.member.application.port.in.GetAuthorUseCase;
import indiv.abko.todo.todo.application.port.out.TodoAuthorPort;
import indiv.abko.todo.global.vo.AuthorVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoAuthorAdapter implements TodoAuthorPort {
    private final GetAuthorUseCase getAuthorUseCase;

    @Override
    public AuthorVO getAuthor(final long authorId) {
        final var authorInfo =  getAuthorUseCase.getAuthor(authorId);
        return AuthorVO.reconstitute(authorId, authorInfo.name());
    }
}
