package indiv.abko.todo.todo.adapter.out.persistence;

import indiv.abko.todo.global.entity.BaseTimeJpaEntity;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.global.vo.ContentVO;
import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.vo.TodoTitleVO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "todo")
public class TodoJpaEntity extends BaseTimeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 일정 제목

    private String content; // 일정 내용

    private Long authorId; // 작성자 고유키

    private String authorName; // 작성자 이름

    public Todo toDomain() {
        final var author = AuthorVO.reconstitute(authorId, authorName);
        return Todo.builder()
                .id(id)
                .title(new TodoTitleVO(title))
                .content(ContentVO.reconstitute(content))
                .author(author)
                .createdAt(getCreatedAt())
                .modifiedAt(getModifiedAt())
                .build();
    }

    public static TodoJpaEntity from(final Todo todo) {
        return TodoJpaEntity.builder()
                .id(todo.getId())
                .content(todo.getContent().getContent())
                .title(todo.getTitle().getTitle())
                .createdAt(todo.getCreatedAt())
                .modifiedAt(todo.getModifiedAt())
                .authorId(todo.getAuthor().getId())
                .authorName(todo.getAuthor().getName())
                .build();
    }
}
