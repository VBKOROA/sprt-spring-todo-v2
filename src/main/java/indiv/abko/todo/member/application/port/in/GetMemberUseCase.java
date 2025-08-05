package indiv.abko.todo.member.application.port.in;

import indiv.abko.todo.member.domain.Member;

public interface GetMemberUseCase {
    Member get(long id);
}
