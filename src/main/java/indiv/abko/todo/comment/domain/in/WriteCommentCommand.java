package indiv.abko.todo.comment.domain.in;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.global.vo.ContentVO;
import lombok.Builder;

@Builder
public record WriteCommentCommand(
    Long todoId,
    String content,
    Long authorId
) {
    public Comment toDomain(AuthorVO author) {
        return Comment.builder()
                .todoId(todoId)
                .content(ContentVO.fromRawContent(content))
                .author(author)
                .build();
    }
}