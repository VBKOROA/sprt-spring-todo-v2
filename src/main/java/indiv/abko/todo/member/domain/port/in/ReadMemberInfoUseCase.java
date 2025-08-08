package indiv.abko.todo.member.domain.port.in;

import indiv.abko.todo.member.domain.Member;

public interface ReadMemberInfoUseCase {
    Member execute(long id);
}
