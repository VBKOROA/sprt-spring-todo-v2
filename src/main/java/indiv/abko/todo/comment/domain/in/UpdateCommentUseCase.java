package indiv.abko.todo.comment.domain.in;

import indiv.abko.todo.comment.domain.Comment;

public interface UpdateCommentUseCase {

    Comment execute(UpdateCommentCommand command);
}
