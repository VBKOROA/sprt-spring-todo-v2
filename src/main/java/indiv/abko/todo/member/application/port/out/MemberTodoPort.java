package indiv.abko.todo.member.application.port.out;

public interface MemberTodoPort {
    void deleteTodosByMemberId(long requesterId);
}
