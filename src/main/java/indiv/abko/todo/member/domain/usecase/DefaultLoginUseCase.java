package indiv.abko.todo.member.domain.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.port.in.LoginUseCase;
import indiv.abko.todo.member.domain.port.out.TodoJwtUtilPort;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import indiv.abko.todo.member.domain.port.out.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultLoginUseCase implements LoginUseCase {
    private final TodoJwtUtilPort todoJwtUtilPort;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public String execute(String email, String password) {
        var member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_LOGIN_FAILED));
        member.auth(password, passwordEncoder);
        String token = todoJwtUtilPort.createToken(member);
        return token;
    }
}
