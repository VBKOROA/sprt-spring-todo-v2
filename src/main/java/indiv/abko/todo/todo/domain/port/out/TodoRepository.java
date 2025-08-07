package indiv.abko.todo.todo.domain.port.out;

import indiv.abko.todo.todo.domain.SearchTodosCriteria;
import indiv.abko.todo.todo.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TodoRepository {
    Optional<Todo> findBy(Long id);
    Page<Todo> searchSummaries(SearchTodosCriteria searchCriteria, Pageable pageable);

    Todo save(Todo todo);
    Todo saveComment(Todo todo);

    Optional<Todo> findSummary(Long id);

    void delete(Todo todo);

    boolean isExistBy(long todoId);
}
