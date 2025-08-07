package indiv.abko.todo.comment.domain.in;

public interface WriteCommentUseCase {
    CommentDto execute(WriteCommentCommand command);
}
