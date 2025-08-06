package indiv.abko.todo.member.adapter.out.security;

import indiv.abko.todo.global.security.JwtUtil;
import indiv.abko.todo.member.application.port.out.CreateTokenPort;
import indiv.abko.todo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateTokenAdapter implements CreateTokenPort {
    private final JwtUtil jwtUtil;

    @Override
    public String createToken(Member member) {
        return jwtUtil.createToken(member.getId(), member.getName().getValue());
    }
}
