package indiv.abko.todo.member.application.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.application.port.in.DeleteMemberUseCase;
import indiv.abko.todo.member.application.port.out.CommentPort;
import indiv.abko.todo.member.application.port.out.MemberTodoPort;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultDeleteMemberUseCase implements DeleteMemberUseCase {
    private final MemberRepository memberRepo;
    private final MemberTodoPort todoPort;
    private final CommentPort commentPort;

    @Override
    @Transactional
    public void execute(long requesterId) {
        if(memberRepo.isExistsById(requesterId) == false) {
                throw new BusinessException(MemberExceptionEnum.MEMBER_NOT_FOUND);
        }
        
        // Member 삭제 시,
        // 관련 정보 전부 말소
        todoPort.deleteTodosByMemberId(requesterId);
        commentPort.deleteAllByMemberId(requesterId);
        memberRepo.deleteById(requesterId);
    }
}
