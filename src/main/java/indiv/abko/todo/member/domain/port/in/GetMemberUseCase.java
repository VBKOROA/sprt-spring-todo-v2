package indiv.abko.todo.member.domain.port.in;

import indiv.abko.todo.member.domain.port.in.dto.MemberDto;

public interface GetMemberUseCase {
    MemberDto get(long id);
}
