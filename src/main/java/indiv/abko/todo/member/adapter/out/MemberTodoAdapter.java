package indiv.abko.todo.member.adapter.out;

import indiv.abko.todo.member.domain.port.out.MemberTodoPort;
import indiv.abko.todo.todo.application.port.in.DeleteTodosByAuthorId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberTodoAdapter implements MemberTodoPort {
    private final DeleteTodosByAuthorId deleteTodosByAuthorId;

    @Override
    public void deleteTodosByMemberId(final long requesterId) {
        deleteTodosByAuthorId.execute(requesterId);
    }
}
