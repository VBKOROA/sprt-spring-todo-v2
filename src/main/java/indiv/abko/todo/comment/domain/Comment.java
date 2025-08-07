package indiv.abko.todo.comment.domain;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.global.exception.GlobalExceptionEnum;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.global.vo.ContentVO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {
    private Long id;
    private ContentVO content;
    private AuthorVO author;
    private Long todoId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void updateContent(String content, long requesterId) {
        if(author.getId().equals(requesterId) == false) {
            throw new BusinessException(CommentExceptionEnum.PERMISSION_DENIED);
        }
        this.content = ContentVO.fromRawContent(content);
    }
}
