package indiv.abko.todo.comment.domain.in;

public interface DeleteCommentsByAuthorId {
    void execute(long authorId);
}
