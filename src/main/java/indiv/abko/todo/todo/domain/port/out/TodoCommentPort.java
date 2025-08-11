package indiv.abko.todo.todo.domain.port.out;

import java.util.List;

public interface TodoCommentPort {
    void deleteAllByTodoId(final long todoId);

    void deleteAllByTodoIds(List<Long> todoIds);
}
