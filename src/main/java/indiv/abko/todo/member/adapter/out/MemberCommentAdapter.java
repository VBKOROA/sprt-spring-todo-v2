package indiv.abko.todo.member.adapter.out;

import indiv.abko.todo.comment.domain.in.DeleteCommentsByAuthorId;
import indiv.abko.todo.member.domain.port.out.MemberCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberCommentAdapter implements MemberCommentPort {
    private final DeleteCommentsByAuthorId deleteCommentsByAuthorId;

    @Override
    public void deleteCommentsByMemberId(long requesterId) {
        deleteCommentsByAuthorId.execute(requesterId);
    }
}
