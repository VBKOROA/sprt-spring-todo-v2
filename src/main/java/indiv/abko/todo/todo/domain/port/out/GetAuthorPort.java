package indiv.abko.todo.todo.domain.port.out;

import indiv.abko.todo.todo.domain.vo.AuthorVO;

public interface GetAuthorPort {
    AuthorVO getAuthor(long authorId);
}
