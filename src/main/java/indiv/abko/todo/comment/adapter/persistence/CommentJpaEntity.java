package indiv.abko.todo.comment.adapter.persistence;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.global.entity.BaseTimeJpaEntity;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.global.vo.ContentVO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "comment")
public class CommentJpaEntity extends BaseTimeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 댓글 내용
    private String content;

    // 작성자 고유키
    private Long authorId;

    // 작성자명
    private String authorName;

    // Todo 고유키
    private Long todoId;

    public static CommentJpaEntity from(final Comment comment) {
        return CommentJpaEntity.builder()
                .id(comment.getId())
                .content(comment.getContent().getContent())
                .authorName(comment.getAuthor().getName())
                .authorId(comment.getAuthor().getId())
                .todoId(comment.getTodoId())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public Comment toDomain() {
        final var author = AuthorVO.reconstitute(authorId, authorName);
        return Comment.builder()
                .id(id)
                .content(ContentVO.reconstitute(content))
                .author(author)
                .modifiedAt(getModifiedAt())
                .createdAt(getCreatedAt())
                .todoId(todoId)
                .build();
    }
}
