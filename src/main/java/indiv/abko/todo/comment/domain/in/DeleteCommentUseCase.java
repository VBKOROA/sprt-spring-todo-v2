package indiv.abko.todo.comment.domain.in;

public interface DeleteCommentUseCase {
    void execute(long id, long requesterId);
}
