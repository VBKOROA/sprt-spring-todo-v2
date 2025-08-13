package indiv.abko.todo.member.domain.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.port.in.LoginUseCase;
import indiv.abko.todo.member.domain.port.out.MemberJwtUtilPort;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import indiv.abko.todo.member.domain.port.out.MemberPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final MemberJwtUtilPort todoJwtUtilPort;
    private final MemberRepository memberRepository;
    private final MemberPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public String execute(final String email, final String password) {
        final Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_LOGIN_FAILED));
        member.verifyPassword(password, passwordEncoder);
        return todoJwtUtilPort.createToken(member);
    }
}
