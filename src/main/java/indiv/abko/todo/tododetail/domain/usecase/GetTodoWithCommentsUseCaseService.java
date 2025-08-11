package indiv.abko.todo.tododetail.domain.usecase;

import indiv.abko.todo.tododetail.domain.in.GetTodoWithCommentsUseCase;
import indiv.abko.todo.tododetail.domain.in.TodoWithCommentsDto;
import indiv.abko.todo.tododetail.domain.out.CommentSpec;
import indiv.abko.todo.tododetail.domain.out.TodoDetailCommentPort;
import indiv.abko.todo.tododetail.domain.out.TodoDetailTodoPort;
import indiv.abko.todo.tododetail.domain.out.TodoSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTodoWithCommentsUseCaseService implements GetTodoWithCommentsUseCase {
    private final TodoDetailTodoPort todoPort;
    private final TodoDetailCommentPort commentPort;

    @Override
    @Transactional(readOnly = true)
    public TodoWithCommentsDto execute(final long todoId) {
        final TodoSpec todo = todoPort.getTodoById(todoId);
        final List<CommentSpec> comments = commentPort.getCommentsByTodoId(todoId);
        return new TodoWithCommentsDto(todo, comments);
    }
}
