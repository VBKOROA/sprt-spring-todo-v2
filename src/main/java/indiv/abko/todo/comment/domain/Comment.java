package indiv.abko.todo.comment.domain;

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
}
