package indiv.abko.todo.member.application.port.in;

import indiv.abko.todo.member.application.port.in.dto.MemberDto;

public interface GetMemberUseCase {
    MemberDto get(long id);
}
