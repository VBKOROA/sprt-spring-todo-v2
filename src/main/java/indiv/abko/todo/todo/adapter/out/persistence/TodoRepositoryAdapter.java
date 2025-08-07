package indiv.abko.todo.todo.adapter.out.persistence;

import indiv.abko.todo.todo.adapter.out.persistence.entity.TodoJpaEntity;
import indiv.abko.todo.todo.adapter.out.persistence.mapper.TodoEntityMapper;
import indiv.abko.todo.todo.domain.SearchTodosCriteria;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import indiv.abko.todo.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryAdapter implements TodoRepository {
    private final TodoJpaRepository todoJpaRepository;
    private final TodoQDSLRepository todoQDSLRepository;
    private final TodoEntityMapper todoEntityMapper;

    public Optional<Todo> findBy(final Long id) {
        return todoJpaRepository.findById(id)
                .map(todoEntityMapper::toDomain);
    }

    @Override
    public Page<Todo> searchSummaries(final SearchTodosCriteria searchCriteria, final Pageable pageable) {
        return todoQDSLRepository.search(searchCriteria, pageable);
    }

    @Override
    public Todo save(final Todo todo) {
        final TodoJpaEntity todoEntity = todoJpaRepository.save(todoEntityMapper.toEntity(todo));
        return todoEntityMapper.toDomain(todoEntity);
    }

    @Override
    public Todo saveComment(final Todo todo) {
        final TodoJpaEntity todoEntity = todoJpaRepository.save(todoEntityMapper.toEntity(todo));
        return todoEntityMapper.toDomain(todoEntity);
    }

    @Override
    public Optional<Todo> findSummary(final Long id) {
        return todoJpaRepository.findById(id).map(todoEntityMapper::toDomain);
    }

    @Override
    public void delete(final Todo todo) {
        final TodoJpaEntity todoEntity = todoEntityMapper.toEntity(todo);
        todoJpaRepository.delete(todoEntity);
    }

    @Override
    public boolean isExistBy(long todoId) {
        return todoJpaRepository.existsById(todoId);
    }
}
