package indiv.abko.todo.member.application.port.out;

import indiv.abko.todo.member.domain.Member;

public interface CreateTokenPort {
    String createToken(Member member);
}
