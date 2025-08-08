package indiv.abko.todo.member.domain.port.out;

public interface CommentPort {
    void deleteAllByMemberId(long requesterId);
}
