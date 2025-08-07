package indiv.abko.todo.todo.adapter.out.persistence.mapper;

import indiv.abko.todo.todo.adapter.out.persistence.entity.TodoJpaEntity;
import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.global.vo.ContentVO;
import indiv.abko.todo.todo.domain.vo.TodoTitleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TodoEntityMapper {
    public Todo toDomain(final TodoJpaEntity todoJpaEntity) {
        var author = AuthorVO.reconstitute(todoJpaEntity.getAuthorId(), todoJpaEntity.getAuthorName());
        return Todo.builder()
                .id(todoJpaEntity.getId())
                .title(new TodoTitleVO(todoJpaEntity.getTitle()))
                .content(ContentVO.reconstitute(todoJpaEntity.getContent()))
                .author(author)
                .createdAt(todoJpaEntity.getCreatedAt())
                .modifiedAt(todoJpaEntity.getModifiedAt())
                .build();
    }

    public TodoJpaEntity toEntity(Todo todo) {
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
