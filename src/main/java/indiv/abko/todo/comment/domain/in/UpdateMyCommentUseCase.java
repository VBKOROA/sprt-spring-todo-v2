package indiv.abko.todo.comment.domain.in;

import indiv.abko.todo.comment.domain.Comment;

public interface UpdateMyCommentUseCase {
    Comment execute(UpdateMyCommentCommand command);
}
