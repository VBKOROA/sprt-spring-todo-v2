package indiv.abko.todo.todo.domain.port.out;

import indiv.abko.todo.global.vo.AuthorVO;

public interface TodoAuthorPort {
    AuthorVO getAuthor(final long authorId);
}
