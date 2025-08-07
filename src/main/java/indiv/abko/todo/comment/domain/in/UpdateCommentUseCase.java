package indiv.abko.todo.comment.domain.in;

public interface UpdateCommentUseCase {

    CommentDto execute(UpdateCommentCommand command);
}
