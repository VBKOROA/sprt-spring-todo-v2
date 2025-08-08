package indiv.abko.todo.member.adapter.out.security;

import indiv.abko.todo.global.security.JwtUtil;
import indiv.abko.todo.member.domain.port.out.MemberJwtUtilPort;
import indiv.abko.todo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoJwtUtilAdapter implements MemberJwtUtilPort {
    private final JwtUtil jwtUtil;

    @Override
    public String createToken(final Member member) {
        return jwtUtil.createToken(member.getId(), member.getName().getValue());
    }
}
