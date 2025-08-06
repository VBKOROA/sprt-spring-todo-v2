package indiv.abko.todo.member.application.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.application.port.in.SignUpMemberUseCase;
import indiv.abko.todo.member.application.port.in.command.SignUpMemberCommand;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import indiv.abko.todo.member.domain.port.out.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultSignUpMemberUseCase implements SignUpMemberUseCase {
    private final MemberRepository memberRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member signUp(final SignUpMemberCommand signUpCommand) {
        if(memberRepo.isExistsByEmail(signUpCommand.email())) {
            throw new BusinessException(MemberExceptionEnum.MEMBER_EMAIL_DUPLICATE);
        }
        final Member member = signUpCommand.toDomain(passwordEncoder);
        return memberRepo.create(member);
    }
}
