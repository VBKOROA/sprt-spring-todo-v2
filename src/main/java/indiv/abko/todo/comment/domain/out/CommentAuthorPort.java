package indiv.abko.todo.comment.domain.out;

import indiv.abko.todo.global.vo.AuthorVO;

public interface CommentAuthorPort {
    AuthorVO getAuthor(long authorId);
}
