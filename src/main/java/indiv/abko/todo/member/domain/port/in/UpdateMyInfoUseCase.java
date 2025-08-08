package indiv.abko.todo.member.domain.port.in;

import indiv.abko.todo.member.domain.Member;

public interface UpdateMyInfoUseCase {
    Member execute(long requesterId, String name);
}
