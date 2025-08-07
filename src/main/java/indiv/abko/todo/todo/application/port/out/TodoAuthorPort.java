package indiv.abko.todo.todo.application.port.out;

import indiv.abko.todo.global.vo.AuthorVO;

public interface TodoAuthorPort {
    AuthorVO getAuthor(long authorId);
}
