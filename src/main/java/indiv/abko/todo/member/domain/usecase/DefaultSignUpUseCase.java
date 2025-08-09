package indiv.abko.todo.member.domain.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.port.in.SignUpUseCase;
import indiv.abko.todo.member.domain.port.in.command.SignUpCommand;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import indiv.abko.todo.member.domain.port.out.MemberPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultSignUpUseCase implements SignUpUseCase {
    private final MemberRepository memberRepo;
    private final MemberPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member execute(final SignUpCommand signUpCommand) {
        if(memberRepo.isExistsByEmail(signUpCommand.email())) {
            throw new BusinessException(MemberExceptionEnum.MEMBER_EMAIL_DUPLICATE);
        }
        final Member member = signUpCommand.toDomain(passwordEncoder);
        return memberRepo.save(member);
    }
}
