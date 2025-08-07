package indiv.abko.todo.member.application.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.application.port.in.dto.GetAuthorDto;
import indiv.abko.todo.member.application.port.in.GetAuthorUseCase;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultGetAuthorUseCase implements GetAuthorUseCase {
    private final MemberRepository memberRepo;

    @Override
    @Transactional(readOnly = true)
    public GetAuthorDto getAuthor(long id) {
        final var member = memberRepo.findById(id)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_NOT_FOUND));
        return new GetAuthorDto(member.getName().getValue());
    }
}
