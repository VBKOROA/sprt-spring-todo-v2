package indiv.abko.todo.member.application.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.application.port.in.dto.GetAuthorDto;
import indiv.abko.todo.member.application.port.in.GetNameByMemberIdUseCase;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultGetNameByMemberIdUseCase implements GetNameByMemberIdUseCase {
    private final MemberRepository memberRepo;

    @Override
    @Transactional(readOnly = true)
    public String execute(long id) {
        final String name = memberRepo.findNameById(id)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_NOT_FOUND));
        return name;
    }
}
