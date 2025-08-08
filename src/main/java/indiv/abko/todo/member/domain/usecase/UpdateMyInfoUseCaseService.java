package indiv.abko.todo.member.domain.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.port.in.UpdateMyInfoUseCase;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMyInfoUseCaseService implements UpdateMyInfoUseCase {
    private final MemberRepository memberRepo;

    @Override
    @Transactional
    public Member execute(final long requesterId, final String name) {
        final var requester = memberRepo.findById(requesterId)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_NOT_FOUND));
        requester.update(name);
        return memberRepo.save(requester);
    }
}
