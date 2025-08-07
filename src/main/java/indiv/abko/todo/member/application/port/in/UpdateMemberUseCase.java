package indiv.abko.todo.member.application.port.in;

import indiv.abko.todo.member.application.port.in.dto.MemberDto;

public interface UpdateMemberUseCase {
    MemberDto update(long requesterId, String name);
}
