package indiv.abko.todo.member.application.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.application.port.in.GetMemberUseCase;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultGetMemberUseCase implements GetMemberUseCase {
    private final MemberRepository memberRepo;

    @Override
    public Member get(long id) {
        return memberRepo.findById(id)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_NOT_FOUND));
    }
}
