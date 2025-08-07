package indiv.abko.todo.member.application.port.out;

public interface CommentPort {
    void deleteAllByMemberId(long requesterId);
}
