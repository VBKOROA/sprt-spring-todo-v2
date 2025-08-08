package indiv.abko.todo.member.domain.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.port.in.UpdateMemberUseCase;
import indiv.abko.todo.member.domain.port.in.dto.MemberDto;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUpdateMemberUseCase implements UpdateMemberUseCase {
    private final MemberRepository memberRepo;

    @Override
    @Transactional
    public MemberDto update(final long requesterId, final String name) {
        Member requester = memberRepo.findById(requesterId)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_NOT_FOUND));
        requester.update(name);
        Member updatedMember = memberRepo.save(requester);
        return MemberDto.from(updatedMember);
    }
}
