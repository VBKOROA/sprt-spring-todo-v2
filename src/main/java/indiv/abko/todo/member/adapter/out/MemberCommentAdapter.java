package indiv.abko.todo.member.adapter.out;

import indiv.abko.todo.comment.domain.in.DeleteCommentsByAuthorId;
import indiv.abko.todo.member.application.port.out.CommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberCommentAdapter implements CommentPort {
    private final DeleteCommentsByAuthorId deleteCommentsByAuthorId;

    @Override
    public void deleteAllByMemberId(long requesterId) {
        deleteCommentsByAuthorId.execute(requesterId);
    }
}
