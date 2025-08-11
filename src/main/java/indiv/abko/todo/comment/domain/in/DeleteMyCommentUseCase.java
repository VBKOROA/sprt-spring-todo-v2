package indiv.abko.todo.comment.domain.in;

public interface DeleteMyCommentUseCase {
    void execute(long id, long requesterId);
}
