package indiv.abko.todo.member.domain.port.out;

public interface MemberTodoPort {
    void deleteTodosByMemberId(long requesterId);
}
