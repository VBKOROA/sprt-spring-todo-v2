package indiv.abko.todo.member.domain.port.out;

import indiv.abko.todo.member.domain.Member;

public interface MemberJwtUtilPort {
    String createToken(Member member);
}
