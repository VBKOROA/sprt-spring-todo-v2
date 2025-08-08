package indiv.abko.todo.member.domain.port.in.command;

import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.port.out.MemberPasswordEncoder;
import indiv.abko.todo.member.domain.vo.EmailVO;
import indiv.abko.todo.member.domain.vo.EncodedPasswordVO;
import indiv.abko.todo.member.domain.vo.NameVO;
import lombok.Builder;

@Builder
public record SignUpCommand(
        String name,
        String email,
        String password
) {
    public Member toDomain(MemberPasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(NameVO.fromRawName(name))
                .email(EmailVO.fromRawEmail(email))
                .password(EncodedPasswordVO.fromRawPassword(password, passwordEncoder))
                .build();
    }
}
