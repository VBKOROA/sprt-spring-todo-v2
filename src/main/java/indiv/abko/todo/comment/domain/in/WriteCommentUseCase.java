package indiv.abko.todo.comment.domain.in;

import indiv.abko.todo.comment.domain.Comment;

public interface WriteCommentUseCase {
    Comment execute(WriteCommentCommand command);
}
