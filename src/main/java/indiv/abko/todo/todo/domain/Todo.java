package indiv.abko.todo.todo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import indiv.abko.todo.todo.domain.exception.TodoExceptionEnum;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.global.vo.ContentVO;
import indiv.abko.todo.todo.domain.vo.PasswordVO;
import indiv.abko.todo.todo.domain.vo.TodoTitleVO;
import indiv.abko.todo.global.exception.BusinessException;
import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Todo {
    private static final int COMMENT_LIMIT = 10;

    private Long id;
    private TodoTitleVO title; // 일정 제목
    private ContentVO content; // 일정 내용
    private AuthorVO author;
    private PasswordVO password; // 비밀번호
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void updateContent(final String content, final long requester) {
        shouldHaveAuth(requester);
        this.content = ContentVO.fromRawContent(content);
    }

    public void shouldHaveAuth(final long memberId) {
        if (author.getId().equals(memberId) == false) {
            throw new BusinessException(TodoExceptionEnum.TODO_PERMISSION_DENIED);
        }
    }
}
