package indiv.abko.todo.comment.domain.out;

public interface TodoPort {
    void shouldExistBy(long todoId);

    void deleteAllByMemberId(long requesterId);
}
