package indiv.abko.todo.member.application.port.out;

import indiv.abko.todo.member.domain.Member;

public interface TodoJwtUtilPort {
    String createToken(Member member);
}
