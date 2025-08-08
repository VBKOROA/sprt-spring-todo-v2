package indiv.abko.todo.tododetail.adapter.out;

import indiv.abko.todo.todo.domain.port.in.TodoUseCaseFacade;
import indiv.abko.todo.todo.domain.port.in.command.GetTodoCommand;
import indiv.abko.todo.tododetail.domain.out.TodoDetailTodoPort;
import indiv.abko.todo.tododetail.domain.out.TodoSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoDetailTodoAdapter implements TodoDetailTodoPort {
    private final TodoUseCaseFacade todoUseCaseFacade;

    @Override
    public TodoSpec getTodoById(final long todoId) {
        final var getTodoCommand = new GetTodoCommand(todoId);
        final var todo = todoUseCaseFacade.getTodo(getTodoCommand);
        return TodoSpec.builder()
                .id(todo.getId())
                .title(todo.getTitle().getTitle())
                .authorName(todo.getAuthor().getName())
                .modifiedAt(todo.getModifiedAt())
                .content(todo.getContent().getContent())
                .createdAt(todo.getCreatedAt())
                .authorId(todo.getAuthor().getId())
                .build();
    }
}
