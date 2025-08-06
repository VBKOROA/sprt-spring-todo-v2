package indiv.abko.todo.member.application.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.application.port.in.GetAuthorDTO;
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
    public GetAuthorDTO getAuthor(Long id) {
        final var member = memberRepo.findById(id)
                .orElseThrow(() -> new BusinessException(MemberExceptionEnum.MEMBER_NOT_FOUND));
        return new GetAuthorDTO(member.getName().getValue());
    }
}
