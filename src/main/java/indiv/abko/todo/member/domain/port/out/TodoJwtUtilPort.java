package indiv.abko.todo.member.domain.port.out;

import indiv.abko.todo.member.domain.Member;

public interface TodoJwtUtilPort {
    String createToken(Member member);
}
