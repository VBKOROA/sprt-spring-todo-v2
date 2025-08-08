package indiv.abko.todo.member.domain.port.out;

public interface MemberCommentPort {
    void deleteCommentsByMemberId(long requesterId);
}
