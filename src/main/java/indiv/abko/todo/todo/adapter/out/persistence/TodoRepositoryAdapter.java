package indiv.abko.todo.todo.adapter.out.persistence;

import indiv.abko.todo.todo.domain.SearchTodosCriteria;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import indiv.abko.todo.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryAdapter implements TodoRepository {
    private final TodoJpaRepository todoJpaRepository;
    private final TodoQDSLRepository todoQDSLRepository;

    public Optional<Todo> findBy(final Long id) {
        return todoJpaRepository.findById(id)
                .map(TodoJpaEntity::toDomain);
    }

    @Override
    public Page<Todo> searchSummaries(final SearchTodosCriteria searchCriteria, final Pageable pageable) {
        return todoQDSLRepository.search(searchCriteria, pageable);
    }

    @Override
    public Todo save(final Todo todo) {
        final TodoJpaEntity todoEntity = todoJpaRepository.save(TodoJpaEntity.from(todo));
        return todoEntity.toDomain();
    }

    @Override
    public Optional<Todo> findSummary(final Long id) {
        return todoJpaRepository.findById(id).map(TodoJpaEntity::toDomain);
    }

    @Override
    public void delete(final Todo todo) {
        final TodoJpaEntity todoEntity = TodoJpaEntity.from(todo);
        todoJpaRepository.delete(todoEntity);
    }

    @Override
    public boolean isExistBy(final long todoId) {
        return todoJpaRepository.existsById(todoId);
    }

    @Override
    public List<Long> findTodoIdsByAuthorId(final long authorId) {
        final List<TodoIdProjection> todoIds = todoJpaRepository.findAllByAuthorId(authorId);
        return todoIds.stream().map(TodoIdProjection::id).toList();
    }

    @Override
    public void deleteAllByAuthorId(long authorId) {
        todoJpaRepository.deleteAllByAuthorId(authorId);
    }
}
