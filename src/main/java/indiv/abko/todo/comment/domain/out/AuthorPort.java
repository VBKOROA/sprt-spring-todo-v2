package indiv.abko.todo.comment.domain.out;

import indiv.abko.todo.global.vo.AuthorVO;

public interface AuthorPort {
    AuthorVO getAuthor(long authorId);
}
