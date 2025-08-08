package indiv.abko.todo.member.domain.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.port.in.ReadMemberInfoUseCase;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultGetMemberUseCase implements ReadMemberInfoUseCase {
    private final MemberRepository memberRepo;

    @Override
    @Transactional(readOnly = true)
    public Member get(long id) {
        return memberRepo.findById(id)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_NOT_FOUND));
    }
}
